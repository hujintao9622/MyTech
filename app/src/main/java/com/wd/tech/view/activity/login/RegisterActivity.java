package com.wd.tech.view.activity.login;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.login.RegisterBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.NetUtil;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

//注册
public class RegisterActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_pwd)
    EditText registerPwd;
    @BindView(R.id.sms_register)
    TextView smsRegister;
    @BindView(R.id.register_bt)
    Button registerBt;

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
        return R.layout.activity_register;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof RegisterBean&&TextUtils.equals("0000",((RegisterBean) o).getStatus())){
            Toast.makeText(this, ((RegisterBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(this,LoginActivity.class);
            finish();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.register_bt)
    public void onViewClicked() {
        String name = registerName.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "昵称为空,请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        String phone = registerPhone.getText().toString().trim();
        String pw = registerPwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pw)){
            Toast.makeText(this, "手机号或密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (NetUtil.isMobileNO(phone)){
            Toast.makeText(this, "手机号不合法", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            String pwd = RsaCoder.encryptByPublicKey(pw);
            HashMap<String, Object> map=new HashMap<>();
            map.put("nickName",name);
            map.put("phone",phone);
            map.put("pwd",pwd);
            mPresenter.postDoParams(MyUrls.BASE_REGISTER, RegisterBean.class,map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
