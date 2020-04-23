package com.wd.tech.view.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.RecommendBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期三/22:04
 * 功能：资讯展示列表适配器
 */
public class RecommendAdapter extends BaseQuickAdapter<RecommendBean.ResultBean, BaseViewHolder> {
    public RecommendAdapter(int layoutResId, @Nullable List<RecommendBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RecommendBean.ResultBean item) {
        ImageView thumbnail = helper.getView(R.id.img_recommend_thumbnail);
        Glide.with(mContext).load(item.getThumbnail()).into(thumbnail);
        helper.setText(R.id.tv_recommend_title, item.getTitle());
        helper.setText(R.id.tv_recommend_summary, item.getSummary());
        helper.setText(R.id.tv_recommend_source, item.getSource());
        SimpleDateFormat format = new SimpleDateFormat("mm");
        String timeString = format.format(new Date(item.getReleaseTime()));
        helper.setText(R.id.tv_recommend_time, timeString + "分钟前");
    }
}
