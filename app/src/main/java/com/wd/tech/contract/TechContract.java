package com.wd.tech.contract;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:
 */
public interface TechContract {
    interface IModelCallback<T>{
        void onSuccess(T t);
        void onFailure(Throwable e);
    }
    interface IView<T>{
        void onSuccess(T t);
        void onFailure(Throwable e);
    }
    interface IPresenter{
        //get无参
        void getNoParams(String url, Class cls);
        //get有参
        void getDoParams(String url, Class cls, HashMap<String, Object> map);
        //get头参
        void getHeadParams(String url, Class cls, HashMap<String, Object> map);
        //post 头像
        void postDoHeadPic(String url, Class cls, MultipartBody.Part image);
        //post无参
        void postNoParams(String url, Class cls);
        //post有参
        void postDoParams(String url, Class cls, HashMap<String, Object> map);
        //post 头参
        void postHeadParams(String url, Class cls, HashMap<String, Object> map,HashMap<String, Object> map1);
        //post file
        void postFileParams(String url, Class cls, HashMap<String, RequestBody> map);
        //put无参
        void putNoParams(String url, Class cls);
        //put有参
        void putDoParams(String url, Class cls, HashMap<String, Object> map);
        //dlt无参
        void dltNoParams(String url, Class cls);
        //dlt有参
        void dltDoParams(String url, Class cls, HashMap<String, Object> map);
    }
    interface IModel{
        //get无参
        void getNoParams(String url, Class cls, IModelCallback iModelCallback);
        //get有参
        void getDoParams(String url, Class cls, HashMap<String, Object> map, IModelCallback iModelCallback);
        //get头参
        void getHeadParams(String url, Class cls, HashMap<String, Object> map, IModelCallback iModelCallback);
        //post 头像
        void postDoHeadPic(String url, Class cls, MultipartBody.Part image, IModelCallback iModelCallback);
        //post无参
        void postNoParams(String url, Class cls, IModelCallback iModelCallback);
        //post有参
        void postDoParams(String url, Class cls, HashMap<String, Object> map, IModelCallback iModelCallback);
        //post 头参
        void postHeadParams(String url, Class cls, HashMap<String, Object> map,HashMap<String, Object> map1, IModelCallback iModelCallback);
        //post file
        void postFileParams(String url, Class cls,HashMap<String, RequestBody> map,IModelCallback iModelCallback);
        //put无参
        void putNoParams(String url, Class cls, IModelCallback iModelCallback);
        //put有参
        void putDoParams(String url, Class cls, HashMap<String, Object> map, IModelCallback iModelCallback);
        //dlt无参
        void dltNoParams(String url, Class cls, IModelCallback iModelCallback);
        //dlt有参
        void dltDoParams(String url, Class cls, HashMap<String, Object> map, IModelCallback iModelCallback);
    }
}
