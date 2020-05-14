package com.wd.tech.view.activity.my;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.my.NoticeListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.MyNoticeAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

// 我的通知
public class NoticeActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.rv_my_notice)
    RecyclerView rvMyNotice;

    @Override
    protected void initData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 5);
        mPresenter.getDoParams(MyUrls.NOTICE_LIST, NoticeListBean.class, map);
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
        return R.layout.activity_notice;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof NoticeListBean) {
            if (((NoticeListBean) o).getStatus().equals("0000")) {
                ArrayList<NoticeListBean.ResultBean> list = (ArrayList<NoticeListBean.ResultBean>) ((NoticeListBean) o).getResult();
                rvMyNotice.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                rvMyNotice.setAdapter(new MyNoticeAdapter(R.layout.item_my_notice, list));
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
