package com.wd.tech.view.activity.info;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.community.CommunityZanBean;
import com.wd.tech.model.bean.info.GroupDetailsBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.NetUtil;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupSetActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.no_name)
    TextView noName;
    @BindView(R.id.no_num)
    TextView noNum;
    @BindView(R.id.no_chengyuan)
    RelativeLayout noChengyuan;
    @BindView(R.id.no_tongzhi)
    RelativeLayout noTongzhi;
    @BindView(R.id.no_bt)
    Button noBt;
    @BindView(R.id.no_main)
    LinearLayout noMain;
    @BindView(R.id.do_num)
    TextView doNum;
    @BindView(R.id.do_chengyuan)
    RelativeLayout doChengyuan;
    @BindView(R.id.do_name)
    TextView doName;
    @BindView(R.id.do_jianjie)
    RelativeLayout doJianjie;
    @BindView(R.id.do_tongzhi)
    RelativeLayout doTongzhi;
    @BindView(R.id.do_guanli)
    RelativeLayout doGuanli;
    @BindView(R.id.do_bt)
    Button doBt;
    @BindView(R.id.do_main)
    LinearLayout doMain;
    private SharedPreferences sp;
    private int userId;
    private int id;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        Intent intent = getIntent();
        sp = getSharedPreferences("login.dp", MODE_PRIVATE);
        userId = sp.getInt("userId", -1);
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("groupId", id);
            mPresenter.getDoParams(MyUrls.BASE_GROUP_DETAILS, GroupDetailsBean.class, map);
        }

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_group_set;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof GroupDetailsBean && TextUtils.equals("0000", ((GroupDetailsBean) o).getStatus())) {
            GroupDetailsBean.ResultBean result = ((GroupDetailsBean) o).getResult();
            int ownerUid = result.getOwnerUid();
            //是否是群主
            if (ownerUid == userId) {//是群主
                noName.setVisibility(View.GONE);
                noMain.setVisibility(View.GONE);
                doMain.setVisibility(View.VISIBLE);
                doName.setText(result.getGroupName());
                doNum.setText("共"+result.getCurrentCount()+"人");
                NetUtil.getInstance().getPhoto(result.getGroupImage(),head);
            } else {//不是群主
                noName.setVisibility(View.VISIBLE);
                noMain.setVisibility(View.VISIBLE);
                doMain.setVisibility(View.GONE);
                noName.setText(result.getGroupName());
                noNum.setText("共"+result.getCurrentCount()+"人");
                NetUtil.getInstance().getPhoto(result.getGroupImage(),head);
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
    @OnClick({R.id.comeBack, R.id.no_chengyuan, R.id.no_tongzhi, R.id.no_bt, R.id.do_chengyuan, R.id.do_name, R.id.do_jianjie, R.id.do_tongzhi, R.id.do_guanli, R.id.do_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeBack:
                finish();
                break;
            case R.id.no_chengyuan:
            case R.id.do_chengyuan:
                break;
            case R.id.no_tongzhi:
            case R.id.do_tongzhi:
                break;
            case R.id.no_bt:
                //退出群组
                HashMap<String,Object> map=new HashMap<>();
                map.put("groupId",id);
                mPresenter.dltDoParams(MyUrls.BASE_BACK_GROUP, CommunityZanBean.class,map);
                break;
            case R.id.do_name:
                break;
            case R.id.do_jianjie:
                //群简介
                Intent intent = new Intent(this,UpdateDesionActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.do_guanli:
                //群管理
                Intent guanli = new Intent(this,UpdateDesionActivity.class);
                guanli.putExtra("id",id);
                startActivity(guanli);
                break;
            case R.id.do_bt:
                //解散群
                HashMap<String,Object> map1=new HashMap<>();
                map1.put("groupId",id);
                mPresenter.dltDoParams(MyUrls.BASE_DELETE_GROUP, CommunityZanBean.class,map1);
                break;
        }
    }
}
