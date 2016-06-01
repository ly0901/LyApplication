package com.example.administrator.myapplication.net;


import java.io.Serializable;

/**
 * Created by afs on 2015/4/19.
 * 基础请求
 */
public class BaseRequest implements Serializable {
    public String operateType;
    public String operateArea;
    public String ticket;
    public String bind;//1是去绑定 2解绑
    public Object data;

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String address_id;
    public String qukk;
    public String timestamp;//  时间戳
    public String encode_sign;//加密后
    public String src_key;//领红包用
    public String bonus_type;//领红包用

    public String operateData;//我的收藏时候用到
    public String pageSize;//
    public String pageIndex;//

//    public String version_name = BuildConfig.VERSION_NAME;//版本号
//    public String version_code = String.valueOf(BuildConfig.VERSION_CODE);//版本
//    public String mac;//mac
//    public String imei;//设备ID
//    public String imsi;//设备ID

    public BaseRequest() {
    }

    public BaseRequest(int operateArea, int operateType, String ticket) {
        this.operateType = String.valueOf(operateType);
        this.operateArea = String.valueOf(operateArea);
        this.ticket = ticket;
    }

    public BaseRequest(String ticket) {
        this.ticket = ticket;
    }

    public void setOperateType(int operateType) {
        this.operateType = String.valueOf(operateType);
    }

    public void setoperateArea(int operateArea) {
        this.operateArea = String.valueOf(operateArea);
    }


    public void setData(Object data) {
        this.data = data;
    }
}
