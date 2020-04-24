package com.wd.tech.view.fragment.info;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.model.bean.info.FriendListBean;
import com.wd.tech.model.bean.info.FriendNoticeBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.MainActivity;
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
                ifitRc.setSwipeMenuCreator(new SwipeMenuCreator() {
                    @Override
                    public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

                        SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                                .setText("删除")
                                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)//设置高，这里使用match_parent，就是与item的高相同
                                .setWidth(70);//设置宽
                        swipeRightMenu.addMenuItem(deleteItem);//设置右边的侧滑
                    }
                });
                ifitRc.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
                    @Override
                    public void onItemClick(SwipeMenuBridge menuBridge) {
                        menuBridge.closeMenu();
                        int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                        result.remove(adapterPosition);
                        infoItAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
                ifitRc.setAdapter(infoItAdapter);
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
