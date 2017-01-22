package com.liz.wangying.sosweet.svgPathView;

import android.content.res.Resources;
import android.view.ViewGroup;

import com.github.jorgecastillo.clippingtransforms.ClippingTransform;
import com.github.jorgecastillo.clippingtransforms.PlainClippingTransform;

/**
 * desc: 初始化View的参数
 * Created by Liz on 2017/1/17.
 * github: https://github.com/lizwangying
 */

public class SVGPathViewBuilder {
    private ViewGroup parent;
    private ViewGroup.LayoutParams params;

    private int strokeColor = -1;
    private int fillColor = -1;
    private int strokeWidth = -1;
    private int originalWidth = -1;
    private int originalHeight = -1;
    private int strokeDrawingDuration = -1;
    private int fillDuration = -1;
    private boolean percentageEnabled;
    private float percentage;
    private ClippingTransform clippingTransform;
    private String svgPath;
    public SVGPathViewBuilder parentView(ViewGroup parent) {
        this.parent = parent;
        return this;
    }

    public SVGPathViewBuilder layoutParams(ViewGroup.LayoutParams params) {
        this.params = params;
        return this;
    }

    public SVGPathViewBuilder strokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public SVGPathViewBuilder fillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public SVGPathViewBuilder strokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public SVGPathViewBuilder originalDimensions(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
        return this;
    }

    public SVGPathViewBuilder strokeDrawingDuration(int strokeDrawingDuration) {
        this.strokeDrawingDuration = strokeDrawingDuration;
        return this;
    }

    public SVGPathViewBuilder fillDuration(int fillDuration) {
        this.fillDuration = fillDuration;
        return this;
    }

    public SVGPathViewBuilder clippingTransform(ClippingTransform transform) {
        this.clippingTransform = transform;
        return this;
    }

    public SVGPathViewBuilder svgPath(String svgPath) {
        this.svgPath = svgPath;
        return this;
    }

    public SVGPathViewBuilder percentage(float percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("percentage needs to be a value from 0 to 100");
        }
        percentageEnabled = true;
        this.percentage = percentage;
        return this;
    }
    public SVGPathView build(){
        Resources res = parent.getContext().getResources();
        strokeColor = strokeColor == -1 ? res.getColor(com.github.jorgecastillo.library.R.color.strokeColor) : strokeColor;
        fillColor = fillColor == -1 ? res.getColor(com.github.jorgecastillo.library.R.color.fillColor) : fillColor;
        strokeWidth = strokeWidth < 0 ? res.getDimensionPixelSize(com.github.jorgecastillo.library.R.dimen.strokeWidth) : strokeWidth;
        strokeDrawingDuration =
                strokeDrawingDuration < 0 ? res.getInteger(com.github.jorgecastillo.library.R.integer.strokeDrawingDuration)
                        : strokeDrawingDuration;
        fillDuration = fillDuration < 0 ? res.getInteger(com.github.jorgecastillo.library.R.integer.fillDuration) : fillDuration;
        clippingTransform =
                clippingTransform == null ? new PlainClippingTransform() : clippingTransform;

        if (params == null) {
            throwArgumentException("layout params");
        }

        if (svgPath == null) {
            throwArgumentException("svg path");
        }
        return  new SVGPathView(parent, params, strokeColor, strokeWidth,
                 strokeDrawingDuration, originalWidth,originalHeight, svgPath);
    }
    private void throwArgumentException(String neededStuff) {
        throw new IllegalArgumentException(
                "兄弟，我还差 " + neededStuff + " 就能开始画了");
    }
}
