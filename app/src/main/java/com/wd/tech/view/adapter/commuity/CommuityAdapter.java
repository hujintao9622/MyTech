package com.wd.tech.view.adapter.commuity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.model.bean.community.CommunityListBean;
import com.wd.tech.utils.NetUtil;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2020/4/23 0023
 * author:胡锦涛(Administrator)
 * function:
 */
public class CommuityAdapter extends RecyclerView.Adapter<CommuityAdapter.ViewHolder> {

    private List<CommunityListBean.ResultBean> list;

    public CommuityAdapter(List<CommunityListBean.ResultBean> result) {

        list = result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.com_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommunityListBean.ResultBean resultBean = list.get(position);
        holder.comItemName.setText(resultBean.getNickName());
        holder.comItemNickname.setText(resultBean.getSignature());
        NetUtil.getInstance().getCiclePhoto(resultBean.getHeadPic(), holder.comItemImage);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM:dd HH:mm");
        String format = simpleDateFormat.format(resultBean.getPublishTime());
        String file = resultBean.getFile();
        if (file!=null&&!file.equals("")){
            holder.file.setVisibility(View.VISIBLE);
            NetUtil.getInstance().getPhoto(file,holder.file);
        }else {
            holder.file.setVisibility(View.GONE);
        }

        holder.comItemTime.setText(format);
        holder.comItemMessage.setText(resultBean.getContent());
        holder.comItemPlNum.setText(resultBean.getComment()+"");
        holder.comItemZanNum.setText(resultBean.getPraise()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.com_item_image)
        ImageView comItemImage;
        @BindView(R.id.com_item_name)
        TextView comItemName;
        @BindView(R.id.com_item_nickname)
        TextView comItemNickname;
        @BindView(R.id.com_item_time)
        TextView comItemTime;
        @BindView(R.id.com_item_message)
        TextView comItemMessage;
        @BindView(R.id.com_item_pl)
        ImageView comItemPl;
        @BindView(R.id.com_item_pl_num)
        TextView comItemPlNum;
        @BindView(R.id.com_item_zan)
        ImageView comItemZan;
        @BindView(R.id.file)
        ImageView file;
        @BindView(R.id.com_item_zan_num)
        TextView comItemZanNum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
