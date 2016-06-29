package com.example.administrator.myapplication;

import android.os.Bundle;
import android.widget.Button;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.heartlayout.widget.TestHeartActivity;
import com.example.administrator.myapplication.test.AutomaticRefreshActivity;
import com.example.administrator.myapplication.test.ChioceCityActivity;
import com.example.administrator.myapplication.test.ViewpagerActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @InjectView(R.id.tv_choice_city) Button tvChoiceCity;
    @InjectView(R.id.tv_bear) Button tvBear;
    @InjectView(R.id.tv_test) Button tvTest;
    @InjectView(R.id.tv_auto_troll) Button tvAutoTroll;
    @InjectView(R.id.tv_test2) Button tvTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
//        String userName = "13662584831";
//        String userPassword = "123";
//        String url = "checkInfo";
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("whgcsPhone", userName);
//        params.put("password", userPassword);
//        startNewActivity(ViewpagerActivity.class);
    }

    @OnClick(R.id.tv_choice_city)
    public void setTvChoiceCity() {
        startNewActivity(ChioceCityActivity.class);
    }

    @OnClick(R.id.tv_bear)
    public void setTvBear() {
        startNewActivity(TestHeartActivity.class);
    }

    @OnClick(R.id.tv_auto_troll)
    public void setTvAutoTroll() {
        startNewActivity(ViewpagerActivity.class);
    }

    @OnClick(R.id.tv_test)
    public void setTvTest() {
        startNewActivity(AutomaticRefreshActivity.class);
    }
    @OnClick(R.id.tv_test2)
    public void setTvTest2() {
        startNewActivity(AutomaticRefreshActivity.class);
    }


    private void loadDatas() {
    }
}
