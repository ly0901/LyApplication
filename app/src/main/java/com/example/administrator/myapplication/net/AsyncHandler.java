package com.example.administrator.myapplication.net;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.administrator.myapplication.utils.CommonUtil;
import com.example.administrator.myapplication.utils.LogerUtil;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

public abstract class AsyncHandler extends TextHttpResponseHandler {

    private ProgressDialog mDialog;
    private BaseRequest baseRequest;
    private Activity mActivity;

    //是否显示loading
    private boolean isLoading;
    //是否可以取消loading
    private boolean isCanCancel = true;
    private String requestParams;
    private String className = "";
    private String requestUrl;

    public void setBaseRequest(BaseRequest baseRequest) {
        this.baseRequest = baseRequest;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 订单
     *
     * @param activity
     * @param loading   是否显示loading
     * @param canCancel 是否可以取消loading
     */
    public AsyncHandler(Activity activity, boolean loading, boolean canCancel) {
        super();
        mActivity = activity;
        isLoading = loading;
        isCanCancel = canCancel;
        if (activity != null && activity.getClass() != null) {
            className = activity.getClass().getSimpleName();
        }
        createLoading();
    }

    /**
     * 订单 是否可以取消订单
     *
     * @param activity
     * @param loading  是否显示loading
     */
    public AsyncHandler(Activity activity, boolean loading) {
        this(activity, loading, true);
    }

    public AsyncHandler(Activity activity) {
        this(activity, true, true);
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            if (mDialog != null && isLoading && !mActivity.isFinishing()) {
                mDialog.show();
            }
        } catch (Exception e) {
            CommonUtil.postException(e);
        }
    }

    public void hideDialog() {
        try {
            if (mDialog != null && isLoading && !mActivity.isFinishing()) {
                mDialog.dismiss();
            }
        } catch (Exception e) {
            CommonUtil.postException(e);
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseBody) {
        hideDialog();

        try {
            CommonUtil.printSuccessLog(className, headers, getRequestURI(), requestParams, statusCode, responseBody);

            if (TextUtils.isEmpty(responseBody)) {
                afterFailure();
                return;
            }

            BaseResponse result = null;
            try {
                result = JSON.parseObject(responseBody, BaseResponse.class);
            } catch (Exception e) {
                LogerUtil.ee("onSuccess json 解析错误 ", responseBody);
                CommonUtil.postException(e);
            }

            if (result == null) {
                afterFailure();
                return;
            }

            result.responseBody = responseBody;

            afterSuccess(result);
        } catch (Exception e) {
            CommonUtil.postException(e);
            afterFailure();
        }
    }

    public abstract void afterSuccess(BaseResponse result);

    public void afterFailure() {
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, //
                          Throwable arg3) {
        hideDialog();
        CommonUtil.printErrorLog(className, headers, getRequestURI(), requestParams, statusCode, responseString);
        CommonUtil.toast("非常抱歉，网络不稳定");
        afterFailure();
    }

//    protected MyProgressDialog createProgressDialog(Activity activity, String msg) {
//        if (null != activity) {
//            if (mDialog == null) {
//                mDialog = MyProgressDialog.createDialog(activity, msg);
//            }
//        }
//        return mDialog;
//    }

    protected void createLoading() {

        if (null != mActivity) {
            if (mDialog == null && !mActivity.isFinishing()) {
                mDialog = new ProgressDialog(mActivity);
                mDialog.setMessage("加载中...");
                mDialog.setCancelable(isCanCancel);
                mDialog.setCanceledOnTouchOutside(false);
            }
        }
    }
}
