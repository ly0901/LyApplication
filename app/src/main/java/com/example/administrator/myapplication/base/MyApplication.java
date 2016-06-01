package com.example.administrator.myapplication.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;



public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private Context mContext;
    public static int width;  // 屏幕宽度（像素）
    public static int height;  // 屏幕高度（像素）

    Object tag = new Object();

    public Object getTag() {
        return tag;
    }

    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        return mInstance;
    }

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = this;
        //启动的顺序不可以变--JPush测试的时候模拟器要注释掉

        initAppInfo();

        initJPush();

        initDevices();

        initBugly();

        //initHawk();

        initImageLoader();

        //LogerUtil.ee(MD5Util.getMD5("123456"));
        //initBugtags();

//        try {
//            LogerUtil.ee("-------------------->", AESUtil.encrypt(MD5Util.MD5KEY, "12345"));
//            LogerUtil.ee("-------------------->", Encrypt.toHex(Encrypt.Aes256Encode("12345", AES256Encryption.initkey())));
//            LogerUtil.ee("-------------------->", ToolsAesCrypt.Encrypt("12345", MD5Util.MD5KEY));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void initBugtags() {
        //在这里初始化
        //Bugtags.start("ac155addc3164a4907341a21574fb4de", this, Bugtags.BTGInvocationEventNone);
    }


    private void initImageLoader() {
    }

    public void initAppInfo() {

    }

    private void initJPush() {
    }

    private void initHawk() {
//        Hawk.init(mContext, "@WSXAQ8!#EDC8", new Hawk.Callback() {
//                    @Override
//                    public void onSuccess() {
//                        LogerUtil.ee("Hawk 初始化 Success");
//                    }
//
//                    @Override
//                    public void onFail(Exception e) {
//                        LogerUtil.ee("Hawk 初始化 Fail");
//                    }
//                }
//        );
    }

    private void initDevices() {
        try {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            width = displayMetrics.widthPixels;
            height = displayMetrics.heightPixels;

            int dpi = displayMetrics.densityDpi;

        } catch (Exception e) {
//            CommonUtil.postException(e);
        }
    }

    private void initBugly() {
    }


}
