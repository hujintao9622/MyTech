package com.wd.tech.widget;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:api
 */
public interface ApiService {
    @GET //get无参
    Observable<ResponseBody> getNoParams(@Url String url);
    @GET //get有参
    Observable<ResponseBody> getDoParams(@Url String url, @QueryMap HashMap<String, Object> map);
    @GET //get 头参
    Observable<ResponseBody> getHeadParams(@Url String url, @HeaderMap HashMap<String,Object> map);
    @POST //post头像
    @Multipart
    Observable<ResponseBody> postDoHeadPic(@Url String url, @Part MultipartBody.Part img);
    @POST //post无参
    @FormUrlEncoded
    Observable<ResponseBody> postNoParams(@Url String url);
    @POST //post有参
    @FormUrlEncoded
    Observable<ResponseBody> postDoParams(@Url String url, @FieldMap HashMap<String, Object> map);
    @PUT //put 有参
    @FormUrlEncoded
    Observable<ResponseBody> putDoParams(@Url String url,@FieldMap HashMap<String,Object> map);
    @PUT//put 无参
    @FormUrlEncoded
    Observable<ResponseBody> putNoParams(@Url String url);
    @DELETE //dlt 无参
    Observable<ResponseBody> dltNoParams(@Url String url);
    @DELETE //dlt 有参
    Observable<ResponseBody> dltDoParams(@Url String url, @QueryMap HashMap<String, Object> map);
}
