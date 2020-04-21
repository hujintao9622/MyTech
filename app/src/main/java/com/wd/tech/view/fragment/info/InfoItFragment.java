package com.wd.tech.view.fragment.info;

import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.model.bean.info.FriendNoticeBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.InfoItAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * date:2020/4/21 0021
 * author:胡锦涛(Administrator)
 * function:
 */
public class InfoItFragment extends BaseFragment<TechPresenter> {
    @BindView(R.id.ifit_rc)
    RecyclerView ifitRc;
    private int page=1;
    @Override
    protected void initView(View view) {
        ifitRc.setLayoutManager(new LinearLayoutManager(getContext()));
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("count",10);
        mPresenter.getDoParams(MyUrls.BASE_FRIEND_NOTICE,FriendNoticeBean.class,map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_infoit;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof FriendNoticeBean&& TextUtils.equals("0000",((FriendNoticeBean) o).getStatus())){
            if (((FriendNoticeBean) o).getResult().size()>0){
                List<FriendNoticeBean.ResultBean> result = ((FriendNoticeBean) o).getResult();
                InfoItAdapter infoItAdapter = new InfoItAdapter(result);
                ifitRc.setAdapter(infoItAdapter);
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
