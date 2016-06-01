package com.example.administrator.myapplication.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseRecycleAdapter;
import com.example.administrator.myapplication.base.RecycleItemModel;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 集赞列表
 */
public class MessageRecycleAdapter extends BaseRecycleAdapter<RecycleItemModel> {



    private RecyclerView mRecyclerView;
    private int red = R.color.reviewing;
    private int apple = R.color.apple;
    private int concord = R.color.mine_shaft3;

    public MessageRecycleAdapter(Context context, RecyclerView mRecyclerView) {
        super(context);
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View convertView;
        // 按当前所需的样式，确定new的布局
        switch (viewType) {
            case RecycleItemModel.TYPE_TOP:
                convertView = mInflater.inflate(R.layout.item_top, null);
                holder = new ViewHolderTop(convertView);
                break;
            case RecycleItemModel.TYPE_BODY:
                convertView = mInflater.inflate(R.layout.item_body, null);
                holder = new ViewHolder(convertView);
                break;
            case RecycleItemModel.TYPE_BOTTOM:
                convertView = mInflater.inflate(R.layout.item_bottom, null);
                holder = new ViewHolderBottom(convertView);
                break;
            default:
                convertView = mInflater.inflate(R.layout.item_body, null);
                holder = new ViewHolder(convertView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case RecycleItemModel.TYPE_TOP:
                initTop((ViewHolderTop) holder, position);
                break;
            case RecycleItemModel.TYPE_BODY:
                initBody((ViewHolder) holder, position);
                break;
            case RecycleItemModel.TYPE_BOTTOM:
                initBottom((ViewHolderBottom) holder, position);
                break;

            default:
                break;
        }
    }

    private void initBottom(ViewHolderBottom holder, int position) {
        
    }

    private void initBody(ViewHolder holder, int position) {

    }

    private void initTop(ViewHolderTop holder, int position) {
    }


    /**
     * 获取消息的类型
     */
    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).type;
    }


    class ViewHolderTop extends RecyclerView.ViewHolder {
        public int position;
        @InjectView(R.id.tv_top) TextView tvTop;

        ViewHolderTop(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public int position;
        @InjectView(R.id.tv_top) TextView tvBody;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    class ViewHolderBottom extends RecyclerView.ViewHolder {
        public int position;
        @InjectView(R.id.tv_bottom) TextView tvBottom;

        ViewHolderBottom(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }


}
