package com.example.administrator.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by afs on 2015/3/18.
 * 基础ListAdapter
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int PAGE_SIZE = 10;
    private static final String TAG = "BaseRecycleAdapter";

    public int FIRST_PAGE_NO = 1;
    public int mTotalPage;//总页数
    public int mPageIndex;//当前页
    public boolean mIsLoading;//是否在加载中

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDataList;
    private Class<T> tClass;


    public BaseRecycleAdapter(Context context, List<T> data) {
        this.mDataList = data;
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public BaseRecycleAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    public Context getContext() {
        return mContext;
    }

    public void setClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<T> getmDataList() {
        return mDataList;
    }

    /**
     * 跳转到指定的Activity
     *
     * @param cls
     */
    protected void startNewActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        mContext.startActivity(intent);
    }

    /**
     * 跳转到指定的Activity
     *
     * @param cls
     */
    protected void startNewActivityForResult(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        ((Activity) mContext).startActivityForResult(intent, 0);
    }

    public T getItem(int position) {
        return mDataList.get(position);
    }

    public Object getRecycleItemObject(int position) {
        return ((RecycleItemModel) mDataList.get(position)).value;
    }

    public RecycleItemModel getRecycleItem(int position) {
        return ((RecycleItemModel) mDataList.get(position));
    }

    public void setRecycleItemInit(int position, boolean isInit) {
        ((RecycleItemModel) mDataList.get(position)).isInit = isInit;
    }

    public void add(T t) {
        add(t, mDataList.size());
    }

    /**
     * 插入一个
     *
     * @param homeModel
     * @param position
     */
    public void add(T homeModel, int position) {
        mDataList.add(position, homeModel);
        notifyItemInserted(position);
    }


    /**
     * 删除行
     *
     * @param position
     */
    public void remove(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataList.size());
    }

    /**
     * 删除行
     *
     * @param start
     */
    public void remove(int start, int size) {
        for (int i = size; i >= 0; i--) {
            if (i > start) {
                mDataList.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 二級頁面刪除
     */
    public void removeSecondRange(int start) {
        int size = mDataList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (i > start) {
                mDataList.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    public void clearAll() {
        removeAll();
    }

    /**
     * 清空数据
     */
    public void removeAll() {
        int size = mDataList.size();
        mDataList.clear();
        notifyItemRangeRemoved(0, size);
    }

    /**
     * 刷新数据
     *
     * @param data
     */
    public void refresh(T[] data) {
        removeAll();
        addAll(data);
    }

    /**
     * 刷新数据
     *
     * @param data
     */
    public void refresh(List<T> data) {
        removeAll();
        addAll(data);
    }

    /**
     * 追加数据
     *
     * @param data
     */
    public void addAll(T[] data) {
        addAll(Arrays.asList(data));
    }

    /**
     * 追加数据
     *
     * @param data
     */
    public void addAll(List<T> data) {
        int startIndex = mDataList.size();
        mDataList.addAll(startIndex, data);
        notifyItemRangeInserted(startIndex, data.size());
    }

    /**
     * 插入集合
     *
     * @param data
     * @param position
     */
    public void addAll(List<T> data, int position) {
        mDataList.addAll(position, data);
        notifyItemRangeInserted(position, data.size());
    }

    public boolean canLoading() {
        return !mIsLoading && mTotalPage >= 0;
    }

    public void reset() {
        FIRST_PAGE_NO = 1;
        mTotalPage = 0;//总页数
        mPageIndex = 1;//当前页
        mIsLoading = false;//是否在加载中
    }

//    public void setData(BaseResponse result) {
//
//        if (result == null || CheckUtil.isEmpty(result.data) || result.data.equals("[]")) {
//            setEmptyData();
//            return;
//        }
//
//        List<T> data = JSONUtil.getList(result.data, tClass);
//
//        if (CheckUtil.isEmpty(data)) {
//            setEmptyData();
//            return;
//        }
//
//        if (mPageIndex == 1) {
//            refresh(data);
//        } else {
//            addAll(data);
//        }
//
//        mPageIndex++;
//    }

    public void setEmptyData() {
        removeAll();
        //设置没有数据了不然在请求了
        mTotalPage = -1;
        if (mPageIndex > FIRST_PAGE_NO) {
            mPageIndex--;
        }
        //boolean isFirstPage = mPageIndex == FIRST_PAGE_NO;
        //CommonUtil.toast(mContext, isFirstPage ? R.string.now_no_datas : R.string.now_no_more_datas);
    }

    public boolean isFirstPage() {
        return mPageIndex == FIRST_PAGE_NO;
    }

//    /**
//     * 成功后添加数据
//     *
//     * @param response
//     * @param clszz
//     */
//    public void successLoad(BaseResponse response, Class clszz) {
//
//        if (response.isNotOk() || CheckUtil.isEmpty(response.data) || response.data.equals("[]")) {
//            if (isFirstPage()) {
//                setEmptyData();
//            } else {
//                mTotalPage = -1;
//            }
//            return;
//        }
//
//        List<T> orderModels = null;
//
//        try {
//            orderModels = JSONUtil.getList(response.data, clszz);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (CheckUtil.isEmpty(orderModels)) {
//            if (isFirstPage()) {
//                setEmptyData();
//            } else {
//                mTotalPage = -1;
//            }
//            return;
//        }
//
//        //第一页刷新-或添加更多
//        if (isFirstPage()) {
//            refresh(orderModels);
//        } else {
//            addAll(orderModels);
//        }
//
//        mPageIndex++;
//    }
//
//    /**
//     * 成功后添加数据
//     *
//     * @param response
//     */
//    public void successLoad(BaseResponse response, Class clszz, String key) {
//
//        if (response.isNotOk() || CheckUtil.isEmpty(response.data) || response.data.equals("[]")) {
//            if (isFirstPage()) {
//                setEmptyData();
//            }
//            return;
//        }
//
//        List<T> orderModels = JSONUtil.getList(key, response.data, clszz);
//
//        if (CheckUtil.isEmpty(orderModels)) {
//            if (isFirstPage()) {
//                setEmptyData();
//            }
//            return;
//        }
//
//        //第一页刷新-或添加更多
//        if (isFirstPage()) {
//            refresh(orderModels);
//        } else {
//            addAll(orderModels);
//        }
//
//        mPageIndex++;
//    }
//
//    /**
//     * 成功后添加数据
//     */
//    public void successLoad(List<T> orderModels) {
//
////        if (response.isNotOk() || CheckUtil.isEmpty(response.data) || response.data.equals("[]")) {
////            if (isFirstPage()) {
////                setEmptyData();
////            }
////            return;
////        }
////
////        List<OrderModel> orderModels = JsonUtil.getList(key, response.data, clszz);
//
//        if (CheckUtil.isEmpty(orderModels)) {
//            if (isFirstPage()) {
//                setEmptyData();
//            }
//            return;
//        }
//
//        //第一页刷新-或添加更多
//        if (isFirstPage()) {
//            refresh(orderModels);
//        } else {
//            addAll(orderModels);
//        }
//
//        mPageIndex++;
//    }

    /**
     * 加载前
     *
     * @param isRefresh
     * @return true 停止加载
     */
    public boolean preLoad(boolean isRefresh) {
        if (isRefresh) {
            reset();
        }

        if (!canLoading()) {
            return true;
        }

        mIsLoading = true;
        return false;
    }

}
