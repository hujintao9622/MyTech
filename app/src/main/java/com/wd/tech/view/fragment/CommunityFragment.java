package com.wd.tech.view.fragment;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.model.bean.community.CommunityListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.commuity.CommuityAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * date:2020/4/19 0019
 * author:胡锦涛(Administrator)
 * function:社区
 */
public class CommunityFragment extends BaseFragment<TechPresenter> {

    @BindView(R.id.com_rc)
    RecyclerView comRc;
    private int page = 1;

    @Override
    protected void initView(View view) {
        comRc.setLayoutManager(new LinearLayoutManager(getContext()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("count", 10);
        mPresenter.getDoParams(MyUrls.BASE_COMMUNITYLIST, CommunityListBean.class, map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof CommunityListBean && TextUtils.equals("0000", ((CommunityListBean) o).getStatus())) {
            List<CommunityListBean.ResultBean> result = ((CommunityListBean) o).getResult();
            CommuityAdapter adapter = new CommuityAdapter(result);
            comRc.setAdapter(adapter);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
