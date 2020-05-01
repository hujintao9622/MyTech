package com.wd.tech.view.activity.information;


import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BaseHuaActivity;
import com.wd.tech.model.bean.information.SearchBySourceBean;
import com.wd.tech.model.bean.information.SearchByTitleBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.adapter.info.SearchSourceAdapter;
import com.wd.tech.view.adapter.info.SearchTitleAdapter;
import com.wd.tech.widget.MyUrls;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseHuaActivity<TechPresenter> {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.xrv_search)
    XRecyclerView xrvSearch;
    @BindView(R.id.che_by_title)
    CheckBox cheByTitle;
    private int page = 1;

    @Override
    protected void initData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("count", 10);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (cheByTitle.isChecked()) {
                    String trim = etSearch.getText().toString().trim();
                    map.put("title", trim);
                    mPresenter.getDoParams(MyUrls.INFORMATION_BY_TITLE, SearchByTitleBean.class, map);
                } else if (!cheByTitle.isChecked()) {
                    String trim = etSearch.getText().toString().trim();
                    map.put("source", trim);
                    mPresenter.getDoParams(MyUrls.INFORMATION_BY_SOURCE, SearchBySourceBean.class, map);
                }
            }
        });
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
        return R.layout.activity_search;
    }

    @Override
    protected void DestroyActivity() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof SearchBySourceBean) {
            if (((SearchBySourceBean) o).getStatus().equals("0000")) {
                ArrayList<SearchBySourceBean.ResultBean> searchList = (ArrayList<SearchBySourceBean.ResultBean>) ((SearchBySourceBean) o).getResult();
                xrvSearch.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                SearchSourceAdapter searchSourceAdapter = new SearchSourceAdapter(this, searchList);
                xrvSearch.setAdapter(searchSourceAdapter);
                searchSourceAdapter.notifyDataSetChanged();
                xrvSearch.setPullRefreshEnabled(true);
                xrvSearch.setLoadingMoreEnabled(true);
                xrvSearch.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
                xrvSearch.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
                xrvSearch.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        searchList.clear();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String trim = etSearch.getText().toString().trim();
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("page", page);
                                map.put("count", 10);
                                map.put("source", trim);
                                mPresenter.getDoParams(MyUrls.INFORMATION_BY_SOURCE, SearchBySourceBean.class, map);
                            }
                        }, 2000);

                    }
                    @Override
                    public void onLoadMore() {
                        page++;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String trim = etSearch.getText().toString().trim();
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("page", page);
                                map.put("count", 10);
                                map.put("source", trim);
                                mPresenter.getDoParams(MyUrls.INFORMATION_BY_TITLE, SearchByTitleBean.class, map);
                            }
                        }, 2000);

                    }
                });
            }
        }
        if (o instanceof SearchByTitleBean) {
            if (((SearchByTitleBean) o).getStatus().equals("0000")) {
                ArrayList<SearchByTitleBean.ResultBean> list = (ArrayList<SearchByTitleBean.ResultBean>) ((SearchByTitleBean) o).getResult();
                xrvSearch.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                SearchTitleAdapter searchTitleAdapter = new SearchTitleAdapter(this, list);
                xrvSearch.setAdapter(searchTitleAdapter);
                searchTitleAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
