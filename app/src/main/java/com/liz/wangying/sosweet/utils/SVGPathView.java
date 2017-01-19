package com.liz.wangying.sosweet.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.github.jorgecastillo.State;
import com.github.jorgecastillo.utils.MathUtil;

import java.text.ParseException;

/**
 * desc: Path变成View
 * Created by Liz on 2017/1/17.
 * github: https://github.com/lizwangying
 */

public class SVGPathView extends View {
    private int strokeColor, fillColor, strokeWidth;
    private int originalWidth, originalHeight;
    private int strokeDrawingDuration, fillDuration;
    private int drawingState;
    private int viewWidth;
    private int viewHeight;
    private String svgPath;// svg 的 path 哦
    private Paint dashPaint;
    private Interpolator animInterpolator;
    private SVGPathData pathData;
    private long initialTime;
    private SVGStateChangedListener stateChangeListener;

    //builder的构造函数
    SVGPathView(ViewGroup parent, ViewGroup.LayoutParams params, int strokeColor,
                int strokeWidth, int strokeDrawingDuration, int originalWidth, int originalHeight, String svgPath) {
        super(parent.getContext());

        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
        this.strokeDrawingDuration = strokeDrawingDuration;
        this.fillDuration = fillDuration;
//        this.clippingTransform = transform;
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
        this.svgPath = svgPath;
//        this.percentageEnabled = percentageEnabled;
//        this.percentage = fillPercentage;

        init();
        parent.addView(this, params);
    }

    public SVGPathView(Context context) {
        super(context);
        init();
    }

    public SVGPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public SVGPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        init();
    }

    private void init() {
        drawingState = SVGViewState.NOT_STARTED;

        initDashPaint();
        animInterpolator = new DecelerateInterpolator();
        //这个据我所知是为了防止 XFermode 不好使
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    private void initAttrs(AttributeSet attrs) {
        SVGAttributeExtractorImpl.Builder extractorBuilder = new SVGAttributeExtractorImpl.Builder();
        SVGAttributeExtractorImpl extractor = extractorBuilder.with(getContext()).with(attrs).build();
        fillColor = extractor.getFillColor();
        strokeColor = extractor.getStrokeColor();
        strokeWidth = extractor.getStrokeWidth();
        originalHeight = extractor.getOriginalHeight();
        originalWidth = extractor.getOriginalWidth();
        strokeDrawingDuration = extractor.getStrokeDrawingDuration();
        fillDuration = extractor.getFillDuration();

        extractor.recycleAttributes();

    }

    private void initDashPaint() {
        dashPaint = new Paint();
        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setAntiAlias(true);
        dashPaint.setStrokeWidth(strokeWidth);
        dashPaint.setColor(strokeColor);
    }

    private void checkRequirements() {
        checkOriginalDimensions();
        checkPath();
    }

    private void checkOriginalDimensions() {
        if (originalWidth <= 0 || originalHeight <= 0) {
            throw new IllegalArgumentException(
                    "嘿，兄弟，你必须提供 svg 的原来的尺寸");
        }
    }

    private void checkPath() {
        if (pathData == null) {
            throw new IllegalArgumentException(
                    "嘿，兄弟，你必须提供一个 svg path 我才能画好么");
        }
    }

    /**
     * 开始绘制 stroke 啦
     */
    public void start() {
        checkRequirements();
        initialTime = System.currentTimeMillis();
        changeState(SVGViewState.STROKE_STARTED);
        invalidate();
        //Cause an invalidate to happen on the next animation time step, typically the next display frame.
        //翻译一下，就是 当下一次动画发生的时候重绘界面，一般事是下一帧的时候
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /**
     * 重置 ， stroke 一样消失掉
     */
    public void reset() {
        initialTime = 0;
        changeState(SVGViewState.NOT_STARTED);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /**
     * 改变生命周期并且修改生命周期的listener中的state
     *
     * @param state
     */
    private void changeState(int state) {
        if (drawingState == state) return;
        drawingState = state;
        if (stateChangeListener != null) {
            stateChangeListener.onStateChanged(state);
        }
    }

    public void setOnStateChangeListener(SVGStateChangedListener stateChangeListener) {
        this.stateChangeListener = stateChangeListener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        buildPathData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //判断能不能画，主要是不能再 state 的没开始和 path 为空的时候画
        if (!hasToDraw()) return;

        long elapsedTime = System.currentTimeMillis() - initialTime;
        drawStroke(canvas, elapsedTime);
        if (hasToKeepDrawing(elapsedTime)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            changeState(State.FINISHED);
        }
    }

    private void drawStroke(Canvas canvas, long elapsedTime) {
        float phase = MathUtil.constrain(0, 1, elapsedTime * 1f / strokeDrawingDuration);
        float distance = animInterpolator.getInterpolation(phase) * pathData.length;
        //这个patheffect 只会对STROKE或者FILL_AND_STROKE的paint style产生影响。如果style == FILL它会被忽略掉。
        dashPaint.setPathEffect(getDashPathForDistance(distance));
        canvas.drawPath(pathData.path, dashPaint);
    }

    private PathEffect getDashPathForDistance(float distance) {
        return new DashPathEffect(new float[]{distance, pathData.length}, 0);
    }

    private void buildPathData() {
        SVGPathParse parser = getPathParser();
        pathData = new SVGPathData();
        if (!TextUtils.isEmpty(svgPath)) {
            try {
                pathData.path = parser.parsePath(svgPath);
            } catch (ParseException e) {
                pathData.path = new Path();
            }
        }else {
            throw new IllegalArgumentException("嘿，兄弟，你必须提供一个 svg path 我才能画好么");


        }
        // true 不管 path 是否闭合，都自动闭合，如果可以的话...但是他不会影响原有 path 的状态
        // 当不是闭合的 path 强设置为 true 的时候可能会影响测量长度，可能会测量偏大，因为获取到的是闭合的状态的长度
        PathMeasure pm = new PathMeasure(pathData.path, true);
        while (true) {
            pathData.length = Math.max(pathData.length, pm.getLength());
            //nextContour() 跳转到下一个轮廓，跳转成功true否则。。。
            if (!pm.nextContour()) {
                break;
            }
        }

    }

    public void setSvgPath(String svgPath) {
        if (svgPath == null || svgPath.length() == 0) {
            throw new IllegalArgumentException("嘿，兄弟，你必须提供一个 svg path 我才能画好么");
        }
        this.svgPath = svgPath;
        buildPathData();
    }

    /**
     * 控制画，还是不画
     *
     * @return
     */
    public boolean hasToDraw() {
        return !(drawingState == State.NOT_STARTED || pathData == null);
    }
    private boolean hasToKeepDrawing(long elapsedTime) {
//        if (percentageEnabled) {
//            return previousFramePercentage < 100;
//        } else {
            return elapsedTime < strokeDrawingDuration + fillDuration;
//        }
    }
    private SVGPathParse getPathParser(){
        SVGConstrainedSvgPathParser.Builder builder = new SVGConstrainedSvgPathParser.Builder();
        return builder.originalWidth(originalWidth)
                .originalHeight(originalHeight)
                .viewWidth(viewWidth)
                .viewHeight(viewHeight)
                .build();
    }

}
