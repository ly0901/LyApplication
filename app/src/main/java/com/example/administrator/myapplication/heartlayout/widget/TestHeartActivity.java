package com.example.administrator.myapplication.heartlayout.widget;

import android.graphics.Color;
import android.os.Bundle;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TestHeartActivity extends BaseActivity {
    private Random mRandom = new Random();
    private Timer mTimer = new Timer();
    private HeartLayout mHeartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_heart);
        mHeartLayout = (HeartLayout) findViewById(R.id.heart_layout);
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHeartLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mHeartLayout.addHeart(randomColor());
                    }
                });
            }
        }, 1000, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    private int randomColor() {
        return Color.rgb(mRandom.nextInt(255), mRandom.nextInt(255), mRandom.nextInt(255));
    }
}
