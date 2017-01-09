package com.hjy.aboutview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.hjy.aboutview.bean.Point;
import com.hjy.aboutview.callback.CoolViewCallback;
import com.hjy.aboutview.view.CircleView;
import com.hjy.aboutview.view.CoolView;

public class MainActivity extends AppCompatActivity implements CoolViewCallback,View.OnClickListener{

    private LinearLayout outLayout,firstLayout,secondLayout,thirdLayout;
    private CoolView cv;
    private View deliverView;
    private CircleView vivoCv,meizuCv,smartCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        outLayout = (LinearLayout) findViewById(R.id.out_layout);
        firstLayout = (LinearLayout) findViewById(R.id.first_layout);
        secondLayout = (LinearLayout) findViewById(R.id.second_layout);
        thirdLayout = (LinearLayout) findViewById(R.id.third_layout);
        firstLayout.setOnClickListener(this);
        secondLayout.setOnClickListener(this);
        thirdLayout.setOnClickListener(this);

        deliverView = (View) findViewById(R.id.deliverview);
        vivoCv = (CircleView) findViewById(R.id.vivo_cv);
        vivoCv.setColor(ContextCompat.getColor(this,R.color.blue));
        meizuCv = (CircleView) findViewById(R.id.meizu_cv);
        meizuCv.setColor(ContextCompat.getColor(this,R.color.green));
        smartCv = (CircleView) findViewById(R.id.smart_cv);
        smartCv.setColor(ContextCompat.getColor(this,R.color.red));

        cv = (CoolView) findViewById(R.id.cv);
        cv.addCallBack(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int childHeight = firstLayout.getMeasuredHeight();
        int y1 = firstLayout.getTop() + childHeight/2;
        int y2 = y1 + childHeight + deliverView.getMeasuredHeight();
        int y3 = y2 + childHeight + deliverView.getMeasuredHeight();

        cv.addPoint(new Point(165,y1),new Point(165,y2),new Point(165,y3));
    }

    @Override
    public void hideView() {
        ObjectAnimator animator = new ObjectAnimator().ofFloat(cv, "alpha", 1.0f, 0.0f);
        animator.setDuration(2000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                cv.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first_layout:
                toActivity(ViVoActivity.class);
                break;
            case R.id.second_layout:
                toActivity(MeiZuActivity.class);
                break;
            case R.id.third_layout:
                toActivity(SmartActivity.class,smartCv,"share");
                break;
        }
    }
    private void toActivity(Class class1){
        Intent intent = new Intent();
        intent.setClass(this, class1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    private void toActivity(Class class1,View view,String name){
        Intent intent = new Intent();
        intent.setClass(this, class1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this,view,name).toBundle());
    }
}