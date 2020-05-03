package com.wd.tech.view.adapter.my;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.my.IntegralRecordBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期日/16:16
 * 功能：查询用户积分明细
 */
public class MyIntegralAdapter extends BaseQuickAdapter<IntegralRecordBean.ResultBean, BaseViewHolder> {
    public MyIntegralAdapter(int layoutResId, @Nullable List<IntegralRecordBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, IntegralRecordBean.ResultBean item) {
        if (item.getType() == 1) {
            helper.setText(R.id.tv_my_integral_title, "签到成功");
        } else if (item.getType() == 6) {
            helper.setText(R.id.tv_my_integral_title, "付费资讯");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        String time = format.format(item.getCreateTime());
        helper.setText(R.id.tv_integral_list_time, time);
        if (item.getAmount() > 0) {
            helper.setText(R.id.tv_integral_list_amount, "+" + item.getAmount());
        } else {
            helper.setText(R.id.tv_integral_list_amount, "-" + item.getAmount());
        }

    }
}
