package com.liz.wangying.svgpathview;

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

    int getTraceLineColor();

    int getTraceLineWidth();

    //    Clip
    void recycleAttributes();

    void release();
}
