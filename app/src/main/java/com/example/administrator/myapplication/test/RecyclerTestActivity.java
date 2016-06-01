package com.example.administrator.myapplication.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.RecycleItemModel;
import com.example.administrator.myapplication.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecyclerTestActivity extends BaseActivity {

    @InjectView(R.id.m_recycler_view) RecyclerView mRecyclerView;
    @InjectView(R.id.tv_top) TextView tvTop;
    @InjectView(R.id.tv_bottom) TextView tvBottom;
    private MessageRecycleAdapter mAdapter;
    protected Handler handler;
    private LinearLayoutManager mLayoutManager;
    private boolean isLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_test);
        ButterKnife.inject(this);
        init();
        initData();
        initLister();
    }


    private void init() {
        handler = new Handler();
        //创建布局管理器
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MessageRecycleAdapter(mContext, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mAdapter.clearAll();
        RecycleItemModel itemBody = new RecycleItemModel(RecycleItemModel.TYPE_BODY, "top");
        List<RecycleItemModel> itemBodys = new ArrayList<RecycleItemModel>();
        for (int i = 0; i < 25; i++) {
            itemBodys.add(itemBody);
        }
        mAdapter.addAll(itemBodys);
    }
    private RelativeLayout.LayoutParams params;
    private void initLister() {
        params = (RelativeLayout.LayoutParams) tvTop.getLayoutParams();
        int  hideHeaderHeight = -DensityUtil.dp2px(100);
        params.topMargin = hideHeaderHeight;
        tvTop.setLayoutParams(params);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(0==newState){
                    params = (RelativeLayout.LayoutParams) tvTop.getLayoutParams();
                    int  hideHeaderHeight = -DensityUtil.dp2px(100);
                    params.topMargin = hideHeaderHeight;
                    tvTop.setLayoutParams(params);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                int totalCount = mAdapter.getItemCount();
                if (!isLoading && lastVisibleItem == totalCount - 1 && dy > 0) {
                    loadMore();
                }
                toast("  dy :" + dy);
                if (!isLoading && 0 == mLayoutManager.findFirstVisibleItemPosition()&& dy > 0) {
                    toast("dy+" +dy);
                    int  hideHeaderHeight = -DensityUtil.dp2px(Math.abs(100+dy));
                    params.topMargin = hideHeaderHeight;
                    tvTop.setLayoutParams(params);
                }
            }


        });
    }

    private void loadMore() {
        isLoading = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RecycleItemModel itemBody = new RecycleItemModel(RecycleItemModel.TYPE_BODY, "top");
                List<RecycleItemModel> itemBodys = new ArrayList<RecycleItemModel>();
                for (int i = 0; i < 25; i++) {
                    itemBodys.add(itemBody);
                }
                mAdapter.addAll(itemBodys, mAdapter.getItemCount() - 1);
                isLoading = false;
            }
        }, 2000);
    }

    private void reFresh() {
        isLoading = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                isLoading = false;
            }
        }, 2000);

    }
}
