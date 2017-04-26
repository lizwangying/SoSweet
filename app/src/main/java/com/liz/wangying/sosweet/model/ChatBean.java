package com.liz.wangying.sosweet.model;

import android.databinding.BaseObservable;

/**
 * desc: use data binding in RecyclerView ,so have to extends BaseObservable
 * Created by Liz on 2017/3/30.
 * email: lizwangying@icloud.com
 */

public class ChatBean extends BaseObservable{
    public String msg;
    public String date;
    public boolean receive;

    public boolean isReceive() {
        return receive;
    }

    public void setReceive(boolean receive) {
        this.receive = receive;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
