package com.example.administrator.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.myapplication.utils.CommonUtil;

import java.util.HashMap;


public class BaseActivity extends FragmentActivity {
    protected Context mContext;
    protected Activity mActivity;
    protected LayoutInflater mInflater;

    protected final int mPageSize = 10;
    protected final int FIRST_PAGE_NO = 1;
    protected int mPageNo = FIRST_PAGE_NO;
    protected int mTotalNo = 0;
    protected boolean mIsLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        mActivity = this;
        mInflater = LayoutInflater.from(this);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        try {
            //Bugtags.onDispatchTouchEvent(this, ev);
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (shouleHideIM(v, ev)) {
                    CommonUtil.hideIME(mContext, v);
                    hideIMECallBack();
                }
            }
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    protected void hideIMECallBack() {
    }

    protected boolean shouleHideIM(View v, MotionEvent ev) {
        if (null != v && v instanceof EditText) {

            // 获取当前edittext在屏幕中的位置
            int[] location = {0, 0};
            v.getLocationInWindow(location);// x:location[0] y:location[1]
            int left = location[0], top = location[1];
            int bottom = top + v.getHeight(), right = left + v.getWidth();

            // 判断点击时间是否在输入框区域，true就不隐藏，false代表发生在区域外，需隐藏软键盘
            return !(ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom);
        }
        return false;
    }

    protected void toast(String message) {
        CommonUtil.toast(mContext, message);
    }

    protected void toast(String message, int resId) {
        CommonUtil.toast(mContext, message, resId);
    }


    protected void toast(int message, int resId) {
        CommonUtil.toast(mContext, message, resId);
    }


    protected void toast(Object message) {
        toast(String.valueOf(message));
    }

    protected void toast(int resid) {
        toast(getString(resid));
    }

    /**
     * 跳转到指定的Activity 默认界面
     *
     * @param cls
     */
    protected void startNewActivity(Class<?> cls) {
        startNewActivity(cls, false);
    }


    /**
     * 跳转到指定的Activity
     *
     * @param cls
     */
    protected void startNewActivity(Class<?> cls, boolean isFinish) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        if (isFinish) {
            this.finish();
        }
    }

    /**
     * 跳转到指定的Activity
     *
     * @param cls
     */
    protected void startNewActivityForResult(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivityForResult(intent, 0);
    }

    protected HashMap<String, Object> getParams() {
        return new HashMap<String, Object>();
    }




}
