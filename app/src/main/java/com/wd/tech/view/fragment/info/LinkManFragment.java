package com.wd.tech.view.fragment.info;

import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.presenter.TechPresenter;

/**
 * date:2020/4/21 0021
 * author:胡锦涛(Administrator)
 * function:
 */
public class LinkManFragment extends BaseFragment<TechPresenter> {
    @Override
    protected void initView(View view) {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_linkman;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }
}
