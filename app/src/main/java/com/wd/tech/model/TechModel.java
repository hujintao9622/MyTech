package com.wd.tech.model;

import com.wd.tech.contract.TechContract;
import com.wd.tech.utils.NetUtil;

import java.util.HashMap;

import okhttp3.MultipartBody;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:
 */
public class TechModel implements TechContract.IModel {
    @Override
    public void getNoParams(String url, Class cls, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().getNoParams(url, cls, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void getDoParams(String url, Class cls, HashMap<String, Object> map, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().getDoParams(url, cls, map,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void postDoHeadPic(String url, Class cls, MultipartBody.Part image, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().postDoHeadPic(url, cls, image,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void postNoParams(String url, Class cls, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().postNoParams(url, cls, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void postDoParams(String url, Class cls, HashMap<String, Object> map, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().postDoParams(url, cls, map,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }
    @Override
    public void putNoParams(String url, Class cls, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().putNoParams(url, cls, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void putDoParams(String url, Class cls, HashMap<String, Object> map, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().putDoParams(url, cls, map,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }
    @Override
    public void dltNoParams(String url, Class cls, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().dltNoParams(url, cls, new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }

    @Override
    public void dltDoParams(String url, Class cls, HashMap<String, Object> map, final TechContract.IModelCallback iModelCallback) {
        NetUtil.getInstance().dltDoParams(url, cls, map,new NetUtil.ICallback() {
            @Override
            public void onSuccess(Object o) {
                iModelCallback.onSuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                iModelCallback.onFailure(e);
            }
        });
    }
}
