package io.github.sumailin.smartweather.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
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
import io.github.sumailin.smartweather.R;
import io.github.sumailin.smartweather.adapter.WeatherRecyclerViewAdapter;
import io.github.sumailin.smartweather.api.Const;
import io.github.sumailin.smartweather.api.ResponseAdapterFactory;
import io.github.sumailin.smartweather.api.ResponseException;
import io.github.sumailin.smartweather.api.WeatherService;
import io.github.sumailin.smartweather.model.WeatherInfo;
import java.util.LinkedHashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
    implements AMapLocationListener, SwipeRefreshLayout.OnRefreshListener,
    AppBarLayout.OnOffsetChangedListener {

  @Bind(R.id.main_toolbar) Toolbar mToolbar;
  @Bind(R.id.main_drawer_layout) DrawerLayout mDrawerLayout;
  @Bind(R.id.main_recyclerview) RecyclerView mWeatherRecyclerView;
  @Bind(R.id.main_refresh) SwipeRefreshLayout mRefresh;
  @Bind(R.id.appBarLayout) AppBarLayout mAppBarLayout;

  private WeatherRecyclerViewAdapter adapter;

  //声明AMapLocationClient类对象
  private AMapLocationClient mLocationClient = null;
  //声明mLocationOption对象
  public AMapLocationClientOption mLocationOption = null;

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

    initRefresh();
    initLocation();
    initRecylerView();
  }

  private void initRefresh() {
    mRefresh.setColorSchemeResources(android.R.color.holo_red_light,
        android.R.color.holo_orange_light, android.R.color.holo_blue_light,
        android.R.color.holo_green_light);
  }

  private void initRecylerView() {
    adapter = new WeatherRecyclerViewAdapter(this);
    mWeatherRecyclerView.setAdapter(adapter);
  }

  /**
   * 初始化高德地图
   */
  private void initLocation() {
    //初始化定位
    mLocationClient = new AMapLocationClient(getApplicationContext());
    //设置定位回调监听
    mLocationClient.setLocationListener(this);
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
    mLocationOption.setInterval(60 * 60 * 1000);
    //给定位客户端对象设置定位参数
    mLocationClient.setLocationOption(mLocationOption);
    //启动定位
    mLocationClient.startLocation();
  }

  private void requestWeather(String city) {

    //处理以下city格式
    city = city.replace("市", "");

    LinkedHashMap<String, String> params = new LinkedHashMap<>();
    params.put("city", city);
    params.put("key", Const.HeFengAppKey);

    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ResponseAdapterFactory()).create();

    new Retrofit.Builder().baseUrl(Const.Domain)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(WeatherService.class)
        .getWeather(params)
        .enqueue(new Callback<List<WeatherInfo>>() {
          @Override public void onResponse(Call<List<WeatherInfo>> call,
              Response<List<WeatherInfo>> response) {
            adapter.updateWeatherInfos(response.body());
            adapter.notifyDataSetChanged();

            mRefresh.setRefreshing(false);
          }

          @Override public void onFailure(Call<List<WeatherInfo>> call, Throwable t) {
            if (t instanceof ResponseException) {
              Toast.makeText(MainActivity.this, getString(((ResponseException) t).getErrMessage()),
                  Toast.LENGTH_SHORT).show();
            }
            mRefresh.setRefreshing(false);
          }
        });
  }

  @Override protected void onResume() {
    super.onResume();
    mAppBarLayout.addOnOffsetChangedListener(this);
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
    mAppBarLayout.removeOnOffsetChangedListener(this);
  }

  @Override public void onLocationChanged(AMapLocation aMapLocation) {
    if (aMapLocation != null) {
      if (aMapLocation.getErrorCode() == 0) {
        requestWeather(aMapLocation.getCity());
      } else {
        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
        Log.e("AmapError", "location Error, ErrCode:"
            + aMapLocation.getErrorCode()
            + ", errInfo:"
            + aMapLocation.getErrorInfo());
      }
    }
  }

  @Override public void onRefresh() {

  }

  @Override public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    mRefresh.setEnabled(verticalOffset == 0);
  }
}
