package com.liz.wangying.sosweet.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * desc:
 * Created by Liz on 2017/2/23.
 * github: https://github.com/lizwangying
 */

public class HeartFontView extends TextView {
    private static Typeface cachedTypeface = null;

    public HeartFontView(Context context) {
        super(context);
        initFont();
    }


    public HeartFontView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont();
    }

    public HeartFontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFont();
    }

    private void initFont() {
        if (cachedTypeface == null) {
            final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "heart.ttf");
            cachedTypeface = typeface;
        }
        setTypeface(cachedTypeface);
    }
}
