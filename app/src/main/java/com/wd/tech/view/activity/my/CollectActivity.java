package com.wd.tech.view.activity.my;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.my.AllCollectionBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.MyColllectionAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.rv_my_collection)
    RecyclerView rvMyCollection;

    @Override
    protected void initData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 5);
        mPresenter.getDoParams(MyUrls.ALL_INFO_COLLECTION, AllCollectionBean.class, map);
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
        return R.layout.activity_collect;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof AllCollectionBean) {
            if (((AllCollectionBean) o).getStatus().equals("0000")) {
                ArrayList<AllCollectionBean.ResultBean> list = (ArrayList<AllCollectionBean.ResultBean>) ((AllCollectionBean) o).getResult();
                rvMyCollection.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                rvMyCollection.setAdapter(new MyColllectionAdapter(R.layout.item_my_collection, list));
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
