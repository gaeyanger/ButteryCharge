package com.hjy.aboutview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by windwos on 2016/12/25.
 * 思路：
 * 1.把电池画出来，path；
 * 2.充电效果，还是path去画
 */

public class SmartView extends View {

    private Paint butteryPaint, chargePaint;
    private Path butteryPath, chargePath;
    private int mWidth, mHeight, mLeft, mRight, mTop, mButtom;
    private int targetX,targetY;
    boolean start = true;
    public SmartView(Context context) {
        super(context);
        init();
    }

    public SmartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SmartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        mLeft = getLeft();
        mRight = getRight();
        mTop = getTop();
        mButtom = getBottom();

        targetY = mTop+ mHeight/4*3;
    }

    private void init() {
        butteryPaint = new Paint();
        butteryPaint.setColor(Color.BLACK);
        butteryPaint.setAntiAlias(true);
        butteryPaint.setStyle(Paint.Style.STROKE);
        butteryPaint.setStrokeWidth(20);

        chargePaint = new Paint();
        chargePaint.setColor(Color.GREEN);
        chargePaint.setAntiAlias(true);
        chargePaint.setStyle(Paint.Style.STROKE);
        chargePaint.setStrokeWidth(20);

        butteryPath = new Path();
        chargePath = new Path();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButtery(canvas);
        drawCharge(canvas);
    }

    private void drawCharge(Canvas canvas) {
        canvas.drawPath(chargePath, chargePaint);
    }

    private void drawButtery(Canvas canvas) {
        butteryPath.moveTo(mLeft + 100, mTop+ mHeight/4*3);
        butteryPath.lineTo(mLeft + 100, mTop + 70);
        butteryPath.lineTo(mLeft + mWidth / 2 - 70, mTop + 70);
        butteryPath.lineTo(mLeft + mWidth / 2 - 70, mTop + 20);
        butteryPath.lineTo(mLeft + mWidth / 2 + 70, mTop + 20);
        butteryPath.lineTo(mLeft + mWidth / 2 + 70, mTop + 70);
        butteryPath.lineTo(mRight - 100, mTop + 70);
        butteryPath.lineTo(mRight - 100, mTop+ mHeight/4*3);
        butteryPath.close();
        canvas.drawPath(butteryPath, butteryPaint);
    }
    public void setData(){

        if(start){
            targetX = mLeft+110;
            targetY -= 20;
            if(targetY < mTop+70){
                chargePath = new Path();
                targetY = mButtom-120;
            }
            chargePath.moveTo(targetX,targetY);
            start = false;
        }else{
            if(targetX<=mRight-110&&targetY>=mTop-70){
                targetX +=20;
            }else if(targetX>mRight-50) {
                targetX = mRight - 50;
                start = true;
            }

            chargePath.lineTo(targetX,targetY);
            invalidate();
        }

    }
}