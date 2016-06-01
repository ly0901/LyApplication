package com.example.administrator.myapplication.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.administrator.myapplication.base.MyApplication;


public class DensityUtil {
    public static final int LDPI = 120;
    public static final int MDPI = 160;
    public static final int HDPI = 240;
    public static final int XHDPI = 320;
    public static final int DPI480 = 480;
    public static final int XXHDPI = DPI480;
    public static final int XXXHDPI = 640;
    public static final int DPI1080 = 1080;
    public static final int DPI2560 = 1440;
    public static final int DPI720 = 720;

    private static Context mContext = MyApplication.getInstance();

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        if (mContext == null) {
            return (int) dpValue;
        }
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static DisplayMetrics getScreenDensity() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /**
     * 将dip转换成px
     *
     * @param dip
     * @return
     */
    public static int getPxInt(float dip) {
        return dp2px(dip);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String getDpi() {
        int dpi = MyApplication.getInstance().getResources().getDisplayMetrics().densityDpi;
        String string = "hdpi";
        switch (dpi) {
            case HDPI:
            case LDPI:
            case MDPI:
                string = "hdpi";
                break;
            case XHDPI:
                string = "xhdpi";
                break;
            case XXHDPI:
            case XXXHDPI:
                string = "xxhdpi";
                break;
        }
        return string;
    }

    public static String getDpiDushu() {
        int dpi = MyApplication.getInstance().getResources().getDisplayMetrics().densityDpi;
        String string = "hdpi";
        switch (dpi) {
            case HDPI:
            case LDPI:
            case MDPI:
                string = "480";
                break;
            case XHDPI:
                string = "720";
                break;
            case XXHDPI:
                string = "750";
                break;
            case XXXHDPI:
                string = "1080";
                break;
        }
        return string;
    }

    public static int getDpiInt() {
        int dpi = MyApplication.getInstance().getResources().getDisplayMetrics().densityDpi;
        int string = DPI1080;
        switch (dpi) {
            case HDPI:
            case LDPI:
            case MDPI:
                string = DPI480;
                break;
            case XHDPI:
                string = DPI720;
                break;
            case XXHDPI:
                string = DPI1080;
                break;
            case XXXHDPI:
                string = DPI2560;
                break;
        }
        return string;
    }
}
