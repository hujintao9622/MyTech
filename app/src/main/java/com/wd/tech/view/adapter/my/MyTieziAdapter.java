package com.wd.tech.view.adapter.my;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.my.PostListBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期日/14:50
 * 功能：我的帖子
 */
public class MyTieziAdapter extends BaseQuickAdapter<PostListBean.ResultBean, BaseViewHolder> {
    public MyTieziAdapter(int layoutResId, @Nullable List<PostListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PostListBean.ResultBean item) {
        ImageView headPic = helper.getView(R.id.img_my_tie_headPic);
        Glide.with(mContext).load(item.getHeadPic()).circleCrop().into(headPic);
        helper.setText(R.id.tv_my_tie_nickName, item.getNickName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        String time = format.format(item.getPublishTime());
        helper.setText(R.id.tv_my_tie_time, time);
        helper.setText(R.id.tv_my_tie_content, item.getContent());
        ImageView file = helper.getView(R.id.img_my_tie_file);
        Glide.with(mContext).load(item.getFile()).into(file);
        helper.setText(R.id.tv_my_tie_comment, item.getComment() + "");
        helper.setText(R.id.tv_my_tie_praise, item.getPraise() + "");
    }
}
