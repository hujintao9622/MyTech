package com.wd.tech.view.adapter.my;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.my.FollowListBean;

import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期日/22:44
 * 功能：关注列表
 */
public class FollowAdapter extends BaseQuickAdapter<FollowListBean.ResultBean, BaseViewHolder> {

    public FollowAdapter(int layoutResId, @Nullable List<FollowListBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FollowListBean.ResultBean item) {
        ImageView pic = helper.getView(R.id.img_my_follow_pic);
        Glide.with(mContext).load(item.getHeadPic()).circleCrop().into(pic);
        helper.setText(R.id.tv_my_follow_name, item.getNickName());
        if (!(item.getSignature() == null)) {
            helper.setText(R.id.tv_my_follow_signature, item.getSignature());
        } else {
            TextView signature = helper.getView(R.id.tv_my_follow_signature);
            signature.setVisibility(View.GONE);
        }
    }
}
