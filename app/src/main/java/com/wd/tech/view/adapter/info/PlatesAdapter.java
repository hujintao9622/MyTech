package com.wd.tech.view.adapter.info;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.information.PlatesBean;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期二/15:20
 * 功能：所有版块查询
 */
public class PlatesAdapter extends BaseQuickAdapter<PlatesBean.ResultBean, BaseViewHolder> {
    public PlatesAdapter(int layoutResId, @Nullable List<PlatesBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PlatesBean.ResultBean item) {
        ImageView imgPlatesPic = helper.getView(R.id.img_plates_pic);
        Glide.with(mContext).load(item.getPic()).into(imgPlatesPic);
        helper.setText(R.id.tv_plates_name, item.getName());
    }
}
