package io.github.sumailin.smartweather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.sumailin.smartweather.R;
import io.github.sumailin.smartweather.model.WeatherInfo;
import java.util.List;

/**
 * Created by zhonglin on 16/4/3.
 */
public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int TYPE_BASIC = 0;
  private static final int TYPE_TWO = 1;
  private static final int TYPE_THREE = 3;
  private static final int TYPE_FOUR = 4;

  private Context mContext;

  private LayoutInflater mInflater;

  private WeatherInfo weatherInfo;

  public WeatherRecyclerViewAdapter(Context context) {
    this.mContext = context;
    mInflater = LayoutInflater.from(context);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = null;
    if (viewType == TYPE_BASIC) {
      view = mInflater.inflate(R.layout.basic_city_layout, parent, false);
    }
    return new WeatherViewHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    WeatherViewHolder viewHolder = (WeatherViewHolder) holder;
    if (weatherInfo != null) {
      viewHolder.mTemperature.setText(
          String.format(mContext.getString(R.string.temperature), weatherInfo.getNow().getTmp()));
      viewHolder.mMaxTemperature.setText(String.format(mContext.getString(R.string.max_temperature),
          weatherInfo.getDaily_forecast().get(0).getTmp().getMax()));
      viewHolder.mMinTemperature.setText(String.format(mContext.getString(R.string.min_temperature),
          weatherInfo.getDaily_forecast().get(0).getTmp().getMin()));
      viewHolder.mAirQuality.setText(String.format(mContext.getString(R.string.air_quality),
          weatherInfo.getAqi().getCity().getQlty()));
      viewHolder.mPM.setText(String.format(mContext.getString(R.string.pm_quality),
          weatherInfo.getAqi().getCity().getPm25()));
    }
  }

  @Override public int getItemCount() {
    return 1;
  }

  @Override public int getItemViewType(int position) {
    switch (position) {
      case 0:
        return TYPE_BASIC;
      case 1:
        return TYPE_TWO;
      case 2:
        return TYPE_THREE;
      case 3:
        return TYPE_FOUR;
      default:
        return super.getItemViewType(position);
    }
  }

  public void updateWeatherInfos(List<WeatherInfo> values) {
    List<WeatherInfo> weatherInfoList = values;
    weatherInfo = weatherInfoList.get(0);
  }

  static class WeatherViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tv_temperature) TextView mTemperature;
    @Bind(R.id.tv_max_temperature) TextView mMaxTemperature;
    @Bind(R.id.tv_min_temperature) TextView mMinTemperature;
    @Bind(R.id.tv_air_quality) TextView mAirQuality;
    @Bind(R.id.tv_pm_quality) TextView mPM;

    public WeatherViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
