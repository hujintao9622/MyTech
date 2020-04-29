package com.wd.tech.view.activity.information;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wd.tech.R;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.information.VipListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.info.VipListAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VipActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.rv_vip)
    RecyclerView rvVip;
    @BindView(R.id.tv_vip_list_price)
    TextView tvVipListPrice;

    @Override
    protected void initData() {
        mPresenter.getNoParams(MyUrls.FIND_VIP_LIST, VipListBean.class);
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
        return R.layout.activity_vip;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof VipListBean) {
            if (((VipListBean) o).getStatus().equals("0000")) {
                ArrayList<VipListBean.ResultBean> list = (ArrayList<VipListBean.ResultBean>) ((VipListBean) o).getResult();
                rvVip.setLayoutManager(new GridLayoutManager(this, 2));
                ;
                VipListAdapter vipListAdapter = new VipListAdapter(R.layout.item_vip_list, list);
                rvVip.setAdapter(vipListAdapter);
                vipListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        tvVipListPrice.setText(list.get(position).getPrice() + "");
                    }
                });
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
