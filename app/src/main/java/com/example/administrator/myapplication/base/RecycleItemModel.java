package com.example.administrator.myapplication.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by afs on 2015/6/20.
 * Recycle item 每行
 */
public class RecycleItemModel {

    public static final int TYPE_TOP = 0;
    public static final int TYPE_BODY = 1;
    public static final int TYPE_BOTTOM = 2;
    public static final int TYPE_RANK_4 = 3;
    public static final int TYPE_PRODUCT_5 = 4;



    public int type;//類型
    public Object value;//具体的值
    public boolean isInit = false;//是非加载过

    public RecycleItemModel(int type, Object value) {
        this.type = type;
        this.value = value;
    }

    public static <T> List<RecycleItemModel> create(List<T> list, int type) {
        List<RecycleItemModel> itemModels = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            itemModels.add(new RecycleItemModel(type, list.get(i)));
        }
        return itemModels;
    }

    public RecycleItemModel() {
    }
}
