package com.wd.tech.view.activity.information;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.information.CommentBean;
import com.wd.tech.model.bean.information.DetailsBean;
import com.wd.tech.model.bean.information.PingBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.info.CommentAdapter;
import com.wd.tech.view.adapter.info.InfoListAdapter;
import com.wd.tech.view.adapter.info.PlateAdapter;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseHuaActivity<TechPresenter> {

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
    @BindView(R.id.rv_details_information_list)
    RecyclerView rvDetailsInformationList;
    @BindView(R.id.rv_info_comment)
    RecyclerView rvInfoComment;
    @BindView(R.id.img_no_pay)
    ImageView imgNoPay;
    @BindView(R.id.btn_no_pay)
    Button btnNoPay;
    @BindView(R.id.ed_details_ping)
    EditText edDetailsPing;
    private PopupWindow mPopupWindow;
    private View contentView;
    private ImageView imgPop;
    private TextView tvIntegral;
    private TextView tvVip;
    private DetailsBean.ResultBean result;
    private int id;

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        // 资讯详情
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        mPresenter.getDoParams(MyUrls.INFORMATION_DETAILS, DetailsBean.class, map);
        // 资讯评论
        HashMap<String, Object> params = new HashMap<>();
        params.put("infoId", id);
        params.put("page", 1);
        params.put("count", 5);
        mPresenter.getDoParams(MyUrls.INFORMATION_COMMENT, CommentBean.class, params);
    }

    @Override
    protected void initData() {
        edDetailsPing.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    String ping = edDetailsPing.getText().toString();
                    HashMap<String, Object> params = new HashMap<>();
                    params.put("infoId", id);
                    params.put("content", ping);
                    mPresenter.postDoParams(MyUrls.ADD_COMMENT, PingBean.class, params);
                    edDetailsPing.setText("");
                }
                return true;
            }
        });
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
        if (o instanceof PingBean) {
            if (((PingBean) o).getStatus().equals("0000")) {
                Toast.makeText(this, ((PingBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (o instanceof DetailsBean) {
            if (((DetailsBean) o).getStatus().equals("0000")) {
                result = ((DetailsBean) o).getResult();
                // 所属板块
                ArrayList<DetailsBean.ResultBean.PlateBean> plateList = (ArrayList<DetailsBean.ResultBean.PlateBean>) ((DetailsBean) o).getResult().getPlate();
                // 推荐
                ArrayList<DetailsBean.ResultBean.InformationListBean> infoList = (ArrayList<DetailsBean.ResultBean.InformationListBean>) ((DetailsBean) o).getResult().getInformationList();
                tvDetailsTitle.setText(((DetailsBean) o).getResult().getTitle());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd      hh:MM");
                String time = format.format(new Date(((DetailsBean) o).getResult().getReleaseTime()));
                tvDetailsTime.setText(time);
                tvDetailsSource.setText(((DetailsBean) o).getResult().getSource());
                Glide.with(this).load(((DetailsBean) o).getResult().getThumbnail()).into(imgDetailsThumbnail);
                if (!TextUtils.isEmpty(((DetailsBean) o).getResult().getContent())) {
                    if (((DetailsBean) o).getResult().getReadPower() == 2) {
                        // 有权限查看
                        Document document = Jsoup.parseBodyFragment(((DetailsBean) o).getResult().getContent());
                        String text = document.text();
                        tvDetailsContent.setText(text);
                        imgNoPay.setVisibility(View.GONE);
                        btnNoPay.setVisibility(View.GONE);
                    } else if (((DetailsBean) o).getResult().getReadPower() == 1) {
                        // 没有权限查看
                        tvDetailsContent.setMaxLines(12);
                        tvDetailsContent.setEllipsize(TextUtils.TruncateAt.END);
                        imgNoPay.setVisibility(View.VISIBLE);
                        btnNoPay.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvDetailsContent.setVisibility(View.GONE);
                    imgNoPay.setVisibility(View.GONE);
                    btnNoPay.setVisibility(View.GONE);
                }
                if (!(((DetailsBean) o).getResult().getPlate() == null)) {
                    rvDetailsPlate.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                    rvDetailsPlate.setAdapter(new PlateAdapter(R.layout.item_details_plate, plateList));
                } else {
                    rvDetailsPlate.setVisibility(View.GONE);
                }
                if (!(((DetailsBean) o).getResult().getInformationList() == null)) {
                    rvDetailsInformationList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
                    rvDetailsInformationList.setAdapter(new InfoListAdapter(R.layout.item_details_info_list, infoList));
                } else {
                    rvDetailsInformationList.setVisibility(View.GONE);
                }
            }
        }
        if (o instanceof CommentBean) {
            if (((CommentBean) o).getStatus().equals("0000")) {
                // 资讯评论
                ArrayList<CommentBean.ResultBean> commentList = (ArrayList<CommentBean.ResultBean>) ((CommentBean) o).getResult();
                rvInfoComment.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                rvInfoComment.setAdapter(new CommentAdapter(this, commentList));
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    public void noPay(View view) {
        showPopupWindow();
    }

    private void showPopupWindow() {
        // 设置 contentView
        contentView = LayoutInflater.from(this).inflate(R.layout.popuplayout, null);
        mPopupWindow = new PopupWindow(contentView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setOutsideTouchable(true);
        // 设置
        imgPop = contentView.findViewById(R.id.img_pop);
        tvIntegral = contentView.findViewById(R.id.tv_pop_pay_integral);
        tvVip = contentView.findViewById(R.id.tv_pop_pay_vip);
        ShowPopWindow();
        InitClick();
    }

    private void InitClick() {
        imgPop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        tvIntegral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApp.getmContext(), IntegralActivity.class);
                intent.putExtra("thumbnail", result.getThumbnail());
                intent.putExtra("title", result.getTitle());
                intent.putExtra("summary", result.getSummary());
                intent.putExtra("source", result.getSource());
                intent.putExtra("time", result.getReleaseTime());
                intent.putExtra("praise", result.getPraise());
                intent.putExtra("share", result.getShare());
                intent.putExtra("yuanCost", 1);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        tvVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MyApp.getmContext(), VipActivity.class);
                startActivity(intent1);
            }
        });
    }

    @SuppressLint("NewApi")
    public void ShowPopWindow() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
    }
}
