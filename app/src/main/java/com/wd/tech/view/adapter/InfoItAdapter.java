package com.wd.tech.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.model.bean.info.FriendNoticeBean;
import com.wd.tech.utils.NetUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/4/21 0021
 * author:胡锦涛(Administrator)
 * function:
 */
public class InfoItAdapter extends RecyclerView.Adapter<InfoItAdapter.MyViewHolder> {

    private List<FriendNoticeBean.ResultBean> list;

    public InfoItAdapter(List<FriendNoticeBean.ResultBean> result) {

        list = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.infoit_i, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FriendNoticeBean.ResultBean resultBean = list.get(position);
        holder.name.setText(resultBean.getFromNickName());
        holder.remark.setText(resultBean.getRemark());
        NetUtil.getInstance().getPhoto(resultBean.getFromHeadPic(),holder.iv);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        //获取当前时间
        String oldTime = format.format(resultBean.getNoticeTime());
        holder.time.setText(oldTime);
        int status = resultBean.getStatus();
        if (status==1){
            holder.red.setVisibility(View.VISIBLE);
        }else {
            holder.red.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.remark)
        TextView remark;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.red)
        ImageView red;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
