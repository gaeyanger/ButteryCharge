package com.hjy.aboutview.utils;

import android.app.Activity;


import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ToActivity {

    public static void toActivity(Context context, Class<?> class1) {
        Intent intent = new Intent();
        intent.setClass(context, class1);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    // 传一个参数
    public static void toActivity(Context context, Class<?> class1, String str) {
        Intent intent = new Intent();
        intent.setClass(context, class1);
        intent.putExtra("str", str);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public static void toActivityForList(Context context, Class<?> class1, ArrayList<String> list) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setClass(context, class1);
        intent.putStringArrayListExtra("strList", list);
        context.startActivity(intent);
    }

    // 传二个参数
    public static void toActivity(Context context, Class<?> class1, String str,
                                  String str1) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setClass(context, class1);
        intent.putExtra("str", str);
        intent.putExtra("str1", str1);
        context.startActivity(intent);
    }

    // 传三个参数
    public static void toActivity(Context context, Class<?> class1, String str,
                                  String str2, String str3) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setClass(context, class1);
        intent.putExtra("str", str);
        intent.putExtra("str2", str2);
        intent.putExtra("str3", str3);
        context.startActivity(intent);
    }

    // 传对象
    public static void toActivity(Context context, Class<?> class1,
                                  Object object) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setClass(context, class1);
        Bundle bundle = new Bundle();
        bundle.putSerializable("entity", (Serializable) object);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void toActivityForResult(Context context, Class<?> class1, String str, String data) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setClass(context, class1);
        intent.putExtra("str", str);
        intent.putExtra("data", data);
        ((Activity) context).startActivityForResult(intent, 0);
    }

    public static void toActivityForResult(Context context, Class<?> class1, int resultCode) {
        Intent intent = new Intent(context, class1);
        ((Activity) context).startActivityForResult(intent, resultCode);
    }

    /**
     * @Title toActivity
     * @Description TODO
     * @Author Hollow
     * @Date 2015-10-22 ����11:05:59
     */
    public static void toPhotoActivity(Context context, Class<?> class1, int str) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setClass(context, class1);
        intent.putExtra("whereFrom", str);
        context.startActivity(intent);
    }

    /**
     * 自定義倆個傳兩個參數
     *
     * @param context
     * @param class1
     * @param parmsKey1
     * @param parmsValue1
     * @param parmsKey2
     * @param parmValue2
     */
    public static void toActivity(Context context, String intentName, String parmsKey1,
                                  String parmsValue1, String parmsKey2, int parmValue2) {
        Intent intent = new Intent(intentName);
//			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//			intent.setClass(context, class1);
        intent.putExtra(parmsKey1, parmsValue1);
        intent.putExtra(parmsKey2, parmValue2);
        context.startActivity(intent);
    }


}
