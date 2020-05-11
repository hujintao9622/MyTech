package com.wd.tech.view.activity.info;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.community.CommunityZanBean;
import com.wd.tech.model.bean.info.FriendNoticeBean;
import com.wd.tech.model.bean.info.GroupNoticeBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.info.FriendNoticeAdapter;
import com.wd.tech.view.adapter.info.GroupNoticeAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

//群通知
public class FriendNoticeActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeBack)
    ImageView comeBack;
    @BindView(R.id.rc)
    RecyclerView rc;
    int page=1;
    private HashMap<String, Object> map;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        rc.setLayoutManager(new LinearLayoutManager(this));
        map = new HashMap<>();
        map.put("page",page);
        map.put("count",10);
        mPresenter.getDoParams(MyUrls.BASE_FRIEND_NOTICE, FriendNoticeBean.class, map);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_inform;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        //通知列表
        if (o instanceof FriendNoticeBean&& TextUtils.equals("0000",((FriendNoticeBean) o).getStatus())){
            List<FriendNoticeBean.ResultBean> result = ((FriendNoticeBean) o).getResult();
            if (result.size()==0){
                Toast.makeText(this, "没有通知", Toast.LENGTH_SHORT).show();
                rc.setVisibility(View.GONE);
                return;
            }else {
                FriendNoticeAdapter adapter = new FriendNoticeAdapter(result);
                adapter.setOnClickListener(new FriendNoticeAdapter.OnClickListener() {
                    @Override
                    public void onClick(int id, int flag) {
                        //审核群申请
                        HashMap<String,Object> map=new HashMap<>();
                        map.put("noticeId",id);
                        map.put("flag",flag);
                        mPresenter.putDoParams(MyUrls.BASE_REVIEW_FRIEND, CommunityZanBean.class,map);
                    }
                });
                rc.setAdapter(adapter);
            }
        }
        //审核
        if (o instanceof CommunityZanBean&&TextUtils.equals("0000",((CommunityZanBean) o).getStatus())){
            //刷新数据
            HashMap<String,Object> map=new HashMap<>();
            map.put("page",page);
            map.put("count",10);
            mPresenter.getDoParams(MyUrls.BASE_FRIEND_NOTICE, FriendNoticeBean.class, map);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.comeBack)
    public void onViewClicked() {
        finish();
    }
}
