package com.example.administrator.myapplication.choicecity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.CommonUtil;
import com.example.administrator.myapplication.utils.LogerUtil;
import com.example.administrator.myapplication.wheelview.WheelView;
import com.example.administrator.myapplication.wheelview.adapte.ArrayWheelAdapter;
import com.example.administrator.myapplication.wheelview.listener.OnWheelChangedListener;
import com.example.administrator.myapplication.wheelview.model.CityModel;
import com.example.administrator.myapplication.wheelview.model.DistrictModel;
import com.example.administrator.myapplication.wheelview.model.ProvinceModel;
import com.example.administrator.myapplication.wheelview.service.XmlParserHandler;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 选择省市县组件
 *
 * @author lxt
 */
public class AddressComponent implements OnWheelChangedListener, View.OnClickListener {

    private static final String TAG = "ShareComponent";
    private Context mContext;
    private Dialog mDialog;
    private String mUrl;
    private String mTitle;
    private String mLogo;
    private String mContent;
    private String mSharePictureName = "";

    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm;
    private EditText etDetailAddress;

    private static AddressComponent sInstance;

    /**
     * 所有省
     */
    protected String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    /**
     * key - 区 values - 邮编
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    /**
     * 当前省的名称
     */
    protected String mCurrentProviceName;
    /**
     * 当前市的名称
     */
    protected String mCurrentCityName;
    /**
     * 当前区的名称
     */
    protected String mCurrentDistrictName = "";

    /**
     * 当前区的邮政编码
     */
    protected String mCurrentZipCode = "";


    /**
     * 解析省市区的XML数据
     */

    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = mContext.getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static AddressComponent getInstance(Context context, String content, String title, String url, TextView etArea) {

        return new AddressComponent(context, content, title, url, etArea);
    }

    private TextView etArea;

    private AddressComponent(Context context, String content, String title, String url, TextView etArea) {
        this.mContent = content;
        this.mUrl = url;
        this.mTitle = title;
        this.etArea = etArea;
        mContext = context;
        init();
    }

    public void init() {
        mDialog = new Dialog(mContext, R.style.DialogSlideAnim);
        mDialog.setContentView(R.layout.view_address_component);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.getWindow().setGravity(Gravity.BOTTOM);
        //赋值的位置是有影响的
        mDialog.getWindow().getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
        mBtnConfirm = (Button) mDialog.findViewById(R.id.btn_confirm);
        mBtnConfirm.setOnClickListener(this);
        mViewProvince = (WheelView) mDialog.findViewById(R.id.id_province);
        mViewCity = (WheelView) mDialog.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) mDialog.findViewById(R.id.id_district);
        etDetailAddress = (EditText) mDialog.findViewById(R.id.et_detail_address);
        setUpListener();
        setUpData();
        showInitData();

    }

    /**
     * 初始化原来的数据
     */
    private void showInitData() {
        if (mContent != null && mTitle != null && mUrl != null && mUrl.length() > 0) {
            //确定省
            for (int i = 0; i < mProvinceDatas.length; i++) {
                if (mContent.equals(mProvinceDatas[i])) {
                    mViewProvince.setCurrentItem(i);
                    mCurrentProviceName = mContent;
                    break;
                }
            }
            //确定市
            for (int i = 0; i < mCitisDatasMap.get(mContent).length; i++) {
                if (mTitle.equals(mCitisDatasMap.get(mContent)[i])) {
                    mViewCity.setCurrentItem(i);
                    mCurrentCityName = mTitle;
                    break;
                }
            }
            //确定县
            for (int i = 0; i < mDistrictDatasMap.get(mTitle).length; i++) {
                if (mUrl.equals(mDistrictDatasMap.get(mTitle)[i])) {
                    mViewDistrict.setCurrentItem(i);
                    mCurrentDistrictName = mUrl;
                    mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
                    break;
                }
            }

        }
    }

    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(mContext, areas));
        mViewDistrict.setCurrentItem(0);
        //更新县以及邮政编号
        mCurrentDistrictName = areas[0];
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
    }

    private void showInitdata() {
    }


    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        try {
            int pCurrent = mViewProvince.getCurrentItem();
            mCurrentProviceName = mProvinceDatas[pCurrent];
            String[] cities = mCitisDatasMap.get(mCurrentProviceName);
            if (cities == null) {
                cities = new String[]{""};
            }
            mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(mContext, cities));
            mViewCity.setCurrentItem(0);
            updateAreas();
        } catch (Exception e) {
            CommonUtil.postException(e);
        }
    }

    public void show() {
        show(null, null, null, null);
    }

    public void show(String content, String title, String url) {
        show(content, title, url, null);
    }

    public void show(String content, String title, String url, String logo) {
        LogerUtil.ee(TAG, "content:" + this.mContent + "  url:" + this.mUrl + "  title:" + this.mTitle);

        if (mContext != null && !((Activity) mContext).isFinishing()) {
            mDialog.show();
        }
    }


    public void startActivityForResult(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        ((Activity) (mContext)).startActivityForResult(intent, 1001);
    }

    @Override
    public void onClick(View v) {
        if (null == etDetailAddress.getText() || "".equals(etDetailAddress.getText().toString().trim())) {
            CommonUtil.toast("请填写详细地址");
            return;
        }
        etArea.setText(mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "," + etDetailAddress.getText().toString());
        etArea.setTag(mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName);
        dismiss();
    }

    public void dismiss() {
        if (mDialog == null) {
            return;
        }
        mDialog.hide();
        mDialog.dismiss();
    }


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }

    }
}
