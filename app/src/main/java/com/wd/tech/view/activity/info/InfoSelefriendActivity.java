package com.wd.tech.view.activity.info;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.info.InfoSeleFriendBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.utils.NetUtil;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoSelefriendActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.comeback)
    ImageView comeback;
    @BindView(R.id.query)
    EditText query;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ll)
    LinearLayout ll;
    private String phone;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = query.getText().toString().trim();
                HashMap<String ,Object> map=new HashMap<>();
                map.put("phone",name);
                mPresenter.getDoParams(MyUrls.BASE_SEUSER_BYPHONE, InfoSeleFriendBean.class,map);
            }
        });
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoSelefriendActivity.this,UserInfoMsActivity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });
    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_info_selefriend;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof InfoSeleFriendBean&& TextUtils.equals("0000",((InfoSeleFriendBean) o).getStatus())){
            InfoSeleFriendBean.ResultBean result = ((InfoSeleFriendBean) o).getResult();
            String headPic = result.getHeadPic();
            NetUtil.getInstance().getPhoto(headPic,head);
            name.setText(result.getNickName());
            phone = result.getPhone();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick(R.id.comeback)
    public void onViewClicked() {
        finish();
    }
}
