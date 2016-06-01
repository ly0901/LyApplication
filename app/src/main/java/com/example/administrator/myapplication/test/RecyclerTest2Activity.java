package com.example.administrator.myapplication.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.base.RecycleItemModel;
import com.github.captain_miao.recyclerviewutils.common.BaseLoadMoreFooterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class RecyclerTest2Activity extends BaseActivity {

    @InjectView(R.id.tv_top) TextView tvTop;
    @InjectView(R.id.tv_bottom) TextView tvBottom;
    @InjectView(R.id.m_recycler_view) RecyclerView mRecyclerView;
    @InjectView(R.id.material_style_ptr_frame) PtrFrameLayout materialStylePtrFrame;
    private SimpleAdapter mAdapter;
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
        mAdapter = new SimpleAdapter(new ArrayList<String>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setLoadMoreFooterView(new BaseLoadMoreFooterView(this) {
            @Override
            public int getLoadMoreLayoutResource() {
                return R.layout.item_bottom;
            }
        });

    }

    private void initData() {
        initMockData();
    }
    private void initMockData(int count){
        for (int i = 0; i < count; i++) {
            mAdapter.appendToTop("1 page -> " + mAdapter.getItemCount() + "");
        }
    }
    private void initMockData() {
        initMockData(25);
    }
    private void initLister() {
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
