package com.wd.tech.view.adapter.info;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.model.bean.info.FriendNoticeBean;
import com.wd.tech.model.bean.info.GroupNoticeBean;
import com.wd.tech.utils.NetUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/5/8 0008
 * author:胡锦涛(Administrator)
 * function:群通知
 */
public class FriendNoticeAdapter extends RecyclerView.Adapter<FriendNoticeAdapter.ViewHolder> {

    private List<FriendNoticeBean.ResultBean> result;

    public FriendNoticeAdapter(List<FriendNoticeBean.ResultBean> result) {

        this.result = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.group_notice, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendNoticeBean.ResultBean resultBean = result.get(position);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        int status = resultBean.getStatus();
        switch (status){
            case 1://待处理
                holder.btNo.setVisibility(View.VISIBLE);
                holder.btOk.setVisibility(View.VISIBLE);
                holder.statue.setVisibility(View.GONE);
                break;
            case 2://通过
                holder.btNo.setVisibility(View.GONE);
                holder.btOk.setVisibility(View.GONE);
                holder.statue.setVisibility(View.VISIBLE);
                holder.statue.setText("已同意");
                break;
            case 3://驳回
                holder.btNo.setVisibility(View.GONE);
                holder.btOk.setVisibility(View.GONE);
                holder.statue.setVisibility(View.VISIBLE);
                holder.statue.setText("已拒绝");
                break;
        }
        holder.time.setText(format.format(resultBean.getNoticeTime()));
        holder.name.setText(resultBean.getFromNickName());
        NetUtil.getInstance().getCiclePhoto(resultBean.getFromHeadPic(),holder.head);
        String groupName = resultBean.getFromNickName();
        holder.remoke.setText("请求添加你为好友");
        holder.btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(resultBean.getNoticeId(),2);
                }
            }
        });
        holder.btNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(resultBean.getNoticeId(),3);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.head)
        ImageView head;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.remoke)
        TextView remoke;
        @BindView(R.id.statue)
        TextView statue;
        @BindView(R.id.bt_ok)
        TextView btOk;
        @BindView(R.id.bt_no)
        TextView btNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onClick(int id, int flag);
    }
}
