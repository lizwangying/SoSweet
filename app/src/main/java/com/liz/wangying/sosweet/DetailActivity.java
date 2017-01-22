package com.liz.wangying.sosweet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jorgecastillo.FillableLoader;
import com.github.jorgecastillo.State;
import com.github.jorgecastillo.listener.OnStateChangeListener;
import com.liz.wangying.sosweet.utils.LogoPaths;
import com.liz.wangying.sosweet.svgPathView.SVGPathView;
import com.liz.wangying.sosweet.utils.SVGStateChangedListener;
import com.liz.wangying.sosweet.utils.SVGViewState;

import oak.svg.AnimatedSvgView;

/**
 * .,:,,,                                        .::,,,::.
 * .::::,,;;,                                  .,;;:,,....:i:
 * :i,.::::,;i:.      ....,,:::::::::,....   .;i:,.  ......;i.
 * :;..:::;::::i;,,:::;:,,,,,,,,,,..,.,,:::iri:. .,:irsr:,.;i.
 * ;;..,::::;;;;ri,,,.                    ..,,:;s1s1ssrr;,.;r,
 * :;. ,::;ii;:,     . ...................     .;iirri;;;,,;i,
 * ,i. .;ri:.   ... ............................  .,,:;:,,,;i:
 * :s,.;r:... ....................................... .::;::s;
 * ,1r::. .............,,,.,,:,,........................,;iir;
 * ,s;...........     ..::.,;:,,.          ...............,;1s
 * :i,..,.              .,:,,::,.          .......... .......;1,
 * ir,....:rrssr;:,       ,,.,::.     .r5S9989398G95hr;. ....,.:s,
 * ;r,..,s9855513XHAG3i   .,,,,,,,.  ,S931,.,,.;s;s&BHHA8s.,..,..:r:
 * :r;..rGGh,  :SAG;;G@BS:.,,,,,,,,,.r83:      hHH1sXMBHHHM3..,,,,.ir.
 * ,si,.1GS,   sBMAAX&MBMB5,,,,,,:,,.:&8       3@HXHBMBHBBH#X,.,,,,,,rr
 * ;1:,,SH:   .A@&&B#&8H#BS,,,,,,,,,.,5XS,     3@MHABM&59M#As..,,,,:,is,
 * .rr,,,;9&1   hBHHBB&8AMGr,,,,,,,,,,,:h&&9s;   r9&BMHBHMB9:  . .,,,,;ri.
 * :1:....:5&XSi;r8BMBHHA9r:,......,,,,:ii19GG88899XHHH&GSr.      ...,:rs.
 * ;s.     .:sS8G8GG889hi.        ....,,:;:,.:irssrriii:,.        ...,,i1,
 * ;1,         ..,....,,isssi;,        .,,.                      ....,.i1,
 * ;h:               i9HHBMBBHAX9:         .                     ...,,,rs,
 * ,1i..            :A#MBBBBMHB##s                             ....,,,;si.
 * .r1,..        ,..;3BMBBBHBB#Bh.     ..                    ....,,,,,i1;
 * :h;..       .,..;,1XBMMMMBXs,.,, .. :: ,.               ....,,,,,,ss.
 * ih: ..    .;;;, ;;:s58A3i,..    ,. ,.:,,.             ...,,,,,:,s1,
 * .s1,....   .,;sh,  ,iSAXs;.    ,.  ,,.i85            ...,,,,,,:i1;
 * .rh: ...     rXG9XBBM#M#MHAX3hss13&&HHXr         .....,,,,,,,ih;
 * .s5: .....    i598X&&A&AAAAAA&XG851r:       ........,,,,:,,sh;
 * . ihr, ...  .         ..                    ........,,,,,;11:.
 * ,s1i. ...  ..,,,..,,,.,,.,,.,..       ........,,.,,.;s5i.
 * .:s1r,......................       ..............;shs,
 * . .:shr:.  ....                 ..............,ishs.
 * .,issr;,... ...........................,is1s;.
 * .,is1si;:,....................,:;ir1sr;,
 * ..:isssssrrii;::::::;;iirsssssr;:..
 * .,::iiirsssssssssrri;;:.
 */
public class DetailActivity extends AppCompatActivity implements OnStateChangeListener, ResettableView {
    //    @Bind(R.id.fillableLoader)@Nullable  FillableLoader fillableLoader;
    FillableLoader fillableLoader;
    AnimatedSvgView mAnimatedSvgView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ButterKnife.bind(this);
        setContentView(R.layout.activity_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "ff", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.fill_container);
        fillableLoader = (FillableLoader) findViewById(R.id.fillableLoader);
        fillableLoader.setSvgPath(LogoPaths.WONENG_LC_LOGO_PATH);
//        fillableLoader.setClippingTransform(new WavesClippingTransform());
        fillableLoader.setOnStateChangeListener(this);
//        fillableLoader.start();

//        final SVGPathView pathView = (SVGPathView) findViewById(R.id.svg_path_view);
//        pathView.setSvgPath(LogoPaths.WONENG_LC_LOGO_PATH);
//        pathView.start();
        final SVGPathView pathView1 = (SVGPathView) findViewById(R.id.svg_path_view1);
        pathView1.setSvgPath(LogoPaths.MoneyLogo);
        final SVGPathView pathView2 = (SVGPathView) findViewById(R.id.svg_path_view2);
        pathView2.setSvgPath(LogoPaths.PIGGY);
        final SVGPathView pathView3 = (SVGPathView) findViewById(R.id.svg_path_view3);
        pathView3.setSvgPath(LogoPaths.CLOCK);
        final SVGPathView pathView4 = (SVGPathView) findViewById(R.id.svg_path_view4);
        pathView4.setSvgPath(LogoPaths.ONE_HUNDRED);
//        pathView1.start();
//        pathView2.start();
//        pathView3.start();
//        pathView4.start();
//        final SVGPathView pathView5 = (SVGPathView) findViewById(R.id.svg_path_view5);
//        pathView5.setSvgPath(LogoPaths.COMPLICATED_LOGO);
//        pathView5.start();


        float mInitialLogoOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 49, getResources().getDisplayMetrics());

        mAnimatedSvgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);
        mAnimatedSvgView.setGlyphStrings(LogoPaths.COMPLICATED_LOGO);
        mAnimatedSvgView.setFillPaints(
                new int[]{217, 217},
                new int[]{217, 217},
                new int[]{217, 217},
                new int[]{217, 217});
        int traceColor = Color.argb(85, 85, 85, 85);


        int[] traceColors = new int[2]; // 4 glyphs
        int residueColor = Color.argb(217, 217, 217, 217);
        int[] residueColors = new int[2]; // 4 glyphs

        // Every glyph will have the same trace/residue
        for (int i = 0; i < traceColors.length; i++) {
            traceColors[i] = traceColor;
            residueColors[i] = residueColor;
        }
        mAnimatedSvgView.setTraceColors(traceColors);
        mAnimatedSvgView.setTraceResidueColors(residueColors);
        mAnimatedSvgView.setOnStateChangeListener(new AnimatedSvgView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedSvgView.STATE_FINISHED){
                    pathView1.start();
                    pathView2.start();
                    pathView3.start();
                    pathView4.start();
                }
            }
        });
        mAnimatedSvgView.start();
        final View icon_bg=findViewById(R.id.view_icon_bg);
        pathView1.setOnStateChangeListener(new SVGStateChangedListener() {
            @Override
            public void onStateChanged(int state) {
                if (state == SVGViewState.FINISHED){
                    relativeLayout.setVisibility(View.VISIBLE);
                    fillableLoader.start();

                }
            }
        });

        final TextView tv1 = (TextView) findViewById(R.id.tv1);
        final TextView tv2 = (TextView) findViewById(R.id.tv2);
        fillableLoader.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state== State.STROKE_STARTED){
                    icon_bg.setVisibility(View.VISIBLE);
                    AnimatorSet set = new AnimatorSet();
                    Interpolator interpolator = new DecelerateInterpolator();
                    ObjectAnimator a1 = ObjectAnimator.ofFloat(mAnimatedSvgView, "translationY", 0);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(icon_bg, "alpha", 1);
                    a1.setInterpolator(interpolator);
                    set.setDuration(1000).playTogether(a1, a2);
                    set.start();
//                    icon_bg.animate().alpha(0f).translationY(20).setDuration(2000).start();
                }else if (state==State.FINISHED){
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
//                    tv1.animate().alpha(0f).translationY(20).setDuration(2000).start();
//                    tv2.animate().alpha(0f).translationY(20).setDuration(2000).start();
                }
            }
        });
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnimatedSvgView.start();
            }
        }, 1000);

    }

    @Override
    public void onStateChange(int state) {
//        showsta
    }

    @Override
    public void reset() {
        fillableLoader.postDelayed(new Runnable() {
            @Override
            public void run() {
                fillableLoader.start();
            }
        }, 250);
    }
}
