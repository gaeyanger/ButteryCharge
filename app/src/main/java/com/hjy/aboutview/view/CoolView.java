package com.hjy.aboutview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hjy.aboutview.R;
import com.hjy.aboutview.bean.Point;
import com.hjy.aboutview.callback.CoolViewCallback;
import com.hjy.aboutview.utils.PointEvalutor;

/**
 * Created by windwos on 2016/12/26.
 */

public class CoolView extends View {
    private Paint buttonPaint, ballPaint1, ballPaint2, ballPaint3;
    private int btnSize = 300;
    private final int nomalSize = 300;

    private int mWidth, mHeight, mLeft, mRight, mTop, mButtom, mMiddleWidth, mMiddleHeight;
    private Point currentPoint, ballPoint1, ballPoint2, ballPoint3;
    private Point p1, p2, p3;
    //是否分离，分离即不再draw
    private boolean isDeliver = false;
    //是否已经点击，多次点击无效；
    private boolean isPress = true;
    private CoolViewCallback callBack;
    private Context context;

    public CoolView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CoolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CoolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public CoolView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }

    private void init() {
        TypedArray ta= context.obtainStyledAttributes(R.styleable.CoolView);


        currentPoint = new Point();
        ballPoint1 = new Point();
        ballPoint2 = new Point();
        ballPoint3 = new Point();

        buttonPaint = new Paint();
        buttonPaint.setAntiAlias(true);
        buttonPaint.setColor( ta.getColor(R.styleable.CoolView_buttonColor, ContextCompat.getColor(context,R.color.red)));
        buttonPaint.setStyle(Paint.Style.FILL);

        ballPaint1 = new Paint();
        ballPaint1.setAntiAlias(true);
        ballPaint1.setStyle(Paint.Style.FILL);
        ballPaint1.setColor( ta.getColor(R.styleable.CoolView_firstColor,ContextCompat.getColor(context,R.color.green)));

        ballPaint2 = new Paint();
        ballPaint2.setAntiAlias(true);
        ballPaint2.setStyle(Paint.Style.FILL);
        ballPaint2.setColor( ta.getColor(R.styleable.CoolView_secondColor,ContextCompat.getColor(context,R.color.blue)));

        ballPaint3 = new Paint();
        ballPaint3.setAntiAlias(true);
        ballPaint3.setStyle(Paint.Style.FILL);
        ballPaint3.setColor( ta.getColor(R.styleable.CoolView_thirdColor,ContextCompat.getColor(context,R.color.gray)));
        ta.recycle();

    }

    /**
     * 回调
     *
     * @param callBack
     */
    public void addCallBack(CoolViewCallback callBack) {
        this.callBack = callBack;
    }

    /**
     * 把对应的三个球的位置发过啦
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public void addPoint(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
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
        mMiddleWidth = (mLeft + mRight) / 2;
        mMiddleHeight = (mButtom + mTop) / 2;

        currentPoint.setX(mMiddleWidth);
        currentPoint.setY(mMiddleHeight);

        ballPoint1.setX(mMiddleWidth);
        ballPoint1.setY(mMiddleHeight);

        ballPoint2.setX(mMiddleWidth);
        ballPoint2.setY(mMiddleHeight);

        ballPoint3.setX(mMiddleWidth);
        ballPoint3.setY(mMiddleHeight);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isDeliver) {
            Log.d("TAG", "x1 = " + ballPoint1.getX() + "&&y1 = " + ballPoint1.getY());
            Log.d("TAG", "x2 = " + ballPoint2.getX() + "&&y2 = " + ballPoint2.getY());
            Log.d("TAG", "x3 = " + ballPoint3.getX() + "&&y3 = " + ballPoint3.getY());
            canvas.drawCircle(ballPoint1.getX(), ballPoint1.getY(), btnSize, ballPaint1);
            canvas.drawCircle(ballPoint2.getX(), ballPoint2.getY(), btnSize, ballPaint2);
            canvas.drawCircle(ballPoint3.getX(), ballPoint3.getY(), btnSize, ballPaint3);
        } else {
            canvas.drawCircle(currentPoint.getX(), currentPoint.getY(), btnSize, buttonPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (isPress && x > (mMiddleWidth - nomalSize / 2) && x < (mMiddleWidth + nomalSize / 2) && y > (mMiddleHeight - nomalSize / 2) && y < (mMiddleHeight + nomalSize / 2)) {
                    startAnim();
                    isPress = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void startAnim() {
        Log.d("TAG", p1.getY() + "--" + p2.getY() + "--" + p3.getY());
        AnimatorSet animSet = new AnimatorSet();
        final ValueAnimator animator = ValueAnimator.ofInt(nomalSize, nomalSize / 4, nomalSize / 2, nomalSize / 4);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                btnSize = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isDeliver = true;
            }
        });
        animator.setDuration(3000);

        //ball1
        ValueAnimator ballAnim1 = ValueAnimator.ofObject(new PointEvalutor(), new Point(mMiddleWidth, mMiddleHeight), p1);
        ballAnim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ballPoint1 = (Point) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        ballAnim1.setDuration(2000);

        ValueAnimator ballAnim2 = ValueAnimator.ofObject(new PointEvalutor(), new Point(mMiddleWidth, mMiddleHeight), p2);
        ballAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ballPoint2 = (Point) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        ballAnim2.setDuration(2000);

        ValueAnimator ballAnim3 = ValueAnimator.ofObject(new PointEvalutor(), new Point(mMiddleWidth, mMiddleHeight), p3);
        ballAnim3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ballPoint3 = (Point) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        ballAnim3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (callBack != null) {
                    callBack.hideView();
                }

            }
        });
        ballAnim3.setDuration(2000);

        animSet.play(ballAnim3).with(ballAnim2).with(ballAnim1).after(animator);
        animSet.start();

    }
}
