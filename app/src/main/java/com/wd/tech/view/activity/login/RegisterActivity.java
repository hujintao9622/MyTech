package com.wd.tech.view.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.LoginBean;
import com.wd.tech.model.bean.RegisterBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//注册
public class RegisterActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_pwd)
    EditText registerPwd;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.register_bt)
    Button registerBt;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //隐藏标题栏
        getSupportActionBar().hide();
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof RegisterBean&& TextUtils.equals("0000",((RegisterBean) o).getStatus())){
            Toast.makeText(this, ((RegisterBean) o).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.register_bt)
    public void onViewClicked() {
        String name = registerName.getText().toString().trim();
        String phone = registerPhone.getText().toString().trim();
        String pw = registerPwd.getText().toString().trim();
        boolean b = Pattern.matches("^1[3|5|7|8][0-9]{9}$", phone);
        if (b){
            if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pw)||TextUtils.isEmpty(name)){
                Toast.makeText(this, "昵称/账号或密码为空,请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }else {
                try {
                    String pwd = RsaCoder.encryptByPublicKey(pw);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("nickName",name);
                    map.put("phone",phone);
                    map.put("pwd",pwd);
                    mPresenter.postDoParams(MyUrls.BASE_REGISTER, RegisterBean.class,map);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else {
            Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
