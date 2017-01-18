package com.liz.wangying.sosweet.utils;

/**
 * desc:
 * Created by Liz on 2017/1/18.
 * github: https://github.com/lizwangying
 */

public interface SVGAttributeExtractor {
    int getStrokeColor();

    int getFillColor();

    int getStrokeWidth();

    int getOriginalWidth();

    int getOriginalHeight();

    int getStrokeDrawingDuration();

    int getFillDuration();

    //    Clip
    void recycleAttributes();

    void release();
}
