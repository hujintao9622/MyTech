package com.wd.tech.view.adapter.my;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.my.NoticeListBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期四/11:17
 * 功能：我的通知
 */
public class MyNoticeAdapter extends BaseQuickAdapter<NoticeListBean.ResultBean, BaseViewHolder> {
    public MyNoticeAdapter(int layoutResId, @Nullable List<NoticeListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NoticeListBean.ResultBean item) {
        ImageView noticePic = helper.getView(R.id.img_my_notice_pic);
        Glide.with(mContext).load(item.getFromHeadPic()).into(noticePic);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        String time = format.format(item.getNoticeTime());
        helper.setText(R.id.tv_my_notice_time, time);
        helper.setText(R.id.tv_my_notice_name, item.getFromNickName());
        helper.setText(R.id.tv_my_notice_remark, item.getRemark());
    }
}
