package com.liz.wangying.sosweet.model;

/**
 * desc: Banner对象
 * Created by Liz on 2016/12/15.
 * github: https://github.com/lizwangying
 */

public class Banner {
    public String imgUrl;
    public String imgTitle;

    public Banner(String imgUrl, String imgTitle) {
        this.imgUrl = imgUrl;
        this.imgTitle = imgTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }
}
