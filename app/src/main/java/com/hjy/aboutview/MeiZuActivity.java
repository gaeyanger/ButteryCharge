package com.hjy.aboutview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import com.hjy.aboutview.view.MeiZuView;

/**
 * Created by windwos on 2016/12/27.
 */

public class MeiZuActivity extends AppCompatActivity {

    private MeiZuView meiZuView;
    private boolean isRun = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_meizu);

        Transition slide = TransitionInflater.from(this).inflateTransition(R.transition.slide);
        Transition slide2 = TransitionInflater.from(this).inflateTransition(R.transition.slide);

        getWindow().setEnterTransition(slide);
        getWindow().setExitTransition(slide2);
        init();
    }

    private void init() {
        meiZuView = (MeiZuView)findViewById(R.id.mv);
        new Thread(new Runnable() {
            public void run() {
                synchronized (meiZuView) {

                    while (isRun) {
                        Message msg;
                        msg = Message.obtain();
                        SystemClock.sleep(200);
                        msg.what = 1;
                        handler.sendMessage(msg);

                    }
                }
            }

        }).start();

    }


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 1:
                    meiZuView.setData();
                    break;
                default:
                    break;
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRun = false;
    }
}


