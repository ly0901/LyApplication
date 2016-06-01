package com.example.administrator.myapplication.utils;

/**
 * Created by afs on 2015/5/12.
 * 变量
 */
public class Constants {

//    AppID：wxebb3f06d7fe3352d
//    AppSecret：71d4bc6e1a403cb7473abb212e69530c

    public static final String RMB = "￥";
//    AppID(应用ID)：wxd642344836a4b74d
//    AppSecret(应用密钥)：2b35f41b291b35bd84241a2c45088318
//    密钥(PartnerKey)：c2d6a0020820d47cb4730207eb421081

//    public static final String WEI_XIN_APP_ID = "wxd642344836a4b74d";
//    public static final String WEI_XIN_APP_SECRET = "2b35f41b291b35bd84241a2c45088318";
//    public static final String WEI_XIN_PARTNER_ID = "1240230402";

    public static final Long ONE_DAY = 24L * 60L * 60L * 1000L;
    public static final String IMG_SAVE_PATH = "";
    //用一个标识区分测试和正式的帐号
    public static final String USER_TOKEN = true ? "gift_box_sp_debug" : "gift_box_sp";
    public static final String GIFT_BOX_SP2 =true ? "gift_box_sp2_debug" : "gift_box_sp2";
    public static final String ENCODING = "UTF-8";
    public static final String CONTENT_TYPE = "application/json";

    //QQ
    //public String mAppid = "100305073";//老东家
    public static final String QQ_APP_ID = "1104418539";
    public static final String QQ_APP_SECRET = "7wBHEubIUKTJu3FS";

    //微博
    public static final String WEIBO_APP_ID = "1962104948";
    public static final String WEIBO_APP_SECRET = "759d84b95978fce3b31b643955c6ff38";

    //微信
    public static final String WEI_XIN_APP_ID = "wxebb3f06d7fe3352d";
    public static final String WEI_XIN_APP_SECRET = "71d4bc6e1a403cb7473abb212e69530c";
    public static final String WEI_XIN_PARTNER_ID = "1240230402";


    //短信的发送号码
    public static final String MESSAGE_NUM = "10690157263737";
    public static final String IMG_PATH = "/giftbox/";
    //凑分子上传图片临时目录
    public static final String IMG_PATH_TEMP_GROUP = "/giftbox/";
    public static final String SPLASH_IMG_PATH = "/giftbox/splash";

    public static class ShowMsgActivity {
        public static final String STitle = "showmsg_title";
        public static final String SMessage = "showmsg_message";
        public static final String BAThumbData = "showmsg_thumb_data";
    }

    //启动初始化
    public static String MAC_ADDRESS;
    public static String DEVICE_ID;
    public static String IMSI;

    //百度渠道
    public static final String CHANNEL_BAIDU = "baidu";

    public final static String NAME_SPACE = "urn:soap"; // "http://service.xinghuo.com/";
    public final static String WSDL_URL = "http://203.91.43.52:9782/ws/appWS/";
    public static final String checkUserInfo = "checkInfo";
}
