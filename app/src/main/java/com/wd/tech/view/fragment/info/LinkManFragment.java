package com.wd.tech.view.fragment.info;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.model.bean.info.FriendGroupBean;
import com.wd.tech.model.bean.info.FriendListBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.info.FriendGroupAdapter;
import com.wd.tech.view.adapter.info.InfoExpandAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * date:2020/4/21 0021
 * author:胡锦涛(Administrator)
 * function:
 */
public class LinkManFragment extends BaseFragment<TechPresenter> {
    @BindView(R.id.query)
    EditText query;
    @BindView(R.id.linkManRc)
    //RecyclerView linkManRc;
    ExpandableListView linkManRc;
    private FriendGroupAdapter friendGroupAdapter;
    private int positio=-1;
    List<FriendGroupBean.ResultBean> group=new ArrayList<>();
    private InfoExpandAdapter adapter;

    @Override
    protected void initView(View view) {
        //查询所有分组
        mPresenter.getNoParams(MyUrls.BASE_FIND_ALLGROUP, FriendGroupBean.class);
        //linkManRc.setLayoutManager(new LinearLayoutManager(getContext()));
        //查询
        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String serach = query.getText().toString().trim();

            }
        });
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_linkman;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof FriendGroupBean&& TextUtils.equals("0000",((FriendGroupBean) o).getStatus())){
          if (((FriendGroupBean) o).getResult().size()>0){
              List<FriendGroupBean.ResultBean> result = ((FriendGroupBean) o).getResult();
              adapter = new InfoExpandAdapter(result);
              linkManRc.setAdapter(adapter);

              adapter.setOnClickListener(new InfoExpandAdapter.OnClickListener() {
                  @Override
                  public void onClick(int position, int groupId) {
                      boolean closed = result.get(position).isClosed();
                      if (closed){
                          linkManRc.collapseGroup(position);
                      }else {
                          linkManRc.expandGroup(position);
                      }
                      HashMap<String, Object> map = new HashMap<>();
                      map.put("groupId",groupId);
                      //查询分组下所有好友
                      mPresenter.getDoParams(MyUrls.BASE_FINDMAN_BYGROUP,FriendListBean.class,map);
                  }
              });
          }
        }
        if (o instanceof FriendListBean&&TextUtils.equals("0000",((FriendListBean) o).getStatus())){
            List<FriendListBean.ResultBean> result = ((FriendListBean) o).getResult();
            if (adapter != null) {
                adapter.addAllChild(positio,result);
            }

        }

    }

    @Override
    public void onFailure(Throwable e) {

    }
}
