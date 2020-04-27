package com.wd.tech.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.abner.ming.BaseSwipeBackActivity;
import com.wd.tech.contract.TechContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:activity基类
 */
public abstract class BaseHuaActivity<P extends BasePresenter> extends BaseSwipeBackActivity implements TechContract.IView {
    protected P mPresenter;
    private Unbinder bind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(layoutId());
        mPresenter=providePresenter();
        if (mPresenter != null) {
            //绑定
            mPresenter.attach(this);
        }
        //黄油刀
        bind = ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P providePresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (mPresenter != null) {
            mPresenter.detach();
        }
        if (bind != null) {
            bind.unbind();
        }
        DestroyActivity();
    }

    protected abstract void DestroyActivity();
}
