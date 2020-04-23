package com.wd.tech.view.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.fragment.info.InfoItFragment;
import com.wd.tech.view.fragment.info.LinkManFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date:2020/4/19 0019
 * author:胡锦涛(Administrator)
 * function:消息
 */
public class InfoFragment extends BaseFragment<TechPresenter> {
    List<Fragment> fglist = new ArrayList<>();
    @BindView(R.id.info_rg)
    RadioGroup infoRg;
    @BindView(R.id.jia)
    ImageView jia;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected void initView(View view) {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initData() {
        fglist.clear();
        fglist.add(new InfoItFragment());
        fglist.add(new LinkManFragment());
        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
        infoRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.info_rb1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.info_rb2:
                        vp.setCurrentItem(1);
                        break;
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                infoRg.check(infoRg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.jia)
    public void onViewClicked() {
    }
}
