package com.wd.tech.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.login.LoginActivity;
import com.wd.tech.view.fragment.CommunityFragment;
import com.wd.tech.view.fragment.ConsultFragment;
import com.wd.tech.view.fragment.InfoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * date:2020/4/18 0018
 * author:胡锦涛(Administrator)
 * function:主页
 */
public class MainActivity extends BaseActivity<TechPresenter> {

    List<Fragment> fglist = new ArrayList<>();
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.bgz)
    ImageView bgz;
    @BindView(R.id.headPic)
    ImageView headPic;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.dersign)
    TextView dersign;
    @BindView(R.id.shou)
    LinearLayout shou;
    @BindView(R.id.guan)
    LinearLayout guan;
    @BindView(R.id.tie)
    LinearLayout tie;
    @BindView(R.id.tong)
    LinearLayout tong;
    @BindView(R.id.ji)
    LinearLayout ji;
    @BindView(R.id.ren)
    LinearLayout ren;
    @BindView(R.id.she)
    LinearLayout she;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.login_iv)
    ImageView loginIv;
    @BindView(R.id.login)
    TextView login;

    @Override
    protected void initData() {
        //添加数据
        fglist.add(new ConsultFragment());
        fglist.add(new InfoFragment());
        fglist.add(new CommunityFragment());
        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fglist.get(position);
            }

            @Override
            public int getCount() {
                return fglist.size();
            }
        });
        //vp 联动 rg
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });
        //默认页面
        rg.check(rg.getChildAt(0).getId());
        vp.setCurrentItem(0);
        //点击登录
    }

    @Override
    protected void initView() {
        //隐藏标题
        getSupportActionBar().hide();
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
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


    @OnClick({R.id.login_iv, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_iv:
                startActivity(this, LoginActivity.class);
                break;
            case R.id.login:
                startActivity(this, LoginActivity.class);
                break;
        }
    }
}
