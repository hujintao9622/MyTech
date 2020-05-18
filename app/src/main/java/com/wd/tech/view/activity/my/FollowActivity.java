package com.wd.tech.view.activity.my;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.my.FollowListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.my.FollowAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;

// 我的关注
public class FollowActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.rv_my_follow)
    RecyclerView rvMyFollow;

    @Override
    protected void initData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 5);
        mPresenter.getDoParams(MyUrls.FOLLOW_LIST, FollowListBean.class, map);
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
        return R.layout.activity_follow;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof FollowListBean) {
            if (((FollowListBean) o).getStatus().equals("0000")) {
                ArrayList<FollowListBean.ResultBean> list = (ArrayList<FollowListBean.ResultBean>) ((FollowListBean) o).getResult();
                rvMyFollow.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                rvMyFollow.setAdapter(new FollowAdapter(R.layout.item_my_follow, list));
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
