package com.example.administrator.myapplication.test;

import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.choicecity.AddressComponent;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ChioceCityActivity extends BaseActivity {

    @InjectView(R.id.tv_chioce) TextView tvChioce;
    @InjectView(R.id.tv_show) TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chioce_city);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.tv_chioce)
    public void setTvChioce(){
        AddressComponent.getInstance(mContext, null, null, null, tvShow).show();
    }
}
