package com.wd.tech.view.activity.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.information.PlatesBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.info.PlatesAdapter;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlatesActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.rv_plates)
    RecyclerView rvPlates;

    @Override
    protected void initData() {
        mPresenter.getNoParams(MyUrls.ALL_INFO_PLATE, PlatesBean.class);
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
        return R.layout.activity_plates;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof PlatesBean) {
            if (((PlatesBean) o).getStatus().equals("0000")) {
                ArrayList<PlatesBean.ResultBean> list = (ArrayList<PlatesBean.ResultBean>) ((PlatesBean) o).getResult();
                rvPlates.setLayoutManager(new GridLayoutManager(this, 2));
                PlatesAdapter platesAdapter = new PlatesAdapter(R.layout.item_plates, list);
                rvPlates.setAdapter(platesAdapter);
                platesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int id = ((PlatesBean) o).getResult().get(position).getId();
                        String name = ((PlatesBean) o).getResult().get(position).getName();
                        Intent intent = new Intent(MyApp.getmContext(), PlateActivity.class);
                        intent.putExtra("plateId", id);
                        intent.putExtra("plateName", name);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
