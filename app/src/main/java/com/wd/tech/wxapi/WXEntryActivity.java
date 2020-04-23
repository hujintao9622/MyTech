package com.wd.tech.wxapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.login.LoginBean;
import com.wd.tech.model.bean.wx.WXBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.MainActivity;
import com.wd.tech.view.activity.login.LoginActivity;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;

/**
 * date:2020/4/22 0022
 * author:胡锦涛(Administrator)
 * function:
 */
public class WXEntryActivity extends BaseActivity<TechPresenter> implements IWXAPIEventHandler {

    private SharedPreferences sp;

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode){
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if ( baseResp.getType() ==2) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.getType()) {
                    case 1:
                        //拿到了微信返回的code,立马再去请求access_token
                        String code = ((SendAuth.Resp) baseResp).code;
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
                        HashMap<String,Object> map=new HashMap<>();
                        map.put("ak",1);
                        HashMap<String,Object> map1=new HashMap<>();
                        map1.put("code",code);
                        mPresenter.postHeadParams(MyUrls.BASE_WX_LOGIN, WXBean.class,map,map1);
                        break;
                    case 2:
                        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
                break;
        }

    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {
        //不写这句可能无法调起onResp
        MyApp.mWxApi.handleIntent(getIntent(), this);
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_wx_entry;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof WXBean && TextUtils.equals("0000", ((WXBean) o).getStatus())) {

            WXBean.ResultBean result = ((WXBean) o).getResult();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("headPic", result.getHeadPic());
            editor.putString("nickName", result.getNickName());
            editor.putInt("userId", result.getUserId());
            editor.putString("sessionId", result.getSessionId());
            editor.putBoolean("b", true);
            editor.commit();
            startActivity(WXEntryActivity.this,MainActivity.class);
            finish();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
