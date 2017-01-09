package com.hjy.aboutview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by windwos on 2016/12/26.
 */

public class MeiZuView extends View {
    //画笔
    private Paint chargePaint, butteryPaint, ballPaint1, ballPaint2, ballPaint3, ballPaint4, ballPaint5;
    //路径
    private Path chargePath, butteryPath;

    //大小
    private int size1, size2, size3, size4, size5;

    //view的位置
    private int mWidth, mHeight, mLeft, mRight, mTop, mButtom, mMiddle;

    //是否第一次
    boolean startBall1 = true, startBall2 = true, startBall3 = true, startBall4 = true, startBall5 = true;
    //初始透明度
    int aplhaBall1, aplhaBall2, aplhaBall3, aplhaBall4, aplhaBall5;
    //轨迹位置
    int leftBall1, rightBall1;
    int leftBall2, rightBall2;
    int leftBall3, rightBall3;
    int leftBall4, rightBall4;
    int leftBall5, rightBall5;


    //初始位置
    int xBall1, yBall1;
    int xBall2, yBall2;
    int xBall3, yBall3;
    int xBall4, yBall4;
    int xBall5, yBall5;


    //速度
    int speedBall1;
    int speedBall2;
    int speedBall3;
    int speedBall4;
    int speedBall5;


    //变大
    boolean more1 = true;
    boolean more2 = true;
    boolean more3 = true;
    boolean more4 = true;
    boolean more5 = true;

    //球的最高
    private int ballTop;
    //充电到顶
    boolean chargeFull = true;
    //充电进度
    private int chargeHeight;

    public MeiZuView(Context context) {
        super(context);
        init();
    }


    public MeiZuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MeiZuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MeiZuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ballPaint1 = new Paint();
        ballPaint1.setAntiAlias(true);
        ballPaint1.setColor(Color.GREEN);
        ballPaint1.setStyle(Paint.Style.FILL);

        ballPaint2 = new Paint();
        ballPaint2.setAntiAlias(true);
        ballPaint2.setColor(Color.GREEN);
        ballPaint2.setStyle(Paint.Style.FILL);

        ballPaint3 = new Paint();
        ballPaint3.setAntiAlias(true);
        ballPaint3.setColor(Color.GREEN);
        ballPaint3.setStyle(Paint.Style.FILL);

        ballPaint4 = new Paint();
        ballPaint4.setAntiAlias(true);
        ballPaint4.setColor(Color.GREEN);
        ballPaint4.setStyle(Paint.Style.FILL);

        ballPaint5 = new Paint();
        ballPaint5.setAntiAlias(true);
        ballPaint5.setColor(Color.GREEN);
        ballPaint5.setStyle(Paint.Style.FILL);

        butteryPath = new Path();
        butteryPaint = new Paint();
        butteryPaint.setColor(Color.BLACK);
        butteryPaint.setAntiAlias(true);
        butteryPaint.setStyle(Paint.Style.STROKE);
        butteryPaint.setStrokeWidth(20);

        chargePaint = new Paint();
        chargePaint.setAntiAlias(true);
        chargePaint.setColor(Color.GREEN);
        chargePaint.setStyle(Paint.Style.STROKE);


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
        ballTop = mButtom - mHeight / 5*2;
        mMiddle = mLeft + mWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBall1(canvas);
        drawBall2(canvas);
        drawBall3(canvas);
        drawBall4(canvas);
        drawBall5(canvas);

        drawButtery(canvas);
        drawCharge(canvas);
    }


    /**
     * 画电池
     *
     * @param canvas
     */
    private void drawButtery(Canvas canvas) {
        Log.d("TAG", "drawBUtt!!!!!");

        butteryPath.moveTo(mLeft + mWidth/4, ballTop - 20);
        butteryPath.lineTo(mLeft + mWidth/4, mTop + 70);
        butteryPath.lineTo(mLeft + mWidth / 2 - 70, mTop + 70);
        butteryPath.lineTo(mLeft + mWidth / 2 - 70, mTop + 20);
        butteryPath.lineTo(mLeft + mWidth / 2 + 70, mTop + 20);
        butteryPath.lineTo(mLeft + mWidth / 2 + 70, mTop + 70);
        butteryPath.lineTo(mRight - mWidth/4, mTop + 70);
        butteryPath.lineTo(mRight - mWidth/4, ballTop - 20);
        butteryPath.close();
        canvas.drawPath(butteryPath, butteryPaint);


    }

    /**
     * 画充电进度条
     *
     * @param canvas
     */
    private void drawCharge(Canvas canvas) {
        chargePaint.setStrokeWidth(mWidth/2-20);
        if (chargeFull) {
            chargePath = new Path();
            chargeHeight = ballTop - 30;
            chargePath.moveTo(mMiddle, chargeHeight);
            chargeFull = false;
        } else {
            if (chargeHeight <= mTop + 80) {
                chargeFull = true;
            } else {
                chargeHeight -= 2;
            }
        }
        chargePath.lineTo(mMiddle, chargeHeight);


        canvas.drawPath(chargePath, chargePaint);
    }

    /**
     * 几个类似的函数都是用来画球的
     *
     * @param canvas
     */
    private void drawBall1(Canvas canvas) {

        if (startBall1) {
            aplhaBall1 = getMyAlpha();
            xBall1 = getStartX();
            yBall1 = getStartY();
            leftBall1 = getTranceLeft();
            rightBall1 = getTranceRight();
            speedBall1 = getSpeed();
            if (xBall1 < leftBall1 || xBall1 > rightBall1) {
                xBall1 = leftBall1;
            }
            size1 = getStarSize();
            startBall1 = false;
        } else {
            aplhaBall1 = aplhaBall1 > 10 ? aplhaBall1 - 10 : 0;
            ballPaint1.setAlpha(aplhaBall1);
            //根据方向不同，决定向左还是向右
            if (more1) {
                xBall1 += speedBall1;
            } else {
                xBall1 -= speedBall1;
            }
            if (xBall1 <= leftBall1 || xBall1 >= rightBall1) {
                more1 = !more1;
            }
            //是否需要复位
            if (yBall1 > ballTop) {
                yBall1 -= speedBall1;
            } else {
                startBall1 = true;
            }

        }
        canvas.drawCircle(xBall1, yBall1, size1, ballPaint1);
    }

    private void drawBall2(Canvas canvas) {

        if (startBall2) {
            aplhaBall2 = getMyAlpha();
            xBall2 = getStartX();
            yBall2 = getStartY();
            leftBall2 = getTranceLeft();
            rightBall2 = getTranceRight();
            speedBall2 = getSpeed();
            if (xBall2 < leftBall2 || xBall2 > rightBall2) {
                xBall2 = leftBall2;
            }
            size2 = getStarSize();
            startBall2 = false;

        } else {

            aplhaBall1 = aplhaBall1 > 10 ? aplhaBall1 - 10 : 0;
            ballPaint1.setAlpha(aplhaBall1);


            if (more2) {
                xBall2 += speedBall2;
            } else {
                xBall2 -= speedBall2;
            }
            if (xBall2 <= leftBall2 || xBall2 >= rightBall2) {
                more2 = !more2;
            }

            if (yBall2 > ballTop) {
                yBall2 -= speedBall2;
            } else {
                startBall2 = true;
            }

        }


        canvas.drawCircle(xBall2, yBall2, size2, ballPaint2);

    }

    private void drawBall3(Canvas canvas) {

        if (startBall3) {
            aplhaBall3 = getMyAlpha();
            xBall3 = getStartX();
            yBall3 = getStartY();
            leftBall3 = getTranceLeft();
            rightBall3 = getTranceRight();
            speedBall3 = getSpeed();
            if (xBall3 < leftBall3 || xBall3 > rightBall3) {
                xBall3 = leftBall3;
            }
            size3 = getStarSize();
            startBall3 = false;

        } else {

            aplhaBall3 = aplhaBall3 > 10 ? aplhaBall3 - 10 : 0;
            ballPaint3.setAlpha(aplhaBall3);


            if (more3) {
                xBall3 += speedBall3;
            } else {
                xBall3 -= speedBall3;
            }
            if (xBall3 <= leftBall3 || xBall3 >= rightBall3) {
                more3 = !more3;
            }

            if (yBall3 > ballTop) {
                yBall3 -= speedBall3;
            } else {
                startBall3 = true;
            }

        }


        canvas.drawCircle(xBall3, yBall3, size3, ballPaint3);

    }

    private void drawBall4(Canvas canvas) {

        if (startBall4) {
            aplhaBall4 = getMyAlpha();
            xBall4 = getStartX();
            yBall4 = getStartY();
            leftBall4 = getTranceLeft();
            rightBall4 = getTranceRight();
            speedBall4 = getSpeed();
            if (xBall4 < leftBall4 || xBall4 > rightBall4) {
                xBall4 = leftBall4;
            }
            size4 = getStarSize();
            startBall4 = false;

        } else {

            aplhaBall4 = aplhaBall4 > 10 ? aplhaBall4 - 10 : 0;
            ballPaint1.setAlpha(aplhaBall4);


            if (more4) {
                xBall4 += speedBall4;
            } else {
                xBall4 -= speedBall4;
            }
            if (xBall4 <= leftBall4 || xBall4 >= rightBall4) {
                more4 = !more4;
            }

            if (yBall4 > ballTop) {
                yBall4 -= speedBall4;
            } else {
                startBall4 = true;
            }

        }
        canvas.drawCircle(xBall4, yBall4, size4, ballPaint4);

    }

    private void drawBall5(Canvas canvas) {

        if (startBall5) {
            aplhaBall5 = getMyAlpha();
            xBall5 = getStartX();
            yBall5 = getStartY();
            leftBall5 = getTranceLeft();
            rightBall5 = getTranceRight();
            speedBall5 = getSpeed();
            if (xBall5 < leftBall5 || xBall5 > rightBall5) {
                xBall5 = leftBall5;
            }
            size5 = getStarSize();
            startBall5 = false;

        } else {

            aplhaBall5 = aplhaBall5 > 10 ? aplhaBall5 - 10 : 0;
            ballPaint5.setAlpha(aplhaBall5);


            if (more5) {
                xBall1 += speedBall1;
            } else {
                xBall1 -= speedBall1;
            }
            if (xBall1 <= leftBall1 || xBall1 >= rightBall1) {
                more5 = !more5;
            }

            if (yBall1 > ballTop) {
                yBall1 -= speedBall1;
            } else {
                startBall1 = true;
            }

        }
        canvas.drawCircle(xBall1, yBall1, size5, ballPaint1);

    }

    public void setData() {
        postInvalidateDelayed(200);
    }

    /**
     * 获取初始大小
     */
    private int getStarSize() {
        int size = (int) (Math.random() * 30) + 10;
        return size;
    }

    /**
     * 获取初始x,y位置
     */
    private int getStartX() {
        return (int) (Math.random() * mWidth / 2) + mLeft;
    }

    private int getStartY() {
        return (int) (Math.random() * mHeight / 10) + mButtom;
    }

    /**
     * 获取速度，包括的是横向还有竖向
     *
     * @return
     */
    private int getSpeed() {
        return (int) (Math.random() * 20 + 10);
    }

    /**
     * 获取轨道的左右
     */
    private int getTranceLeft() {
        return mLeft + mWidth / 2 - (int) (Math.random() * getWidth() / 4);
    }

    private int getTranceRight() {
        return mLeft + mWidth / 2 + (int) (Math.random() * getWidth() / 4);
    }

    /**
     * 获取初始的透明度
     * 100-255
     */
    private int getMyAlpha() {
        return (int) (Math.random() * 155) + 100;
    }
}
