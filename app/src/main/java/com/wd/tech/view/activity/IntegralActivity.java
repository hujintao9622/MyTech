package com.wd.tech.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.contract.TechContract;
import com.wd.tech.model.bean.information.IntegralBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void initData() {
        mPresenter.getNoParams(MyUrls.USER_INTEGRAL, IntegralBean.class);
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
        int yuanCost = intent.getIntExtra("yuanCost", 0);
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
                Toast.makeText(this, ((IntegralBean) o).getResult().getAmount() + "", Toast.LENGTH_SHORT).show();
                tvIntegralCosts.setText(((IntegralBean) o).getResult().getAmount() + "");
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
