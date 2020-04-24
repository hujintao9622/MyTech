package com.wd.tech.view.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
import com.wd.tech.presenter.TechPresenter;
import com.wd.tech.view.activity.DetailsActivity;
import com.wd.tech.view.adapter.RecommendAdapter;
import com.wd.tech.widget.MyApp;
import com.wd.tech.widget.MyUrls;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

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

    @Override
    protected void initView(View view) {

        // 轮播图
        mPresenter.getNoParams(MyUrls.BASE_BANNER, BannerBean.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("plateId", "1");
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
                recommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int id = ((RecommendBean) o).getResult().get(position).getId();
                        Intent intent = new Intent(MyApp.getmContext(), DetailsActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
