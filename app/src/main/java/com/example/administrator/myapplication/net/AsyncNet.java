//package com.example.administrator.myapplication.net;
//
//import com.alibaba.fastjson.JSON;
//import com.example.administrator.myapplication.utils.CommonUtil;
//import com.example.administrator.myapplication.utils.Constants;
//import com.example.administrator.myapplication.utils.NetUtil;
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.JsonHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//import com.loopj.android.http.TextHttpResponseHandler;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.entity.StringEntity;
//
//import java.io.UnsupportedEncodingException;
//
///**
// * Created by afs on 2015/3/7.
// * 请求
// */
//public class AsyncNet {
//
//    private static AsyncHttpClient client = new AsyncHttpClient();
//
//    private static final int TIME_OUT = 30000;
//    public static final int RE_CONNECTION_TIME = 10;
//    public static int RE_CONNECTION_TIME_CURRENT = 0;
//
//
//    public static void post(String url, BaseRequest params, AsyncHandler handler) {
//
//        if (!NetUtil.isNetWorkAvailable()) {
//            //NetworkPresenter.getNoNewWorkView(url, params, handler);
//            CommonUtil.toast(handler.getActivity(), "网络不给力,请检查网络哦！");
//            handler.afterFailure();
//            return;
//        }
//        HttpEntity entity = null;
//        String requestParams = null;
//
//        try {
//            if (params != null) {
//                params.timestamp = String.valueOf(System.currentTimeMillis());
////                params.encode_sign = MD5Util.getMd5key(params.timestamp);
//
//                requestParams = JSON.toJSONString(params);
//                entity = new StringEntity(requestParams, Constants.ENCODING);
//            }
//        } catch (Exception e) {
//            CommonUtil.postException(e);
//        }
//
//        if (handler != null) {
////            url = appendAppInfo(url);
////            url += "&source_id=" + MD5Util.getMd5key2(requestParams);
//
//            handler.setRequestParams(requestParams);
//            handler.setRequestUrl(url);
//            handler.setBaseRequest(params);
//
//            client.setTimeout(TIME_OUT);
//            client.post(handler.getActivity(), url, entity, Constants.CONTENT_TYPE, handler);
//        }
//    }
//
//
//    public static void get(String url, AsyncHandler handler) {
//        if (!NetUtil.isNetWorkAvailable()) {
//            CommonUtil.toast(handler.getActivity(), "网络不给力,请检查网络哦！");
//            handler.afterFailure();
//            return;
//        }
//
//        if (handler != null) {
////            url = appendAppInfo(url);
//
//            client.setTimeout(TIME_OUT);
//            client.get(handler.getActivity(), url, handler);
//        }
//    }
//
//    public static void get(String url, JsonHttpResponseHandler json) {
//        client.get(url, json);
//    }
//
//    public static void get(String url, TextHttpResponseHandler text) {
//        client.get(url, text);
//    }
//
//    //上传图片
//    public static void uploadImages(String url, RequestParams params, AsyncHandler handler) {
////        url = appendAppInfo(url);
//        if (!NetUtil.isNetWorkAvailable()) {
//            //NetworkPresenter.getNoNewWorkView(url, params, handler);
//            CommonUtil.toast(handler.getActivity(), "网络不给力,请检查网络哦！");
//            handler.afterFailure();
//            return;
//        }
//        client.setTimeout(TIME_OUT);
//        client.post(url, params, handler);
//    }
//
//}
