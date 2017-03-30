package com.liz.wangying.sosweet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liz.wangying.sosweet.R;
import com.liz.wangying.sosweet.model.ChatBean;

import java.util.List;

/**
 * desc: chat recyclerView adapter
 * Created by Liz on 2017/3/30.
 * email: lizwangying@icloud.com
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private List<ChatBean> list;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private ViewDataBinding binding;

    public ChatAdapter(Context mContext, List<ChatBean> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_talk, parent, false);
        ChatHolder chatHolder = new ChatHolder(binding.getRoot());
//        chatHolder.set
        return chatHolder;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        holder.tvMsg.setText(list.get(position).getMsg());
        holder.tvDate.setText(list.get(position).getDate());
        if (list.get(position).isReceive()) {
            holder.tvMsg.setBackgroundResource(R.drawable.left_bubble);
            holder.imMyIcon.setVisibility(View.GONE);
            holder.imIcon.setVisibility(View.VISIBLE);

        } else {
            holder.tvMsg.setBackgroundResource(R.drawable.right_bubble);
            holder.imMyIcon.setVisibility(View.VISIBLE);
            holder.imIcon.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    static class ChatHolder extends RecyclerView.ViewHolder {
        final TextView tvMsg;
        final TextView tvDate;
        final ImageView imIcon;
        final ImageView imMyIcon;
        private ViewDataBinding binding;

        public ChatHolder(View itemView) {
            super(itemView);
            binding=DataBindingUtil.bind(itemView);

                    tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            imIcon = (ImageView) itemView.findViewById(R.id.im_chat_icon);
            imMyIcon = (ImageView) itemView.findViewById(R.id.im_mine_chat_icon);
        }
    }

}
