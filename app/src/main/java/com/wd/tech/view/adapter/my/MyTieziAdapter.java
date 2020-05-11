package com.wd.tech.view.adapter.my;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wd.tech.R;
import com.wd.tech.model.bean.my.PostListBean;
import com.wd.tech.utils.NetUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户名：恆亙
 * 时间：2020/五月/星期日/14:50
 * 功能：我的帖子
 */
public class MyTieziAdapter extends RecyclerView.Adapter<MyTieziAdapter.ViewHolder> {

    private ArrayList<PostListBean.ResultBean> list;

    public MyTieziAdapter(ArrayList<PostListBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_my_tz, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostListBean.ResultBean resultBean = list.get(position);
        //正文
        holder.postContent.setText(resultBean.getContent());
        //日期
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = simpleDateFormat1.format(resultBean.getPublishTime());
        holder.postTime.setText(format);
        //点赞数
        holder.postZanText.setText(resultBean.getPraise()+"");
        //评论数
        holder.postXiaoxiText.setText(resultBean.getComment()+"");
        //图片
        if (resultBean.getFile()==""){
            holder.postImage.setVisibility(View.GONE);
        }else {
            holder.postImage.setVisibility(View.VISIBLE);
            NetUtil.getInstance().getCiclePhoto(resultBean.getFile(),holder.postImage);
        }
        //删除
        holder.postCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(resultBean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.post_content)
        TextView postContent;
        @BindView(R.id.post_image)
        ImageView postImage;
        @BindView(R.id.post_time)
        TextView postTime;
        @BindView(R.id.post_cal)
        TextView postCal;
        @BindView(R.id.post_zan_image)
        ImageView postZanImage;
        @BindView(R.id.post_zan_text)
        TextView postZanText;
        @BindView(R.id.post_xiaoxi_image)
        ImageView postXiaoxiImage;
        @BindView(R.id.post_xiaoxi_text)
        TextView postXiaoxiText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
    public interface OnClickListener{
        void onClick(int id);
    }
}
