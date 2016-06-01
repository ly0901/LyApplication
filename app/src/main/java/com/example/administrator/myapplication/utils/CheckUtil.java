package com.example.administrator.myapplication.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fansm
 */
public class CheckUtil {
    /**
     * chinese code regex
     */
    private static final String chinese_code_regex = "(^[\u4E00-\u9FA5]{2,16}$)|([\u4E00-\u9FA5]+[\u00b7][\u4E00-\u9FA5]+$)";

    public static boolean verifyMoney(String email) {
        if (!TextUtils.isEmpty(email)) {
            String regex = "^[0-9]+(\\.?[0-9]{0,2})?$";
            return (Pattern.compile(regex).matcher(email).matches());
        }
        return true;
    }

    /**
     * check name
     *
     * @param name
     * @return
     */
    public static boolean isValidName(String name) {
        if (name == null || "".equals(name)) return false;
        if (name.length() >= 2 && name.length() <= 16) {
            if (isValidInput(chinese_code_regex, name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check input
     *
     * @param chinese_code_regex
     * @param name
     * @return
     */
    public static boolean isValidInput(String chinese_code_regex, String name) {
        try {
            Pattern chinese_code = Pattern.compile(chinese_code_regex);
            Matcher proto = chinese_code.matcher(name);
            return proto.find();
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * check card all place of China
     *
     * @param card
     * @return
     */
    public static boolean isValidCard(String card) {
        if (card == null || "".equals(card)) return false;
        if (card.length() == 10 || card.length() == 15) {
            return true;
        } else if (card.length() == 18) {
            return isValidChinaCard(card);
        }
        return false;

    }

    /**
     * check card mainland of China
     *
     * @param card
     * @return
     */
    public static boolean isValidChinaCard(String card) {
        card = card.toUpperCase();
        int year = Integer.parseInt(card.substring(6, 10));
        int month = Integer.parseInt(card.substring(10, 12));
        int day = Integer.parseInt(card.substring(12, 14));

        int[] weight = {2, 4, 8, 5, 10, 9, 7, 3, 6, 1, 2, 4, 8, 5, 10, 9, 7};
        char[] checkCard = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

        int sum = 0;
        int[] tmpCard = new int[18];

        if (year < 1900 || month < 1 || month > 12 || day < 1 || day > 31
                || ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
                || (month == 2 && ((year % 4 > 0 && day > 28) || day > 29))) {
            return false;
        }

        for (int i = 1; i < 18; i++) {
            int j = 17 - i;
            tmpCard[i - 1] = Integer.parseInt(card.substring(j, j + 1));
        }

        for (int i = 0; i < 17; i++) {
            sum += weight[i] * tmpCard[i];
        }
        sum = sum % 11;
        if (card.charAt(17) != checkCard[sum]) {
            return false;
        }
        return true;
    }


    // 检查是否11位电话号码
    public static boolean isphonenum(String phonenum) {
        if (TextUtils.isEmpty(phonenum)) {
            return false;
        }
        Pattern p = Pattern.compile("^\\d{11}");
        Matcher m = p.matcher(phonenum);
        return m.matches();
    }

    // 检查是否数字
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是不是质数/素数
     *
     * @param number
     * @return
     */
    public static boolean IsPrime(int number) {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 7, 11, 13, 17, 19, 23,
                29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        return list.contains(number);
    }

    /**
     * 将1转换成01
     *
     * @param time
     * @return
     */
    public static String isTen(int time) {
        String timeStr = "";
        if (time < 10) {
            timeStr += "0" + time;
        } else {
            timeStr += time;
        }
        return timeStr;
    }

    public static String isTenSpace(int time) {
        String timeStr = "";
        if (time < 10) {
            timeStr += "  " + time;
        } else {
            timeStr += time;
        }
        return timeStr;
    }

    /**
     * 判断是否是模拟器
     *
     * @return
     */
    public static boolean isEmulator() {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec("/system/bin/cat /proc/cpuinfo");
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        InputStream in = process.getInputStream();
        BufferedReader boy = new BufferedReader(new InputStreamReader(in));
        String mystring = null;
        try {
            mystring = boy.readLine();
            while (mystring != null) {
                mystring = mystring.trim().toLowerCase();
                if ((mystring.startsWith("hardware"))
                        && mystring.endsWith("goldfish")) {
                    return true;
                }
                mystring = boy.readLine();
            }

        } catch (IOException e) {
            return false;
        }

        return false;
    }

    /**
     * 判断字符串是否是空值
     */
    public static String isNullToOne(String str) {
        if (str == null || str.equals("")) {
            return "1";
        } else {
            return str;
        }
    }

    /**
     * 判断字符串是否是空值
     */
    public static String isNull(String str) {
        if (str == null || "".equals(str)) {
            return "0";
        } else {
            return str;
        }

    }

    /**
     * 判断邮箱的正确性
     *
     * @return 返回是否正确标识
     */
    public static boolean isRightEmail(String email) {
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * 判断充值金额是否满足条件
     *
     * @param context
     * @return
     */
    public static boolean isRecharge(String amount, Context context) {
        if ("".equals(amount.trim())) {
            Toast.makeText(context, "请输入充值金额！", Toast.LENGTH_SHORT).show();
            return true;
        } else if ("0".equals(amount.trim())) {
            Toast.makeText(context, "充值金额不能为0！", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFiveLeague(String league) {
        if (TextUtils.isEmpty(league) || league.trim().length() == 0) {
            return false;
        }
        String[] fiveLeague = {"意甲", "英超", "西甲", "德甲", "法甲"};

        for (int i = 0; i < fiveLeague.length; i++) {
            if (league.contains(fiveLeague[i])) {
                int index = league.indexOf(fiveLeague[i]);
                if (!isChinese(league.substring(0, index))
                        && !isChinese(league.substring(index + 2,
                        league.length()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是中文
     */
    public static boolean isChinese(String str) {
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
            if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExistFields(JSONObject jsonobj, String name) {
        if (jsonobj != null && jsonobj.has(name)) {
            return true;
        }
        return false;
    }

    public final static boolean isScreenLocked(Context context) {
        KeyguardManager mKeyguardManager = (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
        return !mKeyguardManager.inKeyguardRestrictedInputMode();
    }

//    public static boolean isRunningForeground(Context context) {
//        try {
//            String packageName = CommonUtil.getPackageName(context);
//            String appName[] = packageName.split("\\.");
//            String topActivityClassName = PublicMethod.getTopActivityName(context);
//            if (packageName != null
//                    && topActivityClassName != null
//                    && topActivityClassName.startsWith(appName[0] + "."
//                    + appName[1])) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            return false;
//        }
//    }

    /**
     * 是不是合数
     *
     * @param number
     * @return
     */
    public static boolean IsComposite(int number) {
        List<Integer> list = Arrays.asList(0, 4, 6, 8, 9, 10, 12, 14, 15, 16,
                18, 20, 21, 22, 24, 25, 26, 27, 28, 30, 32, 33, 34, 35, 36, 38,
                39, 40, 42, 44, 45, 46, 48, 49, 50, 51, 52, 54, 55, 56, 58, 60,
                62, 63, 64, 68, 69, 70, 72, 74, 75, 76, 78, 80, 82, 84, 85, 86,
                88, 90, 91, 92, 93, 94, 95, 96, 98, 99, 100);
        return list.contains(number);
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

//    public static boolean isAppRunning(Context context) {
//        String packageName = PublicMethod.getPackageName(context);
//        ActivityManager am = (ActivityManager) context
//                .getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
//        for (ActivityManager.RunningTaskInfo info : list) {
//            if (info.topActivity.getPackageName().equals(packageName)
//                    && info.baseActivity.getPackageName().equals(packageName)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static Boolean IsUrl(String s) {
        String regEx = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        // Matcher matcher = Patterns.WEB_URL.matcher(s);
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(s);
        return mat.find();
    }

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static Boolean isEmpty(String... str) {
        if (str == null || str.length == 0) {
            return true;
        }
        for (String s : str) {
            if (TextUtils.isEmpty(s) || s.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否为空
     *
     * @param list
     * @return
     */
    public static Boolean isEmpty(List list) {
        return list == null || list.size() == 0 || list.isEmpty();
    }


    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static Boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 是否为空
     *
     * @param list
     * @return
     */
    public static Boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }


    public static boolean isRunningForeground(Context context) {
        try {
            String packageName = context.getPackageName();
            String appName[] = packageName.split("\\.");
            String topActivityClassName = CommonUtil.getTopActivityName(context);
            if (topActivityClassName != null && topActivityClassName.startsWith(appName[0] + "." + appName[1])) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            CommonUtil.postException(e);
            return false;
        }
    }

    /**
     * 是否是夜间模式规定的时间
     *
     * @return
     */
    public static boolean isNightModeTime() {
        Date date = new Date(System.currentTimeMillis());
        int hours = date.getHours();
        return ((hours >= 19 && hours <= 23) || (hours <= 9 && hours >= 0));
        //return ((hours >= 21 && hours <= 23) || (hours <= 6 && hours >= 0));
    }

}
