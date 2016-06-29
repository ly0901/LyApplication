package com.example.administrator.myapplication.parallax;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lxt on 2016-6-29.
 */
public class ParallaxAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> datas;
    private LayoutInflater inflater;
    private Context context;
    private String[] imageUrls = new String[]{
            "http://yayandroid.com/data/github_library/parallax_listview/test_image_1.jpg",
            "http://yayandroid.com/data/github_library/parallax_listview/test_image_2.jpg",
            "http://yayandroid.com/data/github_library/parallax_listview/test_image_3.png",
            "http://yayandroid.com/data/github_library/parallax_listview/test_image_4.jpg",
            "http://yayandroid.com/data/github_library/parallax_listview/test_image_5.png",
    };

    public ParallaxAdapter(Context context, List<String> data) {
        super();
        inflater = LayoutInflater.from(context);
        datas = data;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.label.setText(datas.get(position));
        Picasso.with(context).load(imageUrls[position % imageUrls.length]).into(holder.backgroundImage);
        holder.backgroundImage.reuse();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewHolder, int position) {

        View view = inflater.inflate(R.layout.listitem_layout, null);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ParallaxImageView.ParallaxImageListener{
        @InjectView(R.id.backgroundImage) ParallaxImageView backgroundImage;
        @InjectView(R.id.label) TextView label;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            backgroundImage.setListener(this);
        }

        @Override
        public int[] requireValuesForTranslate() {
            if (itemView.getParent() == null) {
                // Not added to parent yet!
                return null;
            } else {
                int[] itemPosition = new int[2];
                itemView.getLocationOnScreen(itemPosition);

                int[] recyclerPosition = new int[2];
                ((RecyclerView) itemView.getParent()).getLocationOnScreen(recyclerPosition);

                return new int[]{itemView.getMeasuredHeight(), itemPosition[1], ((RecyclerView) itemView.getParent()).getMeasuredHeight(), recyclerPosition[1]};
            }
        }
    }
}
