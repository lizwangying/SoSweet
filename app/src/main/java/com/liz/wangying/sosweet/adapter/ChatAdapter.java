package com.liz.wangying.sosweet.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liz.wangying.sosweet.BR;
import com.liz.wangying.sosweet.R;
import com.liz.wangying.sosweet.databinding.ItemTalkBinding;
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
    private ItemTalkBinding binding;

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
        holder.getBinding().setVariable(BR.chat,list.get(position));
        if (list.get(position).isReceive()) {
            holder.chat_bg.setBackgroundResource(R.drawable.left_bubble);
        } else {
            holder.chat_bg.setBackgroundResource(R.drawable.right_bubble);
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
        final LinearLayout chat_bg;
        private ViewDataBinding binding;

        public ChatHolder(View itemView) {
            super(itemView);
            binding=DataBindingUtil.bind(itemView);
            chat_bg = (LinearLayout) itemView.findViewById(R.id.chat_bg);
        }

        public void setBinding(ViewDataBinding binding){
            this.binding = binding;
        }

        public ViewDataBinding getBinding(){
            return this.binding;
        }
    }

}
