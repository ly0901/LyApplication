package com.example.administrator.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.net.AsyncHandler;
import com.example.administrator.myapplication.net.AsyncNet;
import com.example.administrator.myapplication.net.BaseRequest;
import com.example.administrator.myapplication.net.BaseResponse;
import com.example.administrator.myapplication.test.RecyclerTest2Activity;

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
        startNewActivity(RecyclerTest2Activity.class);
    }

    private void loadDatas() {
        BaseRequest baseRequest = new BaseRequest();

        AsyncNet.get("http://app.xinyihezi.com:8888/homepage/?search_type=3", new AsyncHandler(mActivity) {
            @Override
            public void afterSuccess(BaseResponse result) {
                String string = result.errmsg + result.status;
                Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void afterFailure() {
                Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
                super.afterFailure();
            }
        });
    }
}
