package com.wd.tech.view.activity.information;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.community.CommunityZanBean;
import com.wd.tech.model.bean.my.PostListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.MyTieziAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//帖子
public class TieziActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.rv_my_tiezi)
    RecyclerView rvMyTiezi;
    @BindView(R.id.my_post_ok)
    TextView myPostOk;
    @BindView(R.id.my_post_no)
    TextView myPostNo;
    @BindView(R.id.my_post_del)
    LinearLayout myPostDel;
    private HashMap<String, Object> map;
    private HashMap<String, Object> map1;

    @Override
    protected void initData() {
        map1 = new HashMap<>();
        map1.put("page", 1);
        map1.put("count", 10);
        mPresenter.getDoParams(MyUrls.MY_POST_LIST, PostListBean.class, map1);
    }

    @Override
    protected void initView() {
        //隐藏标题
        getSupportActionBar().hide();
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_tiezi;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof PostListBean) {
            if (((PostListBean) o).getStatus().equals("0000")) {
                ArrayList<PostListBean.ResultBean> list = (ArrayList<PostListBean.ResultBean>) ((PostListBean) o).getResult();
                rvMyTiezi.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                MyTieziAdapter myTieziAdapter = new MyTieziAdapter(list);
                myTieziAdapter.setListener(new MyTieziAdapter.OnClickListener() {
                    @Override
                    public void onClick(int id) {
                        map = new HashMap<>();
                        map.put("communityId",id);
                        myPostDel.setVisibility(View.VISIBLE);
                    }
                });
                rvMyTiezi.setAdapter(myTieziAdapter);
            }
        }
        //删帖子
        if (o instanceof CommunityZanBean&& TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            Toast.makeText(this, ""+((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            mPresenter.getDoParams(MyUrls.MY_POST_LIST, PostListBean.class, map1);
            myPostDel.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.my_post_ok, R.id.my_post_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_post_ok:
                mPresenter.dltDoParams(MyUrls.DELETE_POST, CommunityZanBean.class,map);
                break;
            case R.id.my_post_no:
                myPostDel.setVisibility(View.GONE);
                break;
        }
    }
}
