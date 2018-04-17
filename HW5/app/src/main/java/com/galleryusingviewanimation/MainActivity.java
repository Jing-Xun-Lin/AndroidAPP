package com.galleryusingviewanimation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    Animation[] animationList;
    private GridView mGridView;
    private ImageSwitcher mImgSwitcher;

    private Integer[] miImgArr = {
            R.drawable.a1, R.drawable.a2, R.drawable.a3,
            R.drawable.a4, R.drawable.a5, R.drawable.a6,
            R.drawable.a7, R.drawable.a8,
            R.drawable.a9, R.drawable.a10,
            R.drawable.a11, R.drawable.a12};

    private Integer[] miThumbImgArr = {
            R.drawable.a1c, R.drawable.a2c, R.drawable.a3c,
            R.drawable.a4c, R.drawable.a5c, R.drawable.a6c,
            R.drawable.a7c, R.drawable.a8c,
            R.drawable.a9c, R.drawable.a10c,
            R.drawable.a11c, R.drawable.a12c};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImgSwitcher = (ImageSwitcher) findViewById(R.id.imgSwitcher);

        mImgSwitcher.setFactory(this);	// 主程式類別必須implements ViewSwitcher.ViewFactory
        mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));

        ImageAdapter imgAdap = new ImageAdapter(this, miThumbImgArr);

        AniInit();

        mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setAdapter(imgAdap);
        //mGridView.setOnItemClickListener(gridViewOnItemClick);
        mGridView.setOnItemClickListener(onItemClick);
    }

    @Override
    public View makeView() {
        ImageView v = new ImageView(this);
        v.setBackgroundColor(0xFF000000);
        v.setScaleType(ImageView.ScaleType.FIT_CENTER);
        v.setLayoutParams(new ImageSwitcher.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundColor(Color.WHITE);
        return v;
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int select = (int) Math.floor(Math.random() * 4);
            mImgSwitcher.setInAnimation(animationList[select * 2]);
            mImgSwitcher.setOutAnimation(animationList[select * 2 + 1]);
            mImgSwitcher.setImageResource(miImgArr[position]);
        }
    };

    private AdapterView.OnItemClickListener gridViewOnItemClick = new
            AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View v,
                                        int position,
                                        long id) {
                    switch ((int)(Math.random()*3 + 1)) {
                        case 1:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.alpha_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.alpha_out));
                            break;
                        case 2:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.trans_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.trans_out));
                            break;
                        case 3:
                            mImgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_in));
                            mImgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                                    R.anim.scale_rotate_out));
                            break;
                    }

                    mImgSwitcher.setImageResource(miImgArr[position]);
                }
            };

    protected void AniInit() {
        ScaleAnimation scale;
        TranslateAnimation trans;
        RotateAnimation rotate;

        //<===========================set alphaIn===========================>
        AlphaAnimation alphaIn = new AlphaAnimation(0f, 1f);
        alphaIn.setInterpolator(new LinearInterpolator());
        alphaIn.setStartOffset(3000);
        alphaIn.setDuration(3000);

        //<===========================set alphaOut===========================>
        AlphaAnimation alphaOut = new AlphaAnimation(1f, 0f);
        alphaOut.setInterpolator(new LinearInterpolator());
        alphaOut.setStartOffset(0);
        alphaOut.setDuration(3000);

        //<===========================set scaleRotateIn===========================>
        AnimationSet scaleRotateIn = new AnimationSet(false);

        scale = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setStartOffset(3000);
        scale.setDuration(3000);

        rotate = new RotateAnimation(0, 1800, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setStartOffset(3000);
        rotate.setDuration(3000);

        scaleRotateIn.addAnimation(scale);
        scaleRotateIn.addAnimation(rotate);

        //<===========================set scaleRotateOut===========================>
        AnimationSet scaleRotateOut = new AnimationSet(false);

        scale = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setDuration(3000);

        rotate = new RotateAnimation(0, 1800, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setDuration(3000);

        scaleRotateOut.addAnimation(scale);
        scaleRotateOut.addAnimation(rotate);

        //<===========================set scaleRotateTransIn===========================>
        AnimationSet scaleRotateTransIn = new AnimationSet(false);

        scale = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setStartOffset(3000);
        scale.setDuration(3000);

        rotate = new RotateAnimation(0, 1800, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setStartOffset(3000);
        rotate.setDuration(3000);

        trans = new TranslateAnimation(0, 0, -500, 0);
        trans.setInterpolator(new LinearInterpolator());
        trans.setStartOffset(2000);
        trans.setDuration(3000);

        scaleRotateTransIn.addAnimation(scale);
        scaleRotateTransIn.addAnimation(rotate);
        scaleRotateTransIn.addAnimation(trans);

        //<===========================set scaleRotateTransOut===========================>
        AnimationSet scaleRotateTransOut = new AnimationSet(false);

        scale = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setInterpolator(new LinearInterpolator());
        scale.setDuration(3000);

        rotate = new RotateAnimation(0, 1800, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
        rotate.setDuration(3000);

        trans = new TranslateAnimation(0, 0, 0, 500);
        trans.setInterpolator(new LinearInterpolator());
        trans.setDuration(3000);

        scaleRotateTransOut.addAnimation(scale);
        scaleRotateTransOut.addAnimation(rotate);
        scaleRotateTransOut.addAnimation(trans);

        //<===========================set transIn===========================>
        AnimationSet transIn = new AnimationSet(false);

        trans = new TranslateAnimation(0, 0, -1000, 0);
        trans.setInterpolator(new LinearInterpolator());
        trans.setDuration(3000);

        transIn.addAnimation(trans);

        //<===========================set transOut===========================>
        AnimationSet transOut = new AnimationSet(false);

        trans = new TranslateAnimation(0, 0, 0, 1000);
        trans.setInterpolator(new LinearInterpolator());
        trans.setDuration(3000);

        transOut.addAnimation(trans);

        //<===========================set animationList===========================>
        animationList = new Animation[]{
                alphaIn, alphaOut,
                scaleRotateIn, scaleRotateOut,
                scaleRotateTransIn, scaleRotateTransOut,
                transIn, transOut
        };
    }
}
