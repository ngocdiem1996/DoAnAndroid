package com.ngocdiem.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ngocdiem.map.Sign_In.Login.MainMainActivity;

public class SplashScreen extends AppCompatActivity {
    private Animation mAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAnimation = AnimationUtils.loadAnimation(this,R.anim.animation_blink);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                    ImageView imageView = (ImageView)findViewById(R.id.img_map);
                    imageView.startAnimation(mAnimation);
                    sleep(3000);
                } catch (Exception e) {
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainMainActivity.class);//login
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}
