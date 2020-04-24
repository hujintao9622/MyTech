package com.wd.tech.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.contract.TechContract;
import com.wd.tech.model.bean.information.DetailsBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.widget.MyUrls;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity<TechPresenter> implements TechContract.IView {

    @BindView(R.id.tv_details_title)
    TextView tvDetailsTitle;
    @BindView(R.id.tv_details_time)
    TextView tvDetailsTime;
    @BindView(R.id.tv_details_source)
    TextView tvDetailsSource;
    @BindView(R.id.img_details_thumbnail)
    ImageView imgDetailsThumbnail;
    @BindView(R.id.tv_details_content)
    TextView tvDetailsContent;
    @BindView(R.id.rv_details_plate)
    RecyclerView rvDetailsPlate;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 54);
        mPresenter.getDoParams(MyUrls.INFORMATION_DETAILS, DetailsBean.class, map);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof DetailsBean) {
            if (((DetailsBean) o).getStatus().equals("0000")) {
                ArrayList<DetailsBean.ResultBean.PlateBean> plateList = (ArrayList<DetailsBean.ResultBean.PlateBean>) ((DetailsBean) o).getResult().getPlate();
                tvDetailsTitle.setText(((DetailsBean) o).getResult().getTitle());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd      hh:MM");
                String time = format.format(new Date(((DetailsBean) o).getResult().getReleaseTime()));
                tvDetailsTime.setText(time);
                tvDetailsSource.setText(((DetailsBean) o).getResult().getSource());
                Glide.with(this).load(((DetailsBean) o).getResult().getThumbnail()).into(imgDetailsThumbnail);
                Document document = Jsoup.parseBodyFragment(((DetailsBean) o).getResult().getContent());
                String text = document.text();
                tvDetailsContent.setText(text);

            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
