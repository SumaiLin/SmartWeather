package io.github.sumailin.smartweather;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.sumailin.smartweather.api.Const;
import io.github.sumailin.smartweather.api.ResponseAdapterFactory;
import io.github.sumailin.smartweather.api.ResponseException;
import io.github.sumailin.smartweather.api.WeatherService;
import io.github.sumailin.smartweather.model.WeatherInfo;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.main_toolbar) Toolbar mToolbar;
  @Bind(R.id.main_drawer_layout) DrawerLayout mDrawerLayout;
  @Bind(R.id.main_recyclerview) RecyclerView mWeatherRecyclerView;

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
          Log.e("AmapError",
              "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation
                  .getErrorInfo());
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

    //initLocation();
    requestWeather();
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

  private void requestWeather() {
    LinkedHashMap<String, String> params = new LinkedHashMap<>();
    params.put("cityid", "CN101210101");
    params.put("key", Const.HeFengAppKey);

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ResponseAdapterFactory()).create();

    new Retrofit.Builder().baseUrl(Const.Domain)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(WeatherService.class)
        .getWeather(params)
        .enqueue(new Callback<List<WeatherInfo>>() {
          @Override public void onResponse(Call<List<WeatherInfo>> call, Response<List<WeatherInfo>> response) {
            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
          }

          @Override public void onFailure(Call<List<WeatherInfo>> call, Throwable t) {
            if (t instanceof ResponseException) {
              Toast.makeText(MainActivity.this, getString(((ResponseException) t).getErrMessage()),
                  Toast.LENGTH_SHORT).show();
            }
          }
        });
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
