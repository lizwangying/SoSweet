package com.liz.wangying.sosweet.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Liz on 2017/3/28.
 * email: lizwangying@icloud.com
 */

@SuppressLint("AppCompatCustomView")
public class AutoSplitTextView extends TextView {
    private boolean mEnabled = true;

    public AutoSplitTextView(Context context) {
        super(context);
    }

    public AutoSplitTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSplitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAutoSplitEnabled(boolean enabled) {
        mEnabled = enabled;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY
                && MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY
                && getWidth() > 0 && getHeight() > 0 && mEnabled) {
            String newText = autoSplitText(this, "");
            if (!TextUtils.isEmpty(newText)) {
                setText(newText);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 自动缩进方法，外部调用
     * @param tv TextView对象，在xml中必须得使用自定义的这个，至于参数为啥是 TextView ，其实你换成自己也没问题。
     * @param indent 在文本之后缩进，比如你需要缩进 1. 就传入 "1." 字符串就好, 会测量indent 的宽度，以他的宽度缩进
     * @return 返回缩进完了之后的 字符串，所以你的 setText 哦，傻傻的盯着屏幕，还问为啥不好使。返回的字符你没有 set 呀，傻子
     */
    public String autoSplitText(final TextView tv, final String indent) {
        final String rawText = tv.getText().toString();//原始文本
        final Paint paint = tv.getPaint();//画笔，还包含字体信息
        int a = tv.getPaddingLeft();
        int b = tv.getPaddingRight();
        int c = tv.getWidth();
        if (c != 0) {
            final float tvWidth = c - a - b;//空间可用宽度

            //将缩进处理成空格
            String indentSpace = "";
            float indentWidth = 0;
            if (!TextUtils.isEmpty(indent)) {
                float rawIndentWidth = paint.measureText(indent);
                if (rawIndentWidth < tvWidth) {
                    while ((indentWidth = paint.measureText(indentSpace)) < rawIndentWidth) {
                        indentSpace += " ";
                    }
                }
            }

            //将原始文本按行拆分
            String[] rawTextLines = rawText.replaceAll("\r", "").split("\n");
            StringBuilder sbNewText = new StringBuilder();
            for (String rawTextLine : rawTextLines) {
                if (paint.measureText(rawTextLine) <= tvWidth) {
                    //如果行宽度在空间范围之内，就不处理了
                    sbNewText.append(rawTextLine+"\n");
                } else {
                    //否则按字符测量，在超过可用宽度的前一个字符处，手动替换，加上换行，缩进
                    float lineWidth = 0;
                    for (int i = 0; i != rawTextLine.length(); ++i) {
                        char ch = rawTextLine.charAt(i);
                        //从手动换行的第二行开始加上缩进
                        if (lineWidth < 0.1f && i != 0) {
                            sbNewText.append(indentSpace);
                            lineWidth += indentWidth;
                        }
                        float textWidth = paint.measureText(String.valueOf(ch));
                        lineWidth += textWidth;
                        if (lineWidth < tvWidth) {
                            sbNewText.append(ch);
                        } else {
                            sbNewText.append("\n");
                            lineWidth = 0;
                            --i;
                        }
                    }
                    sbNewText.append("\n");
                }
            }
            //结尾多余的换行去掉
            if (!rawText.endsWith("\n")) {
                sbNewText.deleteCharAt(sbNewText.length() - 1);
            }
            Log.e("haha", sbNewText.toString());
            return sbNewText.toString();
        }else {
            return "";
        }
    }
}
