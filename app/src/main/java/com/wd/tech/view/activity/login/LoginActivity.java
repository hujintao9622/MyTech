package com.wd.tech.view.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.LoginBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.RsaCoder;
import com.wd.tech.view.activity.MainActivity;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.et_loginPhone)
    EditText etLoginPhone;
    @BindView(R.id.et_loginPwd)
    EditText etLoginPwd;
    @BindView(R.id.img_loginEye)
    CheckBox imgLoginEye;
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
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("login", true);
            startActivity(intent);
            finish();
        } else
            Toast.makeText(this, ((LoginBean) o).getMessage(), Toast.LENGTH_SHORT).show();
    }

    // 手机号正则表达式
    public static boolean isMobileNO(String mobileNums) {
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    @Override
    public void onFailure(Throwable e) {
        Toast.makeText(this, e + "", Toast.LENGTH_SHORT).show();
    }

    // 登录监听
    public void login(View view) throws Exception {
        String phone = etLoginPhone.getText().toString().trim();
        String pwd = etLoginPwd.getText().toString().trim();
        String rsaPwd = RsaCoder.encryptByPublicKey(pwd);
        if (isMobileNO(phone)) {
            // 使用RSA公钥对密码加密
            HashMap<String, Object> map = new HashMap<>();
            map.put("phone", phone);
            map.put("pwd", rsaPwd);
            mPresenter.postDoParams(MyUrls.BASE_LOGIN, LoginBean.class, map);
        } else {
            Toast.makeText(this, "格式不正确", Toast.LENGTH_SHORT).show();
        }

    }

    // 注册监听
    public void register(View view) {
        startActivity(this, RegisterActivity.class);
    }
}
