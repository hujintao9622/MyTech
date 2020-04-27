package com.wd.tech.view.activity.community;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.community.CommUserBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.NetUtil;
import com.wd.tech.view.adapter.commuity.CommUserAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommUserActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.desion)
    TextView desion;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.rc)
    RecyclerView rc;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        rc.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        if (intent != null) {
            int uid = intent.getIntExtra("uid", -1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("fromUid",uid);
            map.put("page",1);
            map.put("count",10);
            mPresenter.getDoParams(MyUrls.BASE_USER_COM, CommUserBean.class,map);
        }
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_comm_user;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof CommUserBean&& TextUtils.equals("0000",((CommUserBean) o).getStatus())){
            List<CommUserBean.ResultBean> result = ((CommUserBean) o).getResult();
            CommUserBean.ResultBean.CommunityUserVoBean communityUserVo = result.get(0).getCommunityUserVo();
            String headPic = communityUserVo.getHeadPic();
            Glide.with(ivTitle).load(headPic).into(ivTitle);
            NetUtil.getInstance().getCiclePhoto(headPic,ivHead);
            name.setText(communityUserVo.getNickName());
            desion.setText(communityUserVo.getSignature());
            CommUserAdapter adapter = new CommUserAdapter(result.get(0).getCommunityUserPostVoList());
            rc.setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.iv_head, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.more:
                break;
        }
    }
}
