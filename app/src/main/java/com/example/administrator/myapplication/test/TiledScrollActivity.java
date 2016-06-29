package com.example.administrator.myapplication.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.tiledscrollview.ConfigurationSet;
import com.example.administrator.myapplication.tiledscrollview.TiledScrollView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TiledScrollActivity extends AppCompatActivity {

    @InjectView(R.id.tiledScrollView) TiledScrollView tiledScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiled_scroll);
        ButterKnife.inject(this);
        tiledScrollView.addConfigurationSet(TiledScrollView.ZoomLevel.LEVEL_1,
                new ConfigurationSet("tiger800/crop_%col%_%row%.png", 100, 100, 2400, 1800));
        tiledScrollView.addConfigurationSet(TiledScrollView.ZoomLevel.LEVEL_2,
                new ConfigurationSet("tiger1600/crop_%col%_%row%.png", 100, 100, 4800, 3600));
    }
}
