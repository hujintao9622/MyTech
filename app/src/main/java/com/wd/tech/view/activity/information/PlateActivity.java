package com.wd.tech.view.activity.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.information.RecommendBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.RecommendAdapter;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlateActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.tv_plate_model)
    TextView tvPlateModel;
    @BindView(R.id.rv_plate)
    RecyclerView rvPlate;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int plateId = intent.getIntExtra("plateId", 1);
        String plateName = intent.getStringExtra("plateName");
        tvPlateModel.setText(plateName);
        HashMap<String, Object> map = new HashMap<>();
        map.put("plateId", plateId);
        map.put("page", "1");
        map.put("count", "10");
        // 资讯展示列表
        mPresenter.getDoParams(MyUrls.INFO_RECOMMEND, RecommendBean.class, map);
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
        return R.layout.activity_plate;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof RecommendBean) {
            if (((RecommendBean) o).getStatus().equals("0000")) {
                ArrayList<RecommendBean.ResultBean> recommendList = (ArrayList<RecommendBean.ResultBean>) ((RecommendBean) o).getResult();
                rvPlate.setLayoutManager(new LinearLayoutManager(MyApp.mContext, RecyclerView.VERTICAL, false));
                RecommendAdapter recommendAdapter = new RecommendAdapter(R.layout.item_recommend, recommendList);
                rvPlate.setAdapter(recommendAdapter);
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
