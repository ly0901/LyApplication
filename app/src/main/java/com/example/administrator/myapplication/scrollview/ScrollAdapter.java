package com.example.administrator.myapplication.scrollview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseRecycleAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lxt on 2016-6-24.
 */
public class ScrollAdapter extends BaseRecycleAdapter<String> {

    public ScrollAdapter(Context context, List data) {
        super(context, data);
    }

    public ScrollAdapter(Context context) {
        super(context);
    }
    private  ViewHolder mHolder;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View convertView;
        convertView = mInflater.inflate(R.layout.item_scroll, null);
        holder = new ViewHolder(convertView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mDataList.size();
        initData((ViewHolder) holder, position);
        mHolder = (ViewHolder)holder;
    }

    private void initData(ViewHolder holder, int position) {
        if(position %2 ==0){
            holder.tvImage.setImageResource(R.drawable.heart);
        }else {
            holder.tvImage.setImageResource(R.drawable.duola_a_meng_39);
        }
    }
    public void setAdapterTranslation(float value){
        mHolder.tvImage.setTranslationY(value*10);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_image) ImageView tvImage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
