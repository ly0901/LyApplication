package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.heartlayout.widget.TestHeartActivity;
import com.example.administrator.myapplication.test.AutomaticRefreshActivity;
import com.example.administrator.myapplication.test.ChioceCityActivity;
import com.example.administrator.myapplication.test.ViewpagerActivity;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

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
        downLoadFile();
//        String userName = "13662584831";
//        String userPassword = "123";
//        String url = "checkInfo";
//
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("whgcsPhone", userName);
//        params.put("password", userPassword);
//        startNewActivity(ViewpagerActivity.class);
    }
    private void downLoadFile(){
        String androidPath="http://interface.im.taobao.com/mobileimweb/fileupload/downloadPriFile.do?type=2&fileId=c5b5704ab2a1ea10c8880a7d081da5e5.amr&suffix=amr&mediaSize=1024&duration=15000";
        String iosPath = "http://ftsproxy.wangxin.taobao.com/fetch?uid=icjyijwxf7f3807e2a4f42c7b11ff65beb46821e&ftsip=140.205.195.134&ftsport=16000&ssession=808891308&filesize=3606&filename=25ec778010fd37002355bba9b26d87cd.amr&type=2&duration=4280";
        String SDCard= Environment.getExternalStorageDirectory()+"";
        String pathName=SDCard+"/"+"file"+"/"+"ccab.amr";//文件存储路径
        String url  = androidPath;
        String path = pathName;
        FileDownloader.getImpl().create(url)
                .setPath(path)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        toast("xiazaichengg");
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }
                }).start();
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
