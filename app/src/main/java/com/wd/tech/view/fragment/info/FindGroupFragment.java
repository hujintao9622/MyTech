package com.wd.tech.view.fragment.info;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.model.bean.info.CheckFriendBean;
import com.wd.tech.model.bean.info.GroupDetailsBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.NetUtil;
import com.wd.tech.view.activity.info.AddGroupActivity;
import com.wd.tech.view.activity.info.UserInfoMsActivity;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date:2020/5/8 0008
 * author:胡锦涛(Administrator)
 * function:找群
 */
public class FindGroupFragment extends BaseFragment<TechPresenter> {

    @BindView(R.id.sou)
    ImageView sou;
    @BindView(R.id.query)
    EditText query;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ll)
    LinearLayout ll;
    private int id;

    @Override
    protected void initView(View view) {
        ll.setVisibility(View.GONE);
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_find_group;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof GroupDetailsBean && TextUtils.equals("0000", ((GroupDetailsBean) o).getStatus())) {
            ll.setVisibility(View.VISIBLE);
            GroupDetailsBean.ResultBean result = ((GroupDetailsBean) o).getResult();
            NetUtil.getInstance().getCiclePhoto(result.getGroupImage(), head);
            name.setText(result.getGroupName());
            id=result.getGroupId();
        }
        //加群
        if (o instanceof CheckFriendBean&&TextUtils.equals("0000",((CheckFriendBean) o).getStatus())){
            int flag = ((CheckFriendBean) o).getFlag();
            if (flag==1){
                Toast.makeText(getContext(), ((CheckFriendBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }else if (flag==2){
                Intent intent = new Intent(getContext(), AddGroupActivity.class);
                intent.putExtra("id",this.id);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.sou, R.id.ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sou:
                //查找群
                String id = query.getText().toString().trim();
                HashMap<String, Object> map = new HashMap<>();
                map.put("groupId", id);
                mPresenter.getDoParams(MyUrls.BASE_GROUP_DETAILS, GroupDetailsBean.class, map);
                break;
            case R.id.ll:
                //是否在群内
                HashMap<String,Object> ma=new HashMap<>();
                ma.put("groupId",this.id);
                mPresenter.getDoParams(MyUrls.BASE_IS_INGROUP, CheckFriendBean.class,ma);
                break;
        }
    }
}
