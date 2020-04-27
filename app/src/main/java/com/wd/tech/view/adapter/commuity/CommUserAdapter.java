package com.wd.tech.view.adapter.commuity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.model.bean.community.CommUserBean;
import com.wd.tech.utils.NetUtil;

import java.util.List;

import butterknife.BindView;

/**
 * date:2020/4/26 0026
 * author:胡锦涛(Administrator)
 * function:
 */
public class CommUserAdapter extends RecyclerView.Adapter<CommUserAdapter.MyViewHolder> {

    private List<CommUserBean.ResultBean.CommunityUserPostVoListBean> list;

    public CommUserAdapter(List<CommUserBean.ResultBean.CommunityUserPostVoListBean> communityUserPostVoList) {

        list = communityUserPostVoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.comuser, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CommUserBean.ResultBean.CommunityUserPostVoListBean listBean = list.get(position);
        holder.content.setText(listBean.getContent());
        NetUtil.getInstance().getPhoto(listBean.getFile(),holder.file);
        int great = listBean.getWhetherGreat();
        if (great==1){
            holder.zan.setImageResource(R.drawable.common_icon_praise_s_hdpi);
        }else {
            holder.zan.setImageResource(R.drawable.common_icon_prise_n_hdpi);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.file)
        ImageView file;
        @BindView(R.id.im_ll)
        LinearLayout imLl;
        @BindView(R.id.pl)
        ImageView pl;
        @BindView(R.id.pl_num)
        TextView plNum;
        @BindView(R.id.zan)
        ImageView zan;
        @BindView(R.id.zan_num)
        TextView zanNum;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
