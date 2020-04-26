package com.wd.tech.view.adapter.info;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.model.bean.information.CommentBean;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用户名：恆亙
 * 时间：2020/四月/星期五/13:54
 * 功能：资讯评论
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context mContext;
    private List<CommentBean.ResultBean> list;

    public CommentAdapter(Context mContext, List<CommentBean.ResultBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_details_comment, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext).load(list.get(position).getHeadPic()).circleCrop().into(holder.imgCommentPic);
        holder.tvCommentName.setText(list.get(position).getNickName());
        SimpleDateFormat format = new SimpleDateFormat("MM-dd hh:MM");
        String time = format.format(list.get(position).getCommentTime());
        holder.tvCommentTime.setText(time);
        holder.tvCommentContent.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_comment_pic)
        ImageView imgCommentPic;
        @BindView(R.id.tv_comment_name)
        TextView tvCommentName;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @BindView(R.id.tv_comment_content)
        TextView tvCommentContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
