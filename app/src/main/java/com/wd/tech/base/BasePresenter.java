package com.wd.tech.base;

import java.lang.ref.WeakReference;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:Presenter基类
 */
public abstract class BasePresenter <V>{
    //弱引用解决内存泄露
    private WeakReference<V> weakReference;

    public void attach(V view){
        weakReference = new WeakReference<>(view);
    }
    public void detach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference=null;
        }
    }
    //获取view
    public V getView(){
        return weakReference.get();
    }
    //获取model对象

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
