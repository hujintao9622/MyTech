package com.wd.tech.view.adapter.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.model.bean.information.SearchBySourceBean;
import com.wd.tech.model.bean.information.SearchByTitleBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期一/20:13
 * 功能：根据作者名模糊查询
 */
public class SearchTitleAdapter extends RecyclerView.Adapter<SearchTitleAdapter.BaseSearchViewHolder> {
    private Context mContext;
    private List<SearchByTitleBean.ResultBean> mList;

    public SearchTitleAdapter(Context mContext, List<SearchByTitleBean.ResultBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public BaseSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_search, parent, false);
        return new BaseSearchViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseSearchViewHolder holder, int position) {
        holder.tvSearchTitle.setText(mList.get(position).getTitle());
        holder.tvSearchSource.setText(mList.get(position).getSource());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM");
        String time = format.format(mList.get(position).getReleaseTime());
        holder.tvSearchTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BaseSearchViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSearchTitle, tvSearchSource, tvSearchTime;
        public BaseSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSearchTitle = itemView.findViewById(R.id.tv_search_title);
            tvSearchSource = itemView.findViewById(R.id.tv_search_source);
            tvSearchTime = itemView.findViewById(R.id.tv_search_time);
        }
    }
}
