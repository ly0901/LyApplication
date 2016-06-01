package com.example.administrator.myapplication.utils;


import android.util.Log;

/**
 * 发布状态去除log日志
 *
 * @author anfs
 * @time 2014-7-27
 */
public class LogerUtil {

    private static final boolean isPrint = true;
    private static String defaultTag = "<===LogerUtil===>";

    private LogerUtil() {
    }

    public static void setTag(String tag) {
        defaultTag = tag;
    }

    public static int i(String m) {
        return isPrint && m != null ? Log.i(defaultTag, m) : -1;
    }

    public static boolean isIsPrint() {
//        if (Bu.ildConfig.DEBUG && Log.isLoggable(tag, Log.VERBOSE)) {
//            Log.v(tag, message);
//        }
        return Log.isLoggable(defaultTag, Log.VERBOSE);
    }

    /**
     * ******************** Log **************************
     */
    public static int v(String tag, String msg) {
        return isPrint && msg != null ? Log.v(tag, msg) : -1;
    }

    public static int d(String tag, String msg) {
        return isPrint && msg != null ? Log.d(tag, msg) : -1;
    }

    public static int i(String tag, String msg) {
        return isPrint && msg != null ? Log.i(tag, msg) : -1;
    }

    public static int w(String tag, String msg) {
        return isPrint && msg != null ? Log.w(tag, msg) : -1;
    }

    public static int e(String tag, String msg) {
        return isPrint && msg != null ? Log.e(tag, msg) : -1;
    }

    /**
     * ******************** Log with object list **************************
     */
    public static int v(String tag, Object... msg) {
        return isPrint ? Log.v(tag, getLogMessage(msg)) : -1;
    }

    public static int d(String tag, Object... msg) {
        return isPrint ? Log.d(tag, getLogMessage(msg)) : -1;
    }

    public static int i(String tag, Object... msg) {
        return isPrint ? Log.i(tag, getLogMessage(msg)) : -1;
    }

    public static int w(String tag, Object... msg) {
        return isPrint ? Log.w(tag, getLogMessage(msg)) : -1;
    }

    public static int e(String tag, Object... msg) {
        return isPrint ? Log.e(tag, getLogMessage(msg)) : -1;
    }


    /**
     * ******************** Log with object list **************************
     */
    public static int vv(Object... msg) {
        return isPrint ? Log.v(defaultTag, getLogMessage(msg)) : -1;
    }

    public static int dd(Object... msg) {
        return isPrint ? Log.d(defaultTag, getLogMessage(msg)) : -1;
    }

    public static int ii(Object... msg) {
        return isPrint ? Log.i(defaultTag, getLogMessage(msg)) : -1;
    }

    public static int ww(Object... msg) {
        return isPrint ? Log.w(defaultTag, getLogMessage(msg)) : -1;
    }

    public static int ee(Object... msg) {
        return isPrint ? Log.e(defaultTag, getLogMessage(msg)) : -1;
    }

    private static String getLogMessage(Object... msg) {
        if (msg != null && msg.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object s : msg) {
                if (s != null) sb.append("  ").append(s.toString());
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * ******************** Log with Throwable **************************
     */
    public static int v(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? Log.v(tag, msg, tr) : -1;
    }

    public static int d(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? Log.d(tag, msg, tr) : -1;
    }

    public static int i(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? Log.i(tag, msg, tr) : -1;
    }

    public static int w(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? Log.w(tag, msg, tr) : -1;
    }

    public static int e(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? Log.e(tag, msg, tr) : -1;
    }

    /**
     * ******************** TAG use Object Tag **************************
     */
    public static int v(Object tag, String msg) {
        return isPrint ? Log.v(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int d(Object tag, String msg) {
        return isPrint ? Log.d(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int i(Object tag, String msg) {
        return isPrint ? Log.i(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int w(Object tag, String msg) {
        return isPrint ? Log.w(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int e(Object tag, String msg) {
        return isPrint ? Log.e(tag.getClass().getSimpleName(), msg) : -1;
    }
}
