package com.hjy.aboutview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.hjy.aboutview.view.SmartView;

/**
 * Created by hjy on 2016/12/27.
 */

public class SmartActivity extends AppCompatActivity {

    private SmartView sv;
    private boolean isRun = true;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_smart);
        init();
    }

    private void init() {
        sv = (SmartView)findViewById(R.id.sv);
        new Thread(new Runnable() {
            public void run() {
                synchronized (sv) {

                    while (isRun) {
                        Message msg;

                        msg = Message.obtain();

                        SystemClock.sleep(1000);
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
                    sv.setData();
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
