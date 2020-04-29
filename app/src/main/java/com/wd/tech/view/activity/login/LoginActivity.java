package com.wd.tech.view.activity.login;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.login.LoginBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.NetUtil;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.view.activity.information.MainActivity;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.et_loginPhone)
    EditText etLoginPhone;
    @BindView(R.id.et_loginPwd)
    EditText etLoginPwd;
    @BindView(R.id.img_loginEye)
    CheckBox imgLoginEye;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private SharedPreferences sp;

    @Override
    protected void initData() {
        imgLoginEye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    // 如果选中，显示密码
                    imgLoginEye.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else
                    // 否则隐藏密码
                    imgLoginEye.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }

    @Override
    protected void initView() {
        //隐藏标题栏
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
        if (o instanceof LoginBean && TextUtils.equals("0000", ((LoginBean) o).getStatus())) {
            LoginBean.ResultBean resultBean = ((LoginBean) o).getResult();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("headPic", resultBean.getHeadPic());
            editor.putString("nickName", resultBean.getNickName());
            editor.putInt("userId", resultBean.getUserId());
            editor.putString("sessionId", resultBean.getSessionId());
            editor.putBoolean("b", true);
            editor.commit();
            startActivity(LoginActivity.this,MainActivity.class);
            finish();
        }
    }

    @Override
    public void onFailure(Throwable e) {
        Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.tv_register, R.id.btn_login,R.id.wx,R.id.face})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register://注册
                startActivity(this, RegisterActivity.class);
                break;
            case R.id.btn_login:
                String phone = etLoginPhone.getText().toString().trim();
                String pwd = etLoginPwd.getText().toString().trim();
                String rsaPwd = null;
                try {
                    rsaPwd = RsaCoder.encryptByPublicKey(pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (NetUtil.isMobileNO(phone)) {
                    // 使用RSA公钥对密码加密
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("phone", phone);
                    map.put("pwd", rsaPwd);
                    mPresenter.postDoParams(MyUrls.BASE_LOGIN, LoginBean.class, map);
                } else {
                    Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.wx:
                boolean b = MyApp.mWxApi.isWXAppInstalled();
                Log.e("code",b+"");
                if (b){
                    //发起微信请求
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state="wx_login";
                    MyApp.mWxApi.sendReq(req);
                }else {
                    Toast.makeText(this, "请先安装微信", Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
                break;
            case R.id.face:

                break;
        }
    }
}
