package com.wd.tech.view.adapter.info;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.information.VipListBean;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期三/23:55
 * 功能：查询所有会员商品
 */
public class VipListAdapter extends BaseQuickAdapter<VipListBean.ResultBean, BaseViewHolder> {
    public VipListAdapter(int layoutResId, @Nullable List<VipListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, VipListBean.ResultBean item) {
        helper.setText(R.id.tv_vip_commodity_name, item.getCommodityName());
    }
}
