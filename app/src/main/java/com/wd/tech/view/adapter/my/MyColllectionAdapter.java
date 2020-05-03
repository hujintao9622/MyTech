package com.wd.tech.view.adapter.my;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.my.AllCollectionBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期日/11:55
 * 功能：用户收藏列表
 */
public class MyColllectionAdapter extends BaseQuickAdapter<AllCollectionBean.ResultBean, BaseViewHolder> {
    public MyColllectionAdapter(int layoutResId, @Nullable List<AllCollectionBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AllCollectionBean.ResultBean item) {
        ImageView imgMyThumbnail = helper.getView(R.id.img_my_thumbnail);
        Glide.with(mContext).load(item.getThumbnail()).into(imgMyThumbnail);
        helper.setText(R.id.tv_my_title, item.getTitle());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        String time = format.format(item.getCreateTime());
        helper.setText(R.id.tv_my_time, time);
    }
}
