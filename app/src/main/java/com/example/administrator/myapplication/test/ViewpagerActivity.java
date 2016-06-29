package com.example.administrator.myapplication.test;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.rollviewpager.RollPagerView;
import com.example.administrator.myapplication.rollviewpager.adapter.StaticPagerAdapter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ViewpagerActivity extends BaseActivity {

    @InjectView(R.id.rollPagerView) RollPagerView rollPagerView;
    @InjectView(R.id.tv_bottom) TextView tvBottom;
    private Timer mTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.inject(this);
        rollPagerView.setAdapter(new TestNomalAdapter());
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (rollPagerView.shouldFinish()) {
                    finish();
                }
            }
        }, 100, 100);
        tvBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    private class TestNomalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.heart,
                R.drawable.heart,
                R.drawable.heart,
                R.drawable.heart,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
