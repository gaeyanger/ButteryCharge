package com.hjy.aboutview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import com.hjy.aboutview.view.VivoView;

/**
 * Created by windwos on 2016/12/27.
 */

public class ViVoActivity extends AppCompatActivity {
    private VivoView vivoView;
    private boolean isRun = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_vivo);
        Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explored);
        Transition fade = TransitionInflater.from(this).inflateTransition(R.transition.fade);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(fade);
        init();
    }

    private void init() {
        vivoView = (VivoView) findViewById(R.id.vivo);
        new Thread(new Runnable() {
            public void run() {
                synchronized (vivoView) {

                    while (isRun) {
                        Message msg;
                        for (int i = 0; i < n2; i = i + 10) {
                            msg = Message.obtain();
                            msg.obj = i;
                            SystemClock.sleep(100);
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }
                        msg = Message.obtain();
                        msg.what = 2;
                        handler.sendMessage(msg);
                    }
                }
            }
        }).start();

    }

    int n2 = 2;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 1:
                    int a = (Integer) msg.obj;
                    vivoView.setData(a, a);
                    break;
                case 2:
                    n2 = 359;
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


