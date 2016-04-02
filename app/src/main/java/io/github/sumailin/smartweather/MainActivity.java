package io.github.sumailin.smartweather;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.main_toolbar) Toolbar mToolbar;
  @Bind(R.id.main_drawer_layout) DrawerLayout mDrawerLayout;
  @Bind(R.id.main_recyclerview) RecyclerView mWeatherList;

  //声明AMapLocationClient类对象
  private AMapLocationClient mLocationClient = null;
  //声明mLocationOption对象
  public AMapLocationClientOption mLocationOption = null;
  //声明定位回调监听器
  public AMapLocationListener mLocationListener = new AMapLocationListener() {
    @Override public void onLocationChanged(AMapLocation aMapLocation) {
      if (aMapLocation != null) {
        if (aMapLocation.getErrorCode() == 0) {
          //定位成功回调信息，设置相关消息
          aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
          aMapLocation.getLatitude();//获取纬度
          aMapLocation.getLongitude();//获取经度
          aMapLocation.getAccuracy();//获取精度信息
          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date date = new Date(aMapLocation.getTime());
          df.format(date);//定位时间
          aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
          aMapLocation.getCountry();//国家信息
          aMapLocation.getProvince();//省信息
          aMapLocation.getCity();//城市信息
          aMapLocation.getDistrict();//城区信息
          aMapLocation.getStreet();//街道信息
          aMapLocation.getStreetNum();//街道门牌号信息
          aMapLocation.getCityCode();//城市编码
          aMapLocation.getAdCode();//地区编码
          Toast.makeText(MainActivity.this, aMapLocation.getProvince(), Toast.LENGTH_SHORT).show();
        } else {
          //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
          Log.e("AmapError","location Error, ErrCode:"
              + aMapLocation.getErrorCode() + ", errInfo:"
              + aMapLocation.getErrorInfo());
        }
      }
    }
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open_drawerlayout,
            R.string.close_drawerlayout);
    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    initLocation();
  }

  /**
   * 初始化高德地图
   */
  private void initLocation() {
    //初始化定位
    mLocationClient = new AMapLocationClient(getApplicationContext());
    //设置定位回调监听
    mLocationClient.setLocationListener(mLocationListener);
    //初始化定位参数
    mLocationOption = new AMapLocationClientOption();
    //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
    //设置是否返回地址信息（默认返回地址信息）
    mLocationOption.setNeedAddress(true);
    //设置是否只定位一次,默认为false
    mLocationOption.setOnceLocation(false);
    //设置是否强制刷新WIFI，默认为强制刷新
    mLocationOption.setWifiActiveScan(true);
    //设置是否允许模拟位置,默认为false，不允许模拟位置
    mLocationOption.setMockEnable(false);
    //设置定位间隔,单位毫秒,默认为2000ms
    mLocationOption.setInterval(2000);
    //给定位客户端对象设置定位参数
    mLocationClient.setLocationOption(mLocationOption);
    //启动定位
    mLocationClient.startLocation();
  }

  @Override protected void onPause() {
    super.onPause();
    if (mLocationClient != null) {
      mLocationClient.stopLocation();
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mLocationClient != null) {
      mLocationClient.onDestroy();
    }
  }
}
