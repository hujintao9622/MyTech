package com.wd.tech.view.activity.information;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.my.PostListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.MyTieziAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TieziActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.rv_my_tiezi)
    RecyclerView rvMyTiezi;

    @Override
    protected void initData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 5);
        mPresenter.getDoParams(MyUrls.MY_POST_LIST, PostListBean.class, map);
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
        return R.layout.activity_tiezi;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof PostListBean) {
            if (((PostListBean) o).getStatus().equals("0000")) {
                ArrayList<PostListBean.ResultBean> list = (ArrayList<PostListBean.ResultBean>) ((PostListBean) o).getResult();
                rvMyTiezi.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                rvMyTiezi.setAdapter(new MyTieziAdapter(R.layout.item_my_tiezi, list));
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
