package com.liz.wangying.sosweet.svgPathView.svg;

/**
 * desc:
 * Created by Liz on 2017/1/18.
 * github: https://github.com/lizwangying
 */

public class SVGConstrainedSvgPathParser extends SVGPathParse {
    private int originalWidth, originalHeight;
    private int viewWidth, viewHeight;

    private SVGConstrainedSvgPathParser(int originalWidth, int originalHeight, int viewWidth, int viewHeight) {
        this.originalHeight = originalHeight;
        this.originalWidth = originalWidth;
        this.viewHeight = viewHeight;
        this.viewWidth = viewWidth;
    }

    @Override
    protected float transformX(float x) {
//        return x * viewWidth / originalWidth;
        return x ;
    }

    @Override
    protected float transformY(float y) {
//        return y * viewHeight / originalHeight;
        return y ;
    }

    public static class Builder{
        private int originalWidth, originalHeight;
        private int viewWidth, viewHeight;

        public Builder originalWidth(int originalWidth){
            this.originalWidth = originalWidth;
            return this;
        }

        public  Builder originalHeight(int originalHeight){
            this.originalHeight = originalHeight;
            return this;
        }

        public Builder viewWidth(int viewWidth){
            this.viewWidth = viewWidth;
            return this;
        }

        public Builder viewHeight(int viewHeight){
            this.viewHeight = viewHeight;
            return this;
        }

        public SVGConstrainedSvgPathParser build(){
            return new SVGConstrainedSvgPathParser(originalWidth,originalHeight,viewWidth,viewHeight);
        }
    }
}
