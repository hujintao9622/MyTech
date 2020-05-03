package com.wd.tech.view.activity.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.my.InfoByUserIdBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.information.MainActivity;
import com.wd.tech.widget.MyUrls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.img_she_headPic)
    ImageView imgSheHeadPic;
    @BindView(R.id.tv_she_nickName)
    TextView tvSheNickName;
    @BindView(R.id.tv_she_phone)
    TextView tvShePhone;
    @BindView(R.id.tv_she_integral)
    TextView tvSheIntegral;
    @BindView(R.id.tv_she_whetherVip)
    TextView tvSheWhetherVip;
    private SharedPreferences sp;

    @BindView(R.id.she_login)
    TextView sheLogin;

    @Override
    protected void initData() {
        mPresenter.getNoParams(MyUrls.INFO_BY_USER_ID, InfoByUserIdBean.class);
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
        if (o instanceof InfoByUserIdBean) {
            if (((InfoByUserIdBean) o).getStatus().equals("0000")) {
                Glide.with(this).load(((InfoByUserIdBean) o).getResult().getHeadPic()).into(imgSheHeadPic);
                tvSheNickName.setText(((InfoByUserIdBean) o).getResult().getNickName());
                tvShePhone.setText(((InfoByUserIdBean) o).getResult().getPhone());
                tvSheIntegral.setText(((InfoByUserIdBean) o).getResult().getIntegral() + "");
                if (((InfoByUserIdBean) o).getResult().getWhetherVip() == 1) {
                    tvSheWhetherVip.setText("是");
                } else if (((InfoByUserIdBean) o).getResult().getWhetherVip() == 2) {
                    tvSheWhetherVip.setText("否");
                }
            }
        }
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
