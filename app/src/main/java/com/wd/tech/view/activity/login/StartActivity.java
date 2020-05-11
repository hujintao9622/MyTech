package com.wd.tech.view.activity.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.view.activity.information.MainActivity;

//启动页
public class StartActivity extends BaseActivity {
    int time=0;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (time == 3) {
                    startActivity(StartActivity.this,MainActivity.class);
                    finish();
                } else {
                    time++;
                    handler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        }
    };
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void DestroyActivity() {
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }
}
