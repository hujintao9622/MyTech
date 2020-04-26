package com.wd.tech.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.contract.TechContract;
import com.wd.tech.model.bean.information.IntegralBean;
import com.wd.tech.model.bean.information.PayIntegralBean;
import com.wd.tech.model.bean.login.RegisterBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.widget.MyUrls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntegralActivity extends BaseActivity<TechPresenter> implements TechContract.IView {

    @BindView(R.id.img_integral_thumbnail)
    ImageView imgIntegralThumbnail;
    @BindView(R.id.tv_integral_title)
    TextView tvIntegralTitle;
    @BindView(R.id.tv_integral_summary)
    TextView tvIntegralSummary;
    @BindView(R.id.tv_integral_source)
    TextView tvIntegralSource;
    @BindView(R.id.tv_integral_time)
    TextView tvIntegralTime;
    @BindView(R.id.tv_integral_collection)
    TextView tvIntegralCollection;
    @BindView(R.id.tv_integral_share)
    TextView tvIntegralShare;
    @BindView(R.id.tv_integral_cost)
    TextView tvIntegralCost;
    @BindView(R.id.tv_integral_costs)
    TextView tvIntegralCosts;
    @BindView(R.id.btn_integral_dui)
    Button btnIntegralDui;
    private int amount;
    private int yuanCost;
    private int id;

    @Override
    protected void initData() {
        mPresenter.getNoParams(MyUrls.USER_INTEGRAL, IntegralBean.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("infoId", id);
        map.put("integralCost", yuanCost);
        mPresenter.postDoParams(MyUrls.PAY_INTEGRAL, RegisterBean.class, map);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String thumbnail = intent.getStringExtra("thumbnail");
        String title = intent.getStringExtra("title");
        String summary = intent.getStringExtra("summary");
        String source = intent.getStringExtra("source");
        long time = intent.getLongExtra("time", 0);
        int praise = intent.getIntExtra("praise", 0);
        int share = intent.getIntExtra("share", 0);
        yuanCost = intent.getIntExtra("yuanCost", 0);
        id = intent.getIntExtra("id", 0);
        Glide.with(this).load(thumbnail).into(imgIntegralThumbnail);
        tvIntegralTitle.setText(title);
        tvIntegralSummary.setText(summary);
        tvIntegralSource.setText(source);
        SimpleDateFormat format = new SimpleDateFormat("mm");
        String timeString = format.format(new Date(time));
        tvIntegralTime.setText(timeString + "分钟之前");
        tvIntegralCollection.setText(praise + "");
        tvIntegralShare.setText(share + "");
        tvIntegralCost.setText(yuanCost + "");
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_integral;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof IntegralBean) {
            if (((IntegralBean) o).getStatus().equals("0000")) {
                amount = ((IntegralBean) o).getResult().getAmount();
                tvIntegralCosts.setText(((IntegralBean) o).getResult().getAmount() + "");
            }
        }
        if (o instanceof PayIntegralBean) {
            if (((PayIntegralBean) o).getStatus().equals("0000")) {
                onViewClicked();
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.btn_integral_dui)
    public void onViewClicked() {
        if (amount < yuanCost) {
            Toast.makeText(this, "积分不足", Toast.LENGTH_SHORT).show();
        } else if (yuanCost == 0) {
            Toast.makeText(this, "免费资讯，无需兑换", Toast.LENGTH_SHORT).show();
        } else if (amount > yuanCost) {
            // 通过Builder构建器来构造
            // 下面的参数上下文 对话框里面一般都用this
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("兑换成功");
            builder.setMessage("您已兑换该条资讯的阅读权");
            // 设置确定按钮
            builder.setPositiveButton("继续阅读", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.e("dialog", "点击了确定按钮！");
                }
            });
            // 和Toast一样 最后一定要show 出来
            builder.show();
        }
    }
}
