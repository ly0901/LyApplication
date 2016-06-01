package com.example.administrator.myapplication.net;

import android.text.TextUtils;

import com.example.administrator.myapplication.utils.CommonUtil;


/**
 * Created by afs on 2015/4/19.
 * 基础返回数据的基类
 */
public class BaseResponse {
    public String errmsg = "非常抱歉，网络不稳定！";
    public String status;
    public String ticket;
    public String token;
    public String data;
    public String num_bonus;//红包数量
    public String sum_amount;
    public String refund_time;
    public String code_id;
    public int is_bunding;//0没绑定 1 是已经绑定
    public int errcode;
    public String responseBody;
    public boolean isOk() {
        if (status == null) {
            return false;
        }
        return status.equals("0");
    }

    public boolean isNotOk() {
        return !isOk();
    }

//    public boolean isPass() {
//        return errcode == ErrorCode.Error80005;
//    }

    /**
     * 默认由错误就提示
     *
     * @return 成功返回 true
     */
    public boolean isMOK() {
        return isMOK(true);
    }

    public boolean isNotMOK() {
        return !isMOK();
    }

    /**
     * 错误提示
     *
     * @param isErrorToast 出错提示
     * @return 成功返回 true
     */
    public boolean isMOK(boolean isErrorToast) {
        if (isErrorToast && !TextUtils.isEmpty(status) && !status.equals("0") && !TextUtils.isEmpty(errmsg)) {
            CommonUtil.toast(errmsg);
        }
        return !TextUtils.isEmpty(status) && status.equals("0");
    }
}
