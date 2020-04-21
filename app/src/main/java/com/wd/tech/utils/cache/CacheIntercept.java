package com.bw.movie.widget.cache;

import com.bw.movie.util.NetUtil;
import com.bw.movie.widget.MyApplication;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * date:2020/4/12 0012
 * author:胡锦涛(Administrator)
 * function:缓存拦截器
 */
public class CacheIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //过期时间1个小时
        int maxAge=60*60;
        //无网超时为4周
        int maxStale=60*60*24*28;
        //拿到request
        Request request = chain.request();
        //判断有无网
        if (NetUtil.hasNet(MyApplication.getmContext())){
            request= request.newBuilder()
                    //只能请求网络
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        }else {
            request=request.newBuilder()
                    //无网从缓存中获取
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        //拿到response重构 手动缓存
        Response response = chain.proceed(request);
        if (NetUtil.hasNet(MyApplication.getmContext())){
            //设置缓存时间
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public,max-age=" + maxAge)
                    .build();

        }else {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public,only-if-cached,max-stale=" + maxStale)
                    .build();
        }
        return response;
    }
}
