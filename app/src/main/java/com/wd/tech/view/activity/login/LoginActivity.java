package com.wd.tech.view.activity.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.LoginBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.eye)
    ImageView eye;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    Button login;
    private SharedPreferences sp;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof LoginBean&&TextUtils.equals("0000",((LoginBean) o).getStatus())){
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean("b",true);
            edit.putInt("uid",((LoginBean) o).getResult().getUserId());
            edit.putString("sid",((LoginBean) o).getResult().getSessionId());
            Toast.makeText(this, ((LoginBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            edit.commit();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.eye, R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.eye:
                break;
            case R.id.register:
                break;
            case R.id.login:
                String phon = phone.getText().toString().trim();
                String pw = pwd.getText().toString().trim();
                boolean b = Pattern.matches("^1[3|5|7|8][0-9]{9}$", phon);
                if (b){
                    if (TextUtils.isEmpty(phon)||TextUtils.isEmpty(pw)){
                        Toast.makeText(this, "账号或密码为空,请重新输入", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        try {
                            String pwd = RsaCoder.encryptByPublicKey(pw);
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("phone",phon);
                            map.put("pwd",pwd);
                            mPresenter.postDoParams(MyUrls.BASE_LOGIN, LoginBean.class,map);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }
}
