package com.wd.tech.view.activity.my;

import android.os.Bundle;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.model.bean.my.UserTaskBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.widget.MyUrls;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserTaskActivity extends BaseActivity<TechPresenter> {

    @BindView(R.id.status1)
    TextView status1;

    @Override
    protected void initData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 5);
        mPresenter.getDoParams(MyUrls.USER_TASK_LIST, UserTaskBean.class, map);
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
        return R.layout.activity_user_task;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof UserTaskBean) {
            if (((UserTaskBean) o).getStatus().equals("0000")) {
                for (int i = 0; i < 7; i++) {
                    if (((UserTaskBean) o).getResult().get(i).getStatus() == 1) {
                        status1.setText("已完成");
                    }
                }

            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
