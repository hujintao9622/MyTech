package com.wd.tech.view.adapter.info;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.information.DetailsBean;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期五/11:04
 * 功能：所属板块
 */
public class PlateAdapter extends BaseQuickAdapter<DetailsBean.ResultBean.PlateBean, BaseViewHolder> {
    public PlateAdapter(int layoutResId, @Nullable List<DetailsBean.ResultBean.PlateBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DetailsBean.ResultBean.PlateBean item) {
        helper.setText(R.id.tv_plate_name, item.getName());
    }
}
