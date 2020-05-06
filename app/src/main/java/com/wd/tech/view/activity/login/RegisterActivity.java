package com.wd.tech.view.activity.login;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.login.RegisterBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.NetUtil;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.view.activity.information.MainActivity;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

//注册
public class RegisterActivity extends BaseHuaActivity<TechPresenter> {

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
    private String name;
    private String phone;

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
            //注册极光
            JMessageClient.register(phone, MyApp.s1, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    switch (i) {
                        case 0:
                            Toast.makeText(RegisterActivity.this, "极光注册成功", Toast.LENGTH_SHORT).show();
                            break;
                        case 898001:
                            Toast.makeText(RegisterActivity.this, "极光用户名已存在", Toast.LENGTH_SHORT).show();
                            break;
                        case 871301:
                            Toast.makeText(RegisterActivity.this, "极光密码格式错误", Toast.LENGTH_SHORT).show();
                            break;
                        case 871304:
                            Toast.makeText(RegisterActivity.this, "极光密码错误", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
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
        name = registerName.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "昵称为空,请输入昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        phone = registerPhone.getText().toString().trim();
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
            map.put("nickName", name);
            map.put("phone", phone);
            map.put("pwd",pwd);
            mPresenter.postDoParams(MyUrls.BASE_REGISTER, RegisterBean.class,map);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
