package com.wd.tech.view.activity.my;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.my.IntegralRecordBean;
import com.wd.tech.model.bean.my.UserIntegralBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.MyIntegralAdapter;
import com.wd.tech.widget.MyUrls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyIntegralActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.tv_my_integral_amount)
    TextView tvMyIntegralAmount;
    @BindView(R.id.tv_my_integral_time)
    TextView tvMyIntegralTime;
    @BindView(R.id.rv_my_Integral_list)
    RecyclerView rvMyIntegralList;

    @Override
    protected void initData() {
        mPresenter.getNoParams(MyUrls.FIND_USER_INTEGRAL, UserIntegralBean.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 5);
        mPresenter.getDoParams(MyUrls.FIND_USER_INTEGRAL_RECORD, IntegralRecordBean.class, map);
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
        return R.layout.activity_my_integral;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof UserIntegralBean) {
            if (((UserIntegralBean) o).getStatus().equals("0000")) {
                tvMyIntegralAmount.setText(((UserIntegralBean) o).getResult().getAmount() + "");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
                String time = format.format(((UserIntegralBean) o).getResult().getUpdateTime());
                tvMyIntegralTime.setText(time);
            }
        }
        if (o instanceof IntegralRecordBean) {
            if (((IntegralRecordBean) o).getStatus().equals("0000")) {
                ArrayList<IntegralRecordBean.ResultBean> list = (ArrayList<IntegralRecordBean.ResultBean>) ((IntegralRecordBean) o).getResult();
                rvMyIntegralList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                rvMyIntegralList.setAdapter(new MyIntegralAdapter(R.layout.item_integral_list , list));
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
