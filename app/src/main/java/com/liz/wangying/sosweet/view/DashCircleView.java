package com.liz.wangying.sosweet.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * desc:
 * Created by Liz on 2017/1/19.
 * github: https://github.com/lizwangying
 */

public class DashCircleView extends View {
    private Paint mPaint;
    private int mProgress;
    /**
     * 是不是开始绘制下一个圆弧
     */
    private boolean isNext = false;
    /**
     * 圆弧绘制的速度
     */
    private int mSpeed=20;
    public DashCircleView(Context context) {
        super(context);
        init();

    }

    public DashCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DashCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.parseColor("#d9d9d9"));
        PathEffect effects = new DashPathEffect(new float[]{10,10,10,10},1);
        mPaint.setPathEffect(effects);
        //绘图线程
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    mProgress++;
                    if (mProgress == 360) {
//                        mProgress = 0;
//                        if (!isNext) {
//                            isNext = true;
//                        } else {
//                            isNext = false;
//                        }
                        return;
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(mSpeed); //通过传递过来的速度参数来决定线程休眠的时间从而达到绘制速度的快慢
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);


        int center = getWidth()/2;
        int radius = 800;
        int radius2 = 100;
        int r = 900;

        RectF oval1 = new RectF(center-radius,center-radius+780,center+radius,center+radius+780);
        RectF oval2 = new RectF(-550,-450,radius2+550,radius2+450);
        RectF oval3 = new RectF(-450+r,-450,radius2+450+r,radius2+450);
        canvas.drawArc(oval1,-180,mProgress,false,mPaint);
        canvas.drawArc(oval2,-90,mProgress,false,mPaint);
        canvas.drawArc(oval3,-0,mProgress,false,mPaint);
    }
}
