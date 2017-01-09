package com.hjy.aboutview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import com.hjy.aboutview.view.VivoView;

/**
 * Created by hjy on 2016/12/27.
 */

public class ViVoActivity extends AppCompatActivity {
    private VivoView vivoView;

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
        vivoView.startAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetachedFromWindow() {
        vivoView.stopAnimation();
        super.onDetachedFromWindow();

    }
}


