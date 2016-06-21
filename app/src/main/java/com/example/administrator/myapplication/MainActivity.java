package com.example.administrator.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.test.ViewpagerActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {
    @InjectView(R.id.tv_hello) TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        String userName = "13662584831";
        String userPassword = "123";
        String url = "checkInfo";

        Map<String, String> params = new HashMap<String, String>();
        params.put("whgcsPhone", userName);
        params.put("password", userPassword);
        startNewActivity(ViewpagerActivity.class);
    }

    private void loadDatas() {
    }
}
