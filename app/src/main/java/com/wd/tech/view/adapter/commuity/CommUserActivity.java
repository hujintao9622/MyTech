package com.wd.tech.view.adapter.commuity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.presenter.TechPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommUserActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.desion)
    TextView desion;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.rc)
    RecyclerView rc;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_comm_user;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.iv_head, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.more:
                break;
        }
    }
}
