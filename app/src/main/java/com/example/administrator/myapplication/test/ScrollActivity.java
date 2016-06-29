package com.example.administrator.myapplication.test;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.scrollview.ScrollAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ScrollActivity extends BaseActivity {

    @InjectView(R.id.m_recycler_view) RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    ScrollAdapter scrollAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        ButterKnife.inject(this);
        init();
        initListener();
    }

    private void initListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    scrollAdapter.setAdapterTranslation(10);
                }else{
                    scrollAdapter.setAdapterTranslation(-10);
                }
            }
        });
    }

    private void init() {
        //创建布局管理器
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("aa");
        }
        scrollAdapter = new ScrollAdapter(mContext,stringList);
        mRecyclerView.setAdapter(scrollAdapter);
    }
}
