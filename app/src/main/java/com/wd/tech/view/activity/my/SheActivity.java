package com.wd.tech.view.activity.my;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheActivity extends BaseActivity<TechPresenter> {

    private SharedPreferences sp;

    @BindView(R.id.she_login)
    TextView sheLogin;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //隐藏标题栏
        getSupportActionBar().hide();
        sp = getSharedPreferences("login.dp", Context.MODE_PRIVATE);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_she;
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

    @OnClick(R.id.she_login)
    public void onViewClicked() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("b", false);
        editor.commit();
        Intent intent = new Intent(SheActivity.this, MainActivity.class);
        intent.putExtra("login", true);
        startActivity(intent);
        finish();
    }
}
