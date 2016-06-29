package com.example.administrator.myapplication.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.parallax.ParallaxAdapter;
import com.example.administrator.myapplication.parallax.ParallaxRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AutomaticRefreshActivity extends BaseActivity {

    @InjectView(R.id.m_recycler_view) ParallaxRecyclerView mRecyclerView;
    @InjectView(R.id.test_recycler_view_frame) PtrClassicFrameLayout testRecyclerViewFrame;
    private RecyclerAdapterWithHF mHFAdapter;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automatic_refresh);
        ButterKnife.inject(this);
        List<String> mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("test");
        }
        ParallaxAdapter mAdapter = new ParallaxAdapter(mContext, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHFAdapter = new RecyclerAdapterWithHF(mAdapter);
        mRecyclerView.setAdapter(mAdapter);
        testRecyclerViewFrame.setLoadMoreEnable(false);
        testRecyclerViewFrame.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        testRecyclerViewFrame.refreshComplete();

                    }
                }, 2000);
            }
        });
    }
}
