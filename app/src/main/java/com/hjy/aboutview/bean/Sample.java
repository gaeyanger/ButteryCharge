package com.hjy.aboutview.bean;

/**
 * Created by hjy on 2016/12/25.
 */

public class Sample {
    private int Color;
    private String ties;

    public Sample() {

    }

    public Sample(int color, String ties) {
        this.Color = color;
        this.ties = ties;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public String getTies() {
        return ties;
    }

    public void setTies(String ties) {
        this.ties = ties;
    }
}
