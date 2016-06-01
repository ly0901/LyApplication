package com.example.administrator.myapplication.utils;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.MyApplication;
import com.orhanobut.logger.Logger;

import org.apache.http.Header;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class CommonUtil {
    private static Context mContext = MyApplication.getInstance();
    public static boolean isJsonFormat = true;/// BuildConfig.IS_FORMAT_JSON_LOG;

    /**
     * 是否隐藏
     */
    public static void isHideView(boolean isHide, View view) {
        if (view == null) {
            return;
        }
        view.setVisibility(isHide ? View.GONE : View.VISIBLE);
    }

    /**
     * 是否显示
     */
    public static void isShowView(boolean isShow, View view) {
        if (view == null) {
            return;
        }
        view.setVisibility(!isShow ? View.GONE : View.VISIBLE);
    }

    /**
     * 隐藏视图
     */
    public static void hideViews(View... views) {
        try {
            if (views == null || views.length == 0) {
                return;
            }
            for (View view : views) {
                if (view == null) {
                    continue;
                }
                view.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            CommonUtil.postException(e);
        }
    }

    /**
     * 显示视图
     */
    public static void showViews(View... views) {
        try {
            if (views == null || views.length == 0) {
                return;
            }
            for (View view : views) {
                if (view == null) {
                    continue;
                }
                view.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            CommonUtil.postException(e);
        }
    }

    public static void executeOutAnim(Activity activity) {
        //activity.overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
    }

    public static void executeInAnim(Activity activity) {
        //activity.overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
    }

    public static boolean isEmptyString(String str) {
        return null == str || "".equals(str);
    }

//    public static boolean isValidCode(String code) {
//        if (StringUtils.isEmpty(code)) {
//            toast("验证码不能为空");
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean isValidPhone(String phone) {
//        if (StringUtils.isEmpty(phone)) {
//            toast("手机号码不能为空");
//            return false;
//        }
//        if (!(Pattern.matches(("[1][3456789][0-9]{9}$"), phone))) {
//            toast("请输入正确的手机号码");
//            return false;
//        }
//        return true;
//    }

    /**
     * 隐藏输入法
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void hideIME(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

//    /**
//     * 使用picasso开源框架加载网络图片
//     */
//    public static void displayImg(String imgLink, ImageView targetView) {
//        Picasso picasso = Picasso.with(mContext);
//        picasso.load(imgLink).fit().centerInside().into(targetView);
//        //		clearCache();.skipMemoryCache()
//        //		picasso.setIndicatorsEnabled(true);
//    }

    /**
     * 设置textview的字体颜色
     *
     * @param resourceId
     * @param views
     */
    public static void setTextColor(int resourceId, TextView... views) {
        for (TextView tv : views) {
            String str = tv.getText().toString().trim();
            if (TextUtils.isEmpty(str)) {
                continue;
            }
            tv.setTextColor(resourceId);
        }
    }

    public static int getTextColor(int resourceId) {
        return mContext.getResources().getColor(resourceId);
    }

    /**
     * 将int转化为long类型
     */
    public static Long transIntToLong(Object obj) {
        return ((Integer) obj).longValue();
    }

    /**
     * 改变字体
     */
    public static void changeTypeface(TextView view) {
        view.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/swatch.TTF"));
    }

    public static void setDiffColorText(TextView view, String text, int color, int start, int end) {
        SpannableString span = new SpannableString(text);
        span.setSpan(new ForegroundColorSpan(color), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(span);
    }

    /**
     * 为TextView设置大小不一的字体
     */
    public static void setDiffSizeText(String text, TextView view, Map<Integer, Integer[]> data,//
                                       Integer... params) {
        Spannable span = new SpannableString(text); // "大字小字
        //		span.setSpan(new TextAppearanceSpan(family, style, size, color, linkColor), start, end, flags);
        for (Integer res : params) {
            Integer[] offset = data.get(res);
            AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(res); // 大小
            span.setSpan(sizeSpan, offset[0], offset[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        view.setText(span);
    }

    /**
     * 格式化货币
     */
    public static String formatCurrency(BigDecimal price) {
        return formatNum(price).toString();
    }

    public static BigDecimal multiply100(String data) {
        return formatNum(new BigDecimal(data).multiply(new BigDecimal(100)));
    }

    public static BigDecimal divide100(String data) {
        return formatNum(new BigDecimal(data).divide(new BigDecimal(100)));
    }

    public static BigDecimal multiply100(BigDecimal data) {
        return formatNum(data.multiply(new BigDecimal(100)));

    }

    public static BigDecimal divide100(BigDecimal data) {
        return formatNum(data.divide(new BigDecimal(100)));
    }

    /**
     * 格式化数量
     */
    public static BigDecimal formatNum(BigDecimal num) {
        return num.setScale(1, BigDecimal.ROUND_DOWN);
    }

    /**
     * 带星期
     */
    public static String formatFullDate(Date date) {
        return DateFormat.getDateInstance(DateFormat.FULL).format(date);
    }

    /**
     * 带星期
     */
    public static String formatShortDate(Date date) {
        return DateFormat.getDateInstance(DateFormat.SHORT).format(date);
    }

    /**
     * 年月日时分秒
     */
    public static String formatLongDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 月
     */
    public static String formatYueDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return sdf.format(date);
    }

    /**
     * 月日时分
     */
    public static String formatYueRiShiFenDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(date);
    }

    /**
     * 年月日
     */
    public static String formatNianYueRiDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String formatChineseDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(date);
    }

    /**
     * 月日
     */
    public static String formatYueRiDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(date);
    }

    /**
     * 获取星期的全称（例如：星期一）
     */
    public static String getFullWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 获取星期的简称（例如：一）
     */
    public static String getShortWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekDay = c.get(Calendar.DAY_OF_WEEK); // 范围 1~7
        return getShortWeekStr(weekDay + "");
    }

    public static String getShortWeekStr(String str) {
        if ("1".equals(str)) {
            str = "日";
        } else if ("2".equals(str)) {
            str = "一";
        } else if ("3".equals(str)) {
            str = "二";
        } else if ("4".equals(str)) {
            str = "三";
        } else if ("5".equals(str)) {
            str = "四";
        } else if ("6".equals(str)) {
            str = "五";
        } else if ("7".equals(str)) {
            str = "六";
        }
        return str;
    }

    public static String splitWidthSeperatorByIndex(String s, String sep, int index) {
        StringBuilder sb = new StringBuilder(s);
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            //			if (sb.charAt(i) == '\n') {
            //				count = 0;
            //			}
            if (count == index) {
                sb.insert(i, sep);
                count = 0;
            } else {
                count++;
            }
        }
        return sb.toString();
    }

    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate, boolean minusOne) {
        List<Date> list = new ArrayList<Date>();

        // 没有结束时间
        if (endDate == null) {
            list.add(beginDate);
            return list;
        }

        // 开始时间等于结束时间-1，则集合返回开始时间
        if (minusOne) {
            endDate = new Date(endDate.getTime() - Constants.ONE_DAY);
        }
        if (beginDate.compareTo(endDate) >= 0) {
            list.add(beginDate);
            return list;
        }

        // 把开始时间加入集合
        list.add(beginDate);

        // 设置 Calendar 的起始时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            calendar.add(Calendar.DAY_OF_MONTH, 1); // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            if (endDate.after(calendar.getTime())) {// 判断结束时间是否在开始时间之后
                list.add(calendar.getTime());
            } else {
                break;
            }
        }
        list.add(endDate);// 把结束时间加入集合
        return list;
    }

    /**
     * 从sharedpreferences中获取数据
     */
    public static String getBySp(String key) {
        return mContext.getSharedPreferences(Constants.USER_TOKEN, Context.MODE_PRIVATE).getString(key, null);
    }

    public static String getBySp(String file, String key) {
        return mContext.getSharedPreferences(file, Context.MODE_PRIVATE).getString(key, null);
    }

    /**
     * 保存数据到默认的文件中
     */
    public static void saveBySp(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(Constants.USER_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void saveBySp(String file, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(file, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 保存到指定的文件中
     */
    public static void saveBySp(String file, Map<String, Object> map) {
        SharedPreferences sp = mContext.getSharedPreferences(file, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            editor.putString(entry.getKey(), (String) entry.getValue());
        }
        editor.commit();
    }

    public static void clearBySp() {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(Constants.USER_TOKEN, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 清除指定数据
     */
    public static void clearBySp(String file) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(file, Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 清除指定数据
     */
    public static void clearBySp(String file, String key) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences(file, Context.MODE_PRIVATE).edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 弹出提示框
     */
    public static void toast(String content) {
        toast(mContext, content);
    }

    /**
     * 弹出提示框
     */
    public static void toast(int resid) {
        toast(mContext, resid);
    }

    /**
     * 弹出提示框
     */
    public static void toast(Context context, String content) {
        //        Toast toast = Toast.makeText(context, content, Toast.LENGTH_LONG);
        //        toast.setGravity(Gravity.CENTER, 0, 0);
        //        toast.show();
        showToast(context, content, -1);
    }

    /*
    提示带图标
     */
    public static void toast(Context context, String message, int icon) {
        showToast(context, message, icon);
    }

    /*
    提示带图标
     */
    public static void toast(Context context, int resid, int icon) {
        showToast(context, context.getString(resid), icon);
    }

    /**
     * 弹出自定义提示框
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void showToast(Context context, String content, int icon) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.view_toast, null);
            TextView title = (TextView) layout.findViewById(R.id.tv_toast);
            title.setText(content);
            title.setCompoundDrawablesWithIntrinsicBounds(0, icon > 0 ? icon : 0, 0, 0);

            if (mToast != null) {
                mToast.cancel();
            }

            mToast = Toast.makeText(context, content, Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.setView(layout);

            mToast.show();
        } catch (Exception e) {
            CommonUtil.postException(e);
        }
    }

    //可以方式重复的点击
    private static Toast mToast;

    /**
     * 弹出提示框
     */
    public static void toast(Context context, int resid) {
        toast(context, context.getString(resid));
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static int generateFiveRandomNum() {
        Random random = new Random();
        return (int) (random.nextDouble() * (100000 - 10000) + 10000);
    }

//    public static String generateImgID() {
//        String mac = NetUtil.getMacAddress("wlan0");
//        if (CheckUtil.isNotEmpty(mac)) {
//            mac = mac.replaceAll(":", "");
//        }
//        return new StringBuilder()
//                .append(mac)
//                .append(System.currentTimeMillis()).append(generateFiveRandomNum()).toString();
//    }


    public static void printSuccessLog(String TAG, Header[] headers, URI uri, String requestParams,
                                       int statusCode, String responseBody, boolean isSuccess) {
        //        if (headers != null) {
        //            String headerStr = "";
        //            for (int i = 0; i < headers.length; i++) {
        //                Logger.d(headerStr + i + ": " + headers[i]);
        //            }
        //        }

//        if (!ConstantUrl.isPrint) {
//            return;
//        }

        try {

            String url = uri.toString();//"http://" + uri.getHost() + uri.getPath() + "?" + uri.getQuery();
            if (isSuccess) {
                Log.w(TAG, "            请求成功");
            } else {
                Log.w(TAG, "            请求失败");
            }
            requestParams = requestParams == null ? "" : requestParams;
            if (isJsonFormat) {
                if (!TextUtils.isEmpty(requestParams)) {
                    Logger.w(TAG, "请求\n\nURL：" + url + "\n返回码：" + statusCode);
                    Logger.json(TAG, requestParams);
                } else {
                    Logger.w(TAG, "请求" + "\n\n无请求参数" + "\nURL：" + url + "\n返回码：" + statusCode);
                }

                Logger.w(TAG, "返值 ");
                Logger.json(TAG, responseBody);

            } else {
                if (!TextUtils.isEmpty(requestParams)) {
                    Logger.w(TAG, "请求\n\nURL：" + url + "\n返回码：" + statusCode + "\n\n参数" + requestParams);
                } else {
                    Logger.w(TAG, "请求\n\nURL：" + url + "\n返回码：" + statusCode);
                }
                Logger.d(TAG, "返值\n\n" + responseBody);
            }
        } catch (Exception e) {
            Logger.e(TAG, "         日志错误啦");
            e.printStackTrace();
        }
    }

    public static void printSuccessLog(String TAG, Header[] headers, URI uri, String requestParams, int statusCode, String responseBody) {
        printSuccessLog(TAG, headers, uri, requestParams, statusCode, responseBody, true);
    }

    public static void printErrorLog(String TAG, Header[] headers, URI uri, String requestParams, int statusCode, String responseBody) {
        printSuccessLog(TAG, headers, uri, requestParams, statusCode, responseBody, false);
    }

    /**
     * 获取应用程序版本
     */
    public static String getAppVersionName() {
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(mContext.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return ""; // can't reach 肯定不会发生。
        }
    }

    /**
     * 获取客户端操作系统类型
     */
    public static String getClientOsType() {
        return "android";
    }

    /**
     * 获取制造厂商
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机操作系统版本号
     */
    public static String getOsVersion() {
        return "android" + Build.VERSION.RELEASE;
    }

    /**
     * 获得渠道号
     */
    public static String getChannel() {
        String CHANNELID = "000000";
        try {
            ApplicationInfo appInfo = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            CHANNELID = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (Exception e) {
            //
        }
        return CHANNELID;
    }

    public static boolean isEmptyToken() {
        return getBySp("token") == null ? true : false;
    }

    public static Date getDate(Date itemDate, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(itemDate);
        c.add(Calendar.DATE, i);
        return c.getTime();
    }

    public static int getColor(int resid) {
        return mContext.getResources().getColor(resid);
    }

    /**
     * 设置带颜色的 textView
     *
     * @param textView
     * @param money
     */
    public static void setMoney(TextView textView, double money) {
        if (textView == null) {
            return;
        }
        //DecimalFormat decimalFormat = new DecimalFormat(",###0.00");
        //textView.setText(decimalFormat.format(money));
        textView.setText(String.valueOf(money));
        textView.setTextColor( getColor(R.color.green));
    }

    /**
     * 设置带颜色的 textView
     *
     * @param textView
     * @param money
     */
    public static void setMoneyWithText(TextView textView, double money) {
        if (textView == null) {
            return;
        }
        String color = "<font color='" + (money > 0 ? "#FF4E4E" : "#9fff85") + "'>" + money + "<font/>";
        textView.setText(Html.fromHtml(String.format("收支余额%s元", color)));
    }

    private static int displayWidth;

    /**
     * 获取手机屏幕的宽度
     *
     * @return
     */
    public static int getDisplayWidth() {
        if (displayWidth != 0) {
            return displayWidth;
        }
        DisplayMetrics dm = MyApplication.getInstance().getResources().getDisplayMetrics();//new DisplayMetrics();
        return displayWidth = dm.widthPixels;
    }

    /**
     * 获取手机屏幕的宽度
     *
     * @return
     */
    public static int getDisplayHeight() {
        DisplayMetrics dm = MyApplication.getInstance().getResources().getDisplayMetrics();//new DisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 将关键字高亮
     *
     * @param str
     * @param keys
     * @return
     */
    public static SpannableString setSpannable(String str, int color, String... keys) {
        SpannableString spannable = new SpannableString(str);
        try {
            if (keys.length == 0) {
                return spannable;
            }
            CharacterStyle span;
            for (String key : keys) {
                if ("".equals(key) || " ".equals(key)) {
                    continue;
                }
                String[] arrs = str.split(key);
                int length = key.length();
                int start = 0;
                for (int i = 0; i < arrs.length - 1; i++) {
                    String arr = arrs[i];
                    int size = arr.length();
                    span = new ForegroundColorSpan(getColor(color));
                    start += size;
                    spannable.setSpan(span, start, start + length,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    start += length;
                }
                if (str.endsWith(key)) {
                    span = new ForegroundColorSpan(getColor(color));
                    spannable.setSpan(span, str.length() - length , str.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spannable;
    }

    /**
     * 将关键字高亮
     *
     * @param str
     * @return
     */
    public static SpannableString setSpannable2(SpannableString spannable, String str, String color, String key) {
        try {
            if (CheckUtil.isEmpty(str, color, key)) {
                return spannable;
            }
            CharacterStyle span;
            String[] arrs = str.split(key);
            int length = key.length();
            int start = 0;
            for (int i = 0; i < arrs.length - 1; i++) {
                String arr = arrs[i];
                int size = arr.length();
                span = new ForegroundColorSpan(Color.parseColor(color));
                start += size;
                spannable.setSpan(span, start, start + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                start += length;
            }
            if (str.endsWith(key)) {
                span = new ForegroundColorSpan(Color.parseColor(color));
                spannable.setSpan(span, str.length() - length, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spannable;
    }

    /**
     * 将关键字高亮
     *
     * @param str
     * @param keys
     * @return
     */
    public static SpannableString setSpannable(String str, String... keys) {
        return setSpannable(str, R.color.brink_pink, keys);
    }


    /**
     * 隐藏真是姓名替换成 ‘*’ 号
     *
     * @param name
     * @return
     */
    public static String hideUserRealName(String name) {
        char[] charName = name.toCharArray();
        for (int i = 0; i < charName.length; i++) {
            if (i > 0) {
                charName[i] = '*';
            }
        }
        return String.valueOf(charName);
    }

    /**
     * 隐藏姓名中间几个字替换成 ‘*’ 号
     *
     * @param name
     * @return
     */
    public static String hideMiddleRealName(String name) {
        char[] charName = name.toCharArray();
        for (int i = 0; i < charName.length; i++) {
            if (i > 0 && i < charName.length - 1) {
                charName[i] = '*';
            }
        }
        return String.valueOf(charName);
    }

    /**
     * 隐藏真是身份证号码 替换成 ‘*’ 号
     *
     * @param certificateID
     * @return
     */
    public static String hideCertificateID(String certificateID) {
        char[] charName = certificateID.toCharArray();
        for (int i = 0; i < charName.length; i++) {
            if (i > 3 && i < charName.length - 4) {
                charName[i] = '*';
            }
        }
        return String.valueOf(charName);
    }

    /**
     * 隐藏真是手机号 替换成 ‘*’ 号
     *
     * @param phone
     * @return
     */
    public static String hideBindPhone(String phone) {
        char[] charName = phone.toCharArray();
        for (int i = 0; i < charName.length; i++) {
            if (i > 2 && i < charName.length - 4) {
                charName[i] = '*';
            }
        }
        return String.valueOf(charName);
    }


    //逗号分隔 Comma
    //小数    Decimal

    /**
     * String类型格式化为不用逗号分隔,不带小数
     *
     * @param prefix 是否带￥
     * @param amount 金额
     * @return 格式化结果
     * 输入的数据:123456.789
     * 格式化结果:123457
     */
    public static String String2(String prefix, String amount) {
        BigDecimal b = new BigDecimal(amount);
        String s = new DecimalFormat("0").format(b);
        return prefix + s;
    }

    /**
     * String类型格式化为不用逗号分隔,带两位小数
     *
     * @param prefix 是否带￥
     * @param amount 金额
     * @return 格式化结果
     * 输入的数据:123456.789
     * 格式化结果:123456.79
     */
    public static String String2Decimal(String prefix, String amount) {
        BigDecimal b = new BigDecimal(amount);
        String s = new DecimalFormat("0.00").format(b);
        return prefix + s;
    }

    /**
     * String类型格式化为每三位逗号分隔,不带小数
     *
     * @param prefix 是否带￥
     * @param amount 金额
     * @return 格式化结果
     * 输入的数据:123456.789
     * 格式化结果:123,457
     */
    public static String String2Comma(String prefix, String amount) {
        BigDecimal b = new BigDecimal(amount);
        String s = new DecimalFormat(",###,##0").format(b);
        return prefix + s;
    }

    /**
     * String类型格式化为每三位逗号分隔,带两位小数
     *
     * @param prefix 是否带￥
     * @param amount 金额
     * @return 格式化结果
     * 输入的数据:123456.789
     * 格式化结果:123,456.79
     */
    public static String String2CommaDecimal(String prefix, String amount) {
        BigDecimal b = new BigDecimal(amount);
        String s = new DecimalFormat(",###,##0.00").format(b);
        return prefix + s;
    }


    /**
     * Double类型格式化为不用逗号分隔,不带小数
     *
     * @param prefix 是否带￥
     * @param amount 金额
     * @return 格式化结果
     * 输入的数据:123456.789
     * 格式化结果:123457
     */
    public static String Double2(String prefix, Double amount) {
        BigDecimal b = new BigDecimal(amount);
        String s = new DecimalFormat("0").format(b);
        return prefix + s;
    }

    /**
     * Double类型格式化为不用逗号分隔,带两位小数
     *
     * @param amount 金额
     * @return 格式化结果
     * 输入的数据:123456.789
     * 格式化结果:123456.79
     */
    public static String Double2Decimal(Double amount) {
        return Double2Decimal("", amount);
    }

    /**
     * Double类型格式化为不用逗号分隔,带两位小数
     *
     * @param prefix 是否带￥
     * @param amount 金额
     * @return 格式化结果
     * 输入的数据:123456.789
     * 格式化结果:123456.79
     */
    public static String Double2Decimal(String prefix, Double amount) {
        BigDecimal b = new BigDecimal(amount);
        String s = new DecimalFormat("######0.00").format(b);
        return prefix + s;
    }

    /**
     */
    public static String Double2Comma(String amount) {
        BigDecimal b = new BigDecimal(amount);
        return new DecimalFormat("#####0").format(b);
    }

    /**
     * Double类型格式化为每三位逗号分隔,带两位小数
     *
     * @param prefix 是否带￥
     * @param amount 金额
     * @return 格式化结果
     * 输入的数据:123456.789
     * 格式化结果:123,456.79
     */
    public static String Double2CommaDecimal(String prefix, Double amount) {
        BigDecimal b = new BigDecimal(amount);
        String s = new DecimalFormat(",###,##0.00").format(b);
        return prefix + s;
    }


    /**
     * 格式化价格
     *
     * @param price
     * @return
     */
    public static String formatStringPrice(String price) {
        try {
            if (TextUtils.isEmpty(price)){
                return "";
            }
            BigDecimal b = new BigDecimal(price);
            return price.contains(".") ? new DecimalFormat("0.00").format(b) : new DecimalFormat("0").format(b);
        } catch (Exception e) {
            CommonUtil.postException(e);
            return price;
        }
    }

    /**
     * 格式化价格
     *
     * @param price
     * @return
     */
    public static String formatDoublePrice(Double price) {
        return formatStringPrice(String.valueOf(price));
    }

//    /**
//     * 确认对话框
//     *
//     * @param title
//     */
//    public static void confirm(Context context, int title, CallBackListener callBackListener) {
//        confirm(context, context.getString(title), callBackListener);
//    }
//
//    /**
//     * 确认对话框
//     *
//     * @param title
//     */
//    public static void confirm(Context context, String title, String sure, String cancel, final CallBackListener callBackListener) {
//        CustomDialog.getInstance(context).confirm(title, null, sure, cancel, callBackListener);
//    }
//
//    /**
//     * 确认对话框
//     *
//     * @param title
//     */
//    public static void confirm(Context context, String title, final CallBackListener callBackListener) {
//        CustomDialog.getInstance(context).confirm(title, callBackListener);
//    }
//
//    /**
//     * 确认对话框
//     *
//     * @param title
//     */
//    public static void confirmBonus(Context context, String title, final CallBackListener callBackListener) {
//        confirm(context, title, "立即登录", "放弃领取", callBackListener);
//    }
//
//    public static String initDeviceInfo(Context context) {
//        try {
//            org.json.JSONObject json = new org.json.JSONObject();
//            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
//                    .getSystemService(Context.TELEPHONY_SERVICE);
//
//            String device_id = tm.getDeviceId();
//
//            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//            String mac = wifi.getConnectionInfo().getMacAddress();
//
//            if (TextUtils.isEmpty(device_id)) {
//                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//            }
//
//            if (TextUtils.isEmpty(device_id)) {
//                device_id = mac;
//            }
//
//            Constants.MAC_ADDRESS = mac;
//            Constants.DEVICE_ID = device_id;
//            Constants.IMSI = tm.getSubscriberId();
//
//            json.put("mac", Constants.MAC_ADDRESS);
//            json.put("imei", Constants.DEVICE_ID);
//            json.put("imsi", Constants.IMSI);
//
//            return json.toString();
//        } catch (Exception e) {
//            postException(e);
//        }
//        return null;
//    }

    /**
     * 提交异常
     *
     * @param e
     */
    public static void postException(Throwable e) {
        e.printStackTrace();
//        CrashReport.postCatchedException(e);  //把throwable
    }

    public static String getTopActivityName(Context context) {

        String topActivityClassName = null;
        ActivityManager activityManager = (ActivityManager) (context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1);

        if (runningTaskInfos != null) {
            ComponentName topActivityName = runningTaskInfos.get(0).topActivity;
            topActivityClassName = topActivityName.getClassName();
        }
        return topActivityClassName;
    }

    /**
     * 获取字符串的长度 包括中文和英文
     *
     * @param str
     * @return
     */
    public static int getLengthString(String str) {
        try {
            byte[] b = str.getBytes("gb2312");
            return b.length;
        } catch (Exception ex) {
            CommonUtil.postException(ex);
            return 0;
        }
    }

    public static Dialog showLoading(Context context) {
        return showLoading(context, null);
    }

    public static Dialog showLoading(Context context, String message) {
        try {

            if (TextUtils.isEmpty(message)) {
                message = "加载中...";
            }
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.view_custom_progress_dialog, null);
            TextView id_tv_loadingmsg = (TextView) view.findViewById(R.id.id_tv_loadingmsg);
            id_tv_loadingmsg.setText(message);


            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            // 设置大小
            WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
            layoutParams.width = MyApplication.width * 3 / 5;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
            dialog.getWindow().setContentView(view);

            return dialog;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void hideLoading(Dialog progressdialog) {
        try {
            if (progressdialog != null && progressdialog.isShowing()) {
                progressdialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取手机照片的旋转角度
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    //将图片旋转
    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        if (0 != degree) {
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            matrix.postScale(0.5f, 0.5f);

            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
        }
        return bitmap;
    }

    public static byte[] marshall(Parcelable parcelable) {
        Parcel parcel = Parcel.obtain();
        parcelable.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        return parcel.marshall();
    }

    /**
     * 设置金额显示
     * 默认  13 ，18
     *
     * @param text
     * @param money
     */
//    public static void setMoneyText(TextView text, String money) {
//        setMoneyText(text, money, 13, 18);
//    }

    /**
     * 设置金额显示
     *
     * @param text
     * @param money
     * @param rmbSize   ￥ 的大小
     * @param moneySize 具体金额的大小
     */
//    public static void setMoneyText(TextView text, String money, int rmbSize, int moneySize) {
//        text.setText(getSpanPreLast(new SpanModel(Constants.RMB, rmbSize), new SpanModel(money, moneySize)));
//    }
//
//    /**
//     * 设置不同的大小
//     */
//    public static SpannableStringBuilder getSpanPreLast(SpanModel... spanModels) {
//        SpannableStringBuilder spanBuilder = new SpannableStringBuilder();
//        int start = 0;
//        for (SpanModel spanModel : spanModels) {
//            if (spanModel == null || spanModel.text == null) {
//                continue;
//            }
//            spanBuilder.append(spanModel.text);
//            int spnLength = spanModel.text.length();
//            spanBuilder.setSpan(new AbsoluteSizeSpan(spanModel.size, true), start, start + spnLength, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
//            if (spanModel.color > 0) {
//                spanBuilder.setSpan(new ForegroundColorSpan(getColor(spanModel.color)), start, start + spnLength, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//            }
//
//            if (spanModel.isBold) {
//                spanBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, start + spnLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
//            }
//
//            if (spanModel.isUnderlineSpan) {
//                spanBuilder.setSpan(new UnderlineSpan(), start, start + spnLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//
//            if (spanModel.isMiddleLineSpan) {
//                spanBuilder.setSpan(new StrikethroughSpan(), start, start + spnLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//
//            start += spanModel.text.length();
//        }
//        return spanBuilder;
//    }

    /**
     * 设置金额显示
     *
     * @param text
     * @param money
     * @param rmbSize     ￥ 的大小
     * @param moneySize   具体金额的大小
     * @param decimalSize 小数点后的大小
     */
    public static void setMoneyText(TextView text, String money, int rmbSize, int moneySize, int decimalSize) {
        try {

            SpannableStringBuilder spanBuilder = new SpannableStringBuilder();
            String str1, str2, str3;
            str1 = "￥";
            str2 = String2Decimal("", money).split("\\.")[0] + ".";
            str3 = String2Decimal("", money).split("\\.")[1];

            spanBuilder.append(str1);
            spanBuilder.append(str2);
            spanBuilder.append(str3);

            spanBuilder.setSpan(new AbsoluteSizeSpan(rmbSize, true), 0, str1.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanBuilder.setSpan(new AbsoluteSizeSpan(moneySize, true), str1.length(), str1.length() + str2.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanBuilder.setSpan(new AbsoluteSizeSpan(decimalSize, true), str1.length() + str2.length(),
                    str1.length() + str2.length() + str3.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            text.setText(spanBuilder);
        } catch (Exception e) {
            postException(e);
            text.setText("￥" + money);
        }
    }

    /**
     * 设置金额显示
     *
     * @param text
     * @param money
     * @param moneySize   具体金额的大小
     * @param decimalSize 小数点后的大小
     */
    public static void setMoneyTextNoRMB(String str1, TextView text, String money, int text1Size, int moneySize, int decimalSize) {
        try {
            SpannableStringBuilder spanBuilder = new SpannableStringBuilder();
            String str2, str3;
            str2 = String2Decimal("", money).split("\\.")[0] + ".";
            str3 = String2Decimal("", money).split("\\.")[1];

            spanBuilder.append(str1);
            spanBuilder.append(str2);
            spanBuilder.append(str3);

            spanBuilder.setSpan(new AbsoluteSizeSpan(text1Size, true), 0, str1.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanBuilder.setSpan(new AbsoluteSizeSpan(moneySize, true), str1.length(), str1.length() + str2.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanBuilder.setSpan(new AbsoluteSizeSpan(decimalSize, true), str1.length() + str2.length(),
                    str1.length() + str2.length() + str3.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            text.setText(spanBuilder);
        } catch (Exception e) {
            postException(e);
        }
    }


    /**
     * 设置金额显示
     *
     * @param text
     */
    public static void setMoneyTextNoRMB(TextView text, String str1, String str2) {
        try {
            SpannableStringBuilder spanBuilder = new SpannableStringBuilder(str1 + str2);

            spanBuilder.setSpan(new AbsoluteSizeSpan(14, true), 0, str1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            spanBuilder.setSpan(new AbsoluteSizeSpan(11, true), str1.length(), str1.length() + str2.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

            text.setText(spanBuilder);
        } catch (Exception e) {
            postException(e);
        }
    }

    public static final int TOAST_LONG = 5000;
    public static final int TOAST_SHORT = 3000;

//    public static void showCustomToast(final Context ctx, CharSequence toastMessage,
//                                       final View.OnClickListener clickCallback, int duration) {
//
//        final WindowManager windowManager;
//        windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
//        final RelativeLayout addedLayout = (RelativeLayout) LayoutInflater.from(ctx).inflate(R.layout.view_red_package_prompt, null);
//        RelativeLayout toastHolder = (RelativeLayout) addedLayout.findViewById(R.id.rl_prize_red_package_holder);
//        toastHolder.setOnClickListener(v -> {
//            Toast.makeText(ctx, "toast holder clicked!", Toast.LENGTH_SHORT).show();
//            windowManager.removeView(addedLayout);
//        });
//
//        final ValueAnimator anim = ValueAnimator.ofInt(0, 1);
//        anim.setDuration(duration);
//        anim.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                try {
//                    windowManager.removeView(addedLayout);
//                } catch (Exception e) {
//                    CommonUtil.postException(e);
//                }
//                Toast.makeText(ctx, "custom toast removed!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        anim.start();
//
//        TextView textView = (TextView) addedLayout.findViewById(R.id.tv_prize_red_package);
//        textView.setText(toastMessage);
//        textView.setOnClickListener(v -> {
//            anim.end();
//            if (clickCallback != null) {
//                clickCallback.onClick(v);
//            }
//            Toast.makeText(ctx, "custom toast clicked!", Toast.LENGTH_SHORT).show();
//        });
//
//        WindowManager.LayoutParams addedLayoutParams;
//        addedLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.TYPE_PHONE,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                PixelFormat.TRANSPARENT);
//
//        addedLayoutParams.x = 0;
//        addedLayoutParams.y = 0;
//        windowManager.addView(addedLayout, addedLayoutParams);
//
//
//    }
//
//
//    /**
//     * 弹出自定义提示框
//     */
//    public static void showToas2t(Context context, String content) {
//        if (TextUtils.isEmpty(content)) {
//            return;
//        }
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View layout = inflater.inflate(R.layout.view_red_package_prompt, null);
//        TextView title = (TextView) layout.findViewById(R.id.tv_prize_red_package);
//        title.setText(content);
//
//        if (mToast != null) {
//            mToast.cancel();
//        }
//
//        mToast = Toast.makeText(context, content, Toast.LENGTH_LONG);
//        mToast.setGravity(Gravity.BOTTOM, 0, 0);
//        mToast.setView(layout);
//
//        mToast.show();
//    }

    /**
     * 格式化收藏数量
     * 小与1W 显示原来的
     * 大于 ，直接 就 1w+  2w+ 3w+
     */
    public static String formatCollectNumber(long number) {
        if (number < 10000) {
            return String.valueOf(number);
        }
        long wan = number / 10000;
        return wan + "w+";
    }

    /**
     * 隐藏视图
     */
    public static void invisibleViews(View... views) {
        try {
            if (views == null || views.length == 0) {
                return;
            }
            for (View view : views) {
                view.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            CommonUtil.postException(e);
        }
    }

    /**
     * 魅族手机
     *
     * @return
     */
    public static boolean isMeizu() {
        String meizu = "Meizu";
        return meizu.equals(Build.BRAND);
    }

//    public static void viewTreeObserver(View view, CallBackListener callBackListener) {
//        ViewTreeObserver observer = view.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                if (callBackListener != null) {
//                    callBackListener.onCallBack();
//                }
//            }
//        });
//    }
//
//    /**
//     * 检查更新
//     */
//    public static void checkAppUpdate() {
//        if (Constants.CHANNEL_BAIDU.equalsIgnoreCase(SP2Util.getString(SPKeys.CHANNEL))) {
//            BaiduUpdateHelper.getInstance(mContext).checkBaiduUpdate();
//        } else {
//            UmengUpdateAgent.setUpdateOnlyWifi(false);
//            String versioncode = MobclickAgent.getConfigParams(MyApplication.getInstance(), "versioncode");
//
//            if (ConvertUtil.parseInteger(versioncode) > BuildConfig.VERSION_CODE) {
//                UmengUpdateAgent.forceUpdate(mContext);
//            } else {
//                CommonUtil.toast(mContext, "已经是最新版本了");
//            }
//        }
//    }
//    //    public static Parcelable  unmarshall(byte[] byteArrayExtra){
//    //        Parcel parcel = Parcel.obtain();
//    //        parcel.unmarshall(byteArrayExtra, 0, byteArrayExtra.length);
//    //        parcel.setDataPosition(0);
//    //        return TalkEntity.CREATOR.createFromParcel(parcel);
//    //    }
//
//    public static String getPhoneInfo() {
//        StringBuffer sb = new StringBuffer();
//        try {
//            if (SPExtraUtil.isLogin()) {
//                sb.append("用户:" + UserSingleton.getInstance().getPhoneNumber());
//            }
//            sb.append(",android版本:" + BuildConfig.VERSION_NAME);
//            sb.append(",手机型号:" + Build.BRAND + Build.MODEL);
//            sb.append(",渠道:" + SP2Util.getString(SPKeys.CHANNEL));
//            sb.append(",SDK版本:" + Build.VERSION.SDK_INT);
//            sb.append(",系统版本:" + Build.VERSION.RELEASE);
//            sb.append(",分辨率高:" + getDisplayHeight());
//            sb.append(",分辨率宽:" + getDisplayWidth());
//            return sb.toString();
//        } catch (Exception e) {
//            CommonUtil.postException(e);
//            return sb.toString();
//        }
//    }
//
//    //检查指定的报名是否按照
//    public static final boolean isApkInstalled(Context context, String packageName) {
//        try {
//            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
//            return true;
//        } catch (NameNotFoundException e) {
//            return false;
//        }
//    }

    /**
     * 赋值功能
     *
     * @param copyContent
     */
    public static void copy(String copyContent) {
        if (CheckUtil.isEmpty(copyContent)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                ClipboardManager clipboard = (ClipboardManager) MyApplication.getInstance().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("a", copyContent);
                clipboard.setPrimaryClip(clip);
                toast(copyContent + "  已复制到剪切板");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 赋值功能
     */
    public static <T> List<T> copyList(List<T> list) {
        List<T> listCopy = new ArrayList<>();

        if (list == null) {
            return listCopy;
        }
        for (T t : list) {
            listCopy.add(t);
        }
        return listCopy;
    }

    //将颜色转化为16进制
    public static int getHexColor(String color) {
        try {
            if (CheckUtil.isEmpty(color)) {
                return 0Xffffff;
            }
            if (!color.contains("#")) {
                color = "#" + color;
            }
            return Color.parseColor(color);
        } catch (Exception e) {
            CommonUtil.postException(e);
            return 0Xffffff;
        }
    }

}
