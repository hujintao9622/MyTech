package com.wd.tech.view.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.SimpleBannerInfo;
import com.stx.xhb.androidx.transformers.Transformer;
import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.model.bean.information.BannerBean;
import com.wd.tech.model.bean.information.RecommendBean;
import com.wd.tech.model.bean.login.RegisterBean;
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.information.DetailsActivity;
import com.wd.tech.view.activity.information.PlatesActivity;
import com.wd.tech.view.activity.information.SearchActivity;
import com.wd.tech.view.adapter.RecommendAdapter;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * date:2020/4/19 0019
 * author:胡锦涛(Administrator)
 * function:资讯
 */
public class ConsultFragment extends BaseFragment<TechPresenter> {
    @BindView(R.id.xBanner)
    XBanner xBanner;
    @BindView(R.id.rv_info_recommend)
    RecyclerView rvInfoRecommend;
    @BindView(R.id.img_recommend_menu)
    ImageView imgRecommendMenu;
    @BindView(R.id.img_recommend_search)
    ImageView imgRecommendSearch;
    private int i1;

    @Override
    protected void initView(View view) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            i1 = random.nextInt(9) + 1;
        }
        // 轮播图
        mPresenter.getNoParams(MyUrls.BASE_BANNER, BannerBean.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("plateId", i1);
        map.put("page", "1");
        map.put("count", "10");
        // 资讯展示列表
        mPresenter.getDoParams(MyUrls.INFO_RECOMMEND, RecommendBean.class, map);

    }

    @Override
    protected TechPresenter providePresenter() {
        return new TechPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_consult;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DestroyFragment() {

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof BannerBean) {
            if (((BannerBean) o).getStatus().equals("0000")) {
                int size = ((BannerBean) o).getResult().size();
                Log.e("TAG", "轮播图的长度: " + size);
                List<BannerBean.ResultBean> list = ((BannerBean) o).getResult();
                xBanner.setBannerData(new AbstractList<SimpleBannerInfo>() {
                    @Override
                    public SimpleBannerInfo get(int index) {
                        return null;
                    }

                    @Override
                    public int size() {
                        return list.size();
                    }
                });
                xBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(MyApp.getmContext()).load(list.get(position).getImageUrl()).into((ImageView) view);
                    }
                });
                xBanner.setPageTransformer(Transformer.Accordion);
                xBanner.setPageChangeDuration(3000);
            }
        }
        if (o instanceof RecommendBean) {
            if (((RecommendBean) o).getStatus().equals("0000")) {
                ArrayList<RecommendBean.ResultBean> recommendList = (ArrayList<RecommendBean.ResultBean>) ((RecommendBean) o).getResult();
                rvInfoRecommend.setLayoutManager(new LinearLayoutManager(MyApp.mContext, RecyclerView.VERTICAL, false));
                RecommendAdapter recommendAdapter = new RecommendAdapter(R.layout.item_recommend, recommendList);
                rvInfoRecommend.setAdapter(recommendAdapter);

                recommendAdapter.setOnItemClickListene(new RecommendAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int position, CheckBox checkBox, TextView textView, int collection) {
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (checkBox.isChecked()) {
                                    mPresenter.dltNoParams(MyUrls.ADD_COLLECTION, RegisterBean.class);
                                    int collection1 = ((RecommendBean) o).getResult().get(position).getCollection();
                                    textView.setText(collection1 + "");
                                } else {
                                    mPresenter.dltNoParams(MyUrls.CANCEL_COLLECTION, RegisterBean.class);
                                    int collection1 = ((RecommendBean) o).getResult().get(position).getCollection();
                                    textView.setText(collection1 + "");
                                }
                            }
                        });

                        int id = ((RecommendBean) o).getResult().get(position).getId();
                        Intent intent = new Intent(MyApp.getmContext(), DetailsActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
            }
        }
        if (o instanceof RegisterBean) {
            if (((RegisterBean) o).getStatus().equals("0000")) {
                Toast.makeText(MyApp.getmContext(), ((RegisterBean) o).getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.img_recommend_menu, R.id.img_recommend_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_recommend_menu:
                Intent intent1 = new Intent(MyApp.getmContext(), PlatesActivity.class);
                startActivity(intent1);
                break;
            case R.id.img_recommend_search:
                Intent intent = new Intent(MyApp.getmContext(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
