package com.wd.tech.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.wd.tech.R;
import com.wd.tech.utils.cache.CacheIntercept;
import com.wd.tech.widget.ApiService;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:
 */
public class NetUtil {
    private final ApiService api;
    private String sid;
    private int uid;
    private OkHttpClient okhttp;

    private static final class Holder{
        private static final NetUtil NET_UTIL=new NetUtil();
    }

    public static NetUtil getInstance() {
        return Holder.NET_UTIL;
    }
    private NetUtil(){
         try {
           //创建证书对象，方便管理证书数据
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);//初始化证书资源，首次是空

            //校验证书，x.509协议，所有的证书都是通过x.509协议生成的
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(MyApp.mContext.getResources().openRawResource(R.raw.server));

            //ssl协议入场，看看是不是符合ssl协议标准
            SSLContext sc = SSLContext.getInstance("TLS");
            //信任证书管理,这个是由我们自己生成的,信任我们自己的服务器证书
            TrustManager tm = new MyTrustManager(certificate);
            sc.init(null, new TrustManager[]{
                    tm
            }, null);

            //缓存文件
            File file = new File(MyApp.mContext.getCacheDir().getAbsolutePath(), "http");
            Cache cache = new Cache(file,1024*1024*10);
            //拦截器
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //okhttp
            okhttp = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            SharedPreferences sp = MyApp.getmContext().getSharedPreferences("login.dp", MODE_PRIVATE);
                            if (sp.getBoolean("b",false)){
                                sid = sp.getString("sessionId","");
                                uid = sp.getInt("userId",-1);
                                Request request = chain.request().newBuilder()
                                        .addHeader("userId", uid+"")
                                        .addHeader("sessionId", sid)
                                        .build();
                                return  chain.proceed(request);
                            }else {
                                return chain.proceed(chain.request());
                            }
                        }
                    })
                    //添加缓存
                    .addInterceptor(new CacheIntercept())
                    .cache(cache)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .hostnameVerifier(new TrustHostnameVerifier()) // 校验主机名（校验服务器），域名验证
                    .build();

        } catch (Exception e) {
            LogUtils.e("SSL设置错误");
        }
        //retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .client(okhttp)
                .baseUrl(MyUrls.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ApiService.class);
    }

    public ApiService getApi() {
        return api;
    }
    //获取矩形图片
    public void getPhoto(String url, ImageView iv){
        Glide.with(iv).load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(iv);
    }
    //获取圆形图片
    public void getCiclePhoto(String url, ImageView iv){
        Glide.with(iv).load(url)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(iv);
    }
    //get无参
    public void getNoParams(String url, final Class cls, final ICallback iCallback){
        api.getNoParams(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //get有参
    public void getDoParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.getDoParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //get头参
    public void getHeadParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.getHeadParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //post头像
    public void postDoHeadPic(String url, final Class cls, MultipartBody.Part image, final ICallback iCallback){
        api.postDoHeadPic(url,image).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //post无参
    public void postNoParams(String url, final Class cls, final ICallback iCallback){
        api.postNoParams(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //post有参
    public void postDoParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.postDoParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //post有参
    public void postHeadParams(String url, final Class cls, HashMap<String,Object> map,HashMap<String,Object> map1, final ICallback iCallback){
        api.postHeadParams(url,map,map1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //put 无参
    public void putNoParams(String url,final Class cls,final ICallback iCallback){
        api.putNoParams(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    };
    //put 有参
    public void putDoParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.putDoParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //dlt无参
    public void dltNoParams(String url, final Class cls, final ICallback iCallback){
        api.dltNoParams(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //dlt有参
    public void dltDoParams(String url, final Class cls, HashMap<String,Object> map, final ICallback iCallback){
        api.dltDoParams(url,map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Object o = new Gson().fromJson(string, cls);
                            iCallback.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //接口回调
    public interface ICallback<T>{
        void onSuccess(T t);
        void onError(Throwable e);
    }
    //判断是否有网
    public static boolean hasNet(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null&&info.isAvailable()){
            return true;
        }else {
            return false;
        }
    }



/**
     * 实现了 X509TrustManager
     * 通过此类中的 checkServerTrusted 方法来确认服务器证书是否正确
     */

    static class MyTrustManager implements X509TrustManager {
        X509Certificate cert;

        MyTrustManager(X509Certificate cert) {
            this.cert = cert;
        }


/**
         * 信任客户端的
         * @param chain
         * @param authType
         * @throws CertificateException
         */

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // 我们在客户端只做服务器端证书校验。
        }


/**
         * 信任服务器的
         * @param chain
         * @param authType
         * @throws CertificateException
         */

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // 确认服务器端证书和代码中 hard code 的 CRT 证书相同。
            if (chain[0].equals(this.cert)) {
                Log.i("Jin", "checkServerTrusted Certificate from server is valid!");
                return;// found match
            }
            throw new CertificateException("checkServerTrusted No trusted server cert found!");
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }



/**
     * 校验主机名
     */

    public static class TrustHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {

            if (hostname.trim().equals("mobile.bwstudent.com")) {//必须注意，根据题目要求配置相应主机名（域名或者ip地址）
                return true;
            }else{
                return false;
            }
        }
    }


    // 手机号正则表达式
    public static boolean isMobileNO(String mobileNums) {
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }
}
