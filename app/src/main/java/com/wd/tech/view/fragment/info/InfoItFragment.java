package com.wd.tech.view.fragment.info;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.model.bean.community.CommunityZanBean;
import com.wd.tech.model.bean.info.FriendListBean;
import com.wd.tech.model.bean.info.FriendNoticeBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.info.ChatMsgActivity;
import com.wd.tech.view.activity.info.FriendNoticeActivity;
import com.wd.tech.view.adapter.InfoItAdapter;
import com.wd.tech.widget.MyUrls;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

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
    SwipeMenuRecyclerView ifitRc;
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
        if (o instanceof FriendNoticeBean&&TextUtils.equals("0000",((FriendNoticeBean) o).getStatus())){
            if (((FriendNoticeBean) o).getResult().size()>0){
                List<FriendNoticeBean.ResultBean> result = ((FriendNoticeBean) o).getResult();
                InfoItAdapter infoItAdapter = new InfoItAdapter(result);
                infoItAdapter.setOnClickListener(new InfoItAdapter.OnClickListener() {
                    @Override
                    public void onClick(int friendId,String head,String nickName) {
                        Intent intent = new Intent(getContext(), FriendNoticeActivity.class);
                        startActivity(intent);
                    }
                });

                ifitRc.setAdapter(infoItAdapter);
            }
        }
        if (o instanceof CommunityZanBean&&TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            Toast.makeText(getContext(), ((CommunityZanBean) o).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
