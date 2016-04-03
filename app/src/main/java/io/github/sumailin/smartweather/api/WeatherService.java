package io.github.sumailin.smartweather.api;

import io.github.sumailin.smartweather.model.WeatherInfo;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhonglin on 16/4/2.
 */
public interface WeatherService {

  /**
   * 城市接口： https://api.heweather.com/x3/weather?cityid=城市ID&key=你的认证key
   */
  @GET("weather") Call<List<WeatherInfo>> getWeather(@QueryMap Map<String, String> options);
}
