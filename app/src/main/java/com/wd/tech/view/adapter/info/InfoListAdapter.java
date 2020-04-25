package com.wd.tech.view.adapter.info;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.information.DetailsBean;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期五/11:47
 * 功能：推荐
 */
public class InfoListAdapter extends BaseQuickAdapter<DetailsBean.ResultBean.InformationListBean, BaseViewHolder> {
    public InfoListAdapter(int layoutResId, @Nullable List<DetailsBean.ResultBean.InformationListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, DetailsBean.ResultBean.InformationListBean item) {
        ImageView thumbnail = helper.getView(R.id.img_details_list_thumbnail);
        Glide.with(mContext).load(item.getThumbnail()).into(thumbnail);
        helper.setText(R.id.tv_details_list_title, item.getTitle());
    }
}
