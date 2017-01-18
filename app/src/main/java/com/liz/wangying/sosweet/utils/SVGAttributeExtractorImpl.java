package com.liz.wangying.sosweet.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.github.jorgecastillo.clippingtransforms.TransformAbstractFactory;
import com.github.jorgecastillo.clippingtransforms.TransformFactoryImpl;
import com.liz.wangying.sosweet.R;

import java.lang.ref.WeakReference;

/**
 * desc:
 * Created by Liz on 2017/1/18.
 * github: https://github.com/lizwangying
 */

public class SVGAttributeExtractorImpl implements SVGAttributeExtractor {
    private WeakReference<Context> weakContext;
    private WeakReference<AttributeSet> weakAttrs;
    private WeakReference<TypedArray> weakAttributeArray;
    private TransformAbstractFactory transformFactory;

    public SVGAttributeExtractorImpl(WeakReference<Context> weakContext, WeakReference<AttributeSet> weakAttrs) {
        transformFactory = new TransformFactoryImpl();
        this.weakContext = weakContext;
        this.weakAttrs = weakAttrs;
    }

    private Context context() {
        return weakContext.get();
    }

    private TypedArray attributeArray() {
        if (weakAttributeArray == null)
            weakAttributeArray = new WeakReference<>(context().getTheme().obtainStyledAttributes(
                    weakAttrs.get(), R.styleable.SVGPathView, 0, 0
            ));
        return weakAttributeArray.get();
    }

    @Override
    public int getStrokeColor() {
        return attributeArray().getColor(R.styleable.SVGPathView_strokeColor
                , context().getResources().getColor(R.color.white));
    }

    @Override
    public int getFillColor() {
        return attributeArray().getColor(R.styleable.SVGPathView_fillColor
        ,context().getResources().getColor(R.color.white));
    }

    @Override
    public int getStrokeWidth() {
        return attributeArray().getDimensionPixelSize(R.styleable.SVGPathView_strokeWidth
        , 1);
    }

    @Override
    public int getOriginalWidth() {
        return attributeArray().getInteger(R.styleable.SVGPathView_originalWidth
        , -1);
    }

    @Override
    public int getOriginalHeight() {
        return attributeArray().getInteger(R.styleable.FillableLoader_fl_originalHeight
        , -1);
    }

    @Override
    public int getStrokeDrawingDuration() {
        return attributeArray().getInteger(R.styleable.SVGPathView_strokeDrawingDuration
        ,1000);
    }

    @Override
    public int getFillDuration() {
        return attributeArray().getInteger(R.styleable.SVGPathView_fillDuration
        ,2000);
    }

    @Override
    public void recycleAttributes() {
        if (weakAttributeArray != null){
            weakAttributeArray.get().recycle();
        }
    }

    @Override
    public void release() {
        weakAttributeArray = null;
        weakContext = null;
        weakAttrs = null;
    }

    public static class Builder{
        private WeakReference<Context> weakContext;
        private WeakReference<AttributeSet> weakAttrs;

        public Builder with(Context context){
            if (context == null){
                throw new IllegalArgumentException("context 不能为空");
            }
            weakContext = new WeakReference<Context>(context);
            return this;
        }
        public Builder with(AttributeSet attributeSet){
            if (attributeSet == null){
                throw new IllegalArgumentException("attributeSet 不能为空");
            }
            weakAttrs = new WeakReference<AttributeSet>(attributeSet);
            return this;
        }

        public SVGAttributeExtractorImpl build(){
            return new SVGAttributeExtractorImpl(weakContext,weakAttrs);
        }
    }
}
