package com.hjy.aboutview.utils;

import android.animation.TypeEvaluator;

import com.hjy.aboutview.bean.Point;

/**
 * Created by windwos on 2016/12/26.
 */

public class PointEvalutor implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startP = (Point) startValue;
        Point endP = (Point) endValue;
        int x = startP.getX() + (int) (fraction * (endP.getX()-startP.getX()));
        int y = startP.getY() + (int) (fraction * (endP.getY()-startP.getY()));

        return new Point(x, y);
    }
}
