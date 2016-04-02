package io.github.sumailin.smartweather.model;

import java.util.List;

/**
 * Created by zhonglin on 16/4/2.
 */
public class WeatherInfo {

  /**
   * city : {"aqi":"116","co":"1","no2":"80","o3":"55","pm10":"153","pm25":"87","qlty":"轻度污染","so2":"24"}
   */

  private AqiEntity aqi;
  /**
   * city : 杭州
   * cnty : 中国
   * id : CN101210101
   * lat : 30.319000
   * lon : 120.165000
   * update : {"loc":"2016-04-02 17:55","utc":"2016-04-02 09:55"}
   */

  private BasicEntity basic;
  /**
   * cond : {"code":"104","txt":"阴"}
   * fl : 19
   * hum : 77
   * pcpn : 0
   * pres : 1012
   * tmp : 18
   * vis : 4
   * wind : {"deg":"10","dir":"北风","sc":"4-5","spd":"19"}
   */

  private NowEntity now;
  /**
   * aqi : {"city":{"aqi":"116","co":"1","no2":"80","o3":"55","pm10":"153","pm25":"87","qlty":"轻度污染","so2":"24"}}
   * basic : {"city":"杭州","cnty":"中国","id":"CN101210101","lat":"30.319000","lon":"120.165000","update":{"loc":"2016-04-02 17:55","utc":"2016-04-02 09:55"}}
   * daily_forecast : [{"astro":{"sr":"05:46","ss":"18:18"},"cond":{"code_d":"302","code_n":"306","txt_d":"雷阵雨","txt_n":"中雨"},"date":"2016-04-02","hum":"53","pcpn":"4.4","pop":"95","pres":"1012","tmp":{"max":"24","min":"16"},"vis":"10","wind":{"deg":"125","dir":"南风","sc":"3-4","spd":"10"}}]
   * hourly_forecast : [{"date":"2016-04-02 19:00","hum":"78","pop":"94","pres":"1012","tmp":"23","wind":{"deg":"53","dir":"东北风","sc":"微风","spd":"10"}},{"date":"2016-04-02 22:00","hum":"89","pop":"93","pres":"1013","tmp":"20","wind":{"deg":"106","dir":"东南风","sc":"微风","spd":"6"}}]
   * now : {"cond":{"code":"104","txt":"阴"},"fl":"19","hum":"77","pcpn":"0","pres":"1012","tmp":"18","vis":"4","wind":{"deg":"10","dir":"北风","sc":"4-5","spd":"19"}}
   * status : ok
   * suggestion : {"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"flu":{"brf":"易发","txt":"相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"},"sport":{"brf":"较不宜","txt":"有降水，且风力较强，较适宜在户内进行各种健身休闲运动；若坚持户外运动，请注意保暖。"},"trav":{"brf":"一般","txt":"有降水，请尽量不要外出，若外出，请注意防雷。风稍大但温度适宜，还是可以外出游玩的。"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
   */

  private String status;
  /**
   * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
   * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
   * drsg : {"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
   * flu : {"brf":"易发","txt":"相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。"}
   * sport : {"brf":"较不宜","txt":"有降水，且风力较强，较适宜在户内进行各种健身休闲运动；若坚持户外运动，请注意保暖。"}
   * trav : {"brf":"一般","txt":"有降水，请尽量不要外出，若外出，请注意防雷。风稍大但温度适宜，还是可以外出游玩的。"}
   * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
   */

  private SuggestionEntity suggestion;
  /**
   * astro : {"sr":"05:46","ss":"18:18"}
   * cond : {"code_d":"302","code_n":"306","txt_d":"雷阵雨","txt_n":"中雨"}
   * date : 2016-04-02
   * hum : 53
   * pcpn : 4.4
   * pop : 95
   * pres : 1012
   * tmp : {"max":"24","min":"16"}
   * vis : 10
   * wind : {"deg":"125","dir":"南风","sc":"3-4","spd":"10"}
   */

  private List<DailyForecastEntity> daily_forecast;
  /**
   * date : 2016-04-02 19:00
   * hum : 78
   * pop : 94
   * pres : 1012
   * tmp : 23
   * wind : {"deg":"53","dir":"东北风","sc":"微风","spd":"10"}
   */

  private List<HourlyForecastEntity> hourly_forecast;

  public AqiEntity getAqi() {
    return aqi;
  }

  public void setAqi(AqiEntity aqi) {
    this.aqi = aqi;
  }

  public BasicEntity getBasic() {
    return basic;
  }

  public void setBasic(BasicEntity basic) {
    this.basic = basic;
  }

  public NowEntity getNow() {
    return now;
  }

  public void setNow(NowEntity now) {
    this.now = now;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public SuggestionEntity getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(SuggestionEntity suggestion) {
    this.suggestion = suggestion;
  }

  public List<DailyForecastEntity> getDaily_forecast() {
    return daily_forecast;
  }

  public void setDaily_forecast(List<DailyForecastEntity> daily_forecast) {
    this.daily_forecast = daily_forecast;
  }

  public List<HourlyForecastEntity> getHourly_forecast() {
    return hourly_forecast;
  }

  public void setHourly_forecast(List<HourlyForecastEntity> hourly_forecast) {
    this.hourly_forecast = hourly_forecast;
  }

  public static class AqiEntity {
    /**
     * aqi : 116
     * co : 1
     * no2 : 80
     * o3 : 55
     * pm10 : 153
     * pm25 : 87
     * qlty : 轻度污染
     * so2 : 24
     */

    private CityEntity city;

    public CityEntity getCity() {
      return city;
    }

    public void setCity(CityEntity city) {
      this.city = city;
    }

    public static class CityEntity {
      private String aqi;
      private String co;
      private String no2;
      private String o3;
      private String pm10;
      private String pm25;
      private String qlty;
      private String so2;

      public String getAqi() {
        return aqi;
      }

      public void setAqi(String aqi) {
        this.aqi = aqi;
      }

      public String getCo() {
        return co;
      }

      public void setCo(String co) {
        this.co = co;
      }

      public String getNo2() {
        return no2;
      }

      public void setNo2(String no2) {
        this.no2 = no2;
      }

      public String getO3() {
        return o3;
      }

      public void setO3(String o3) {
        this.o3 = o3;
      }

      public String getPm10() {
        return pm10;
      }

      public void setPm10(String pm10) {
        this.pm10 = pm10;
      }

      public String getPm25() {
        return pm25;
      }

      public void setPm25(String pm25) {
        this.pm25 = pm25;
      }

      public String getQlty() {
        return qlty;
      }

      public void setQlty(String qlty) {
        this.qlty = qlty;
      }

      public String getSo2() {
        return so2;
      }

      public void setSo2(String so2) {
        this.so2 = so2;
      }
    }
  }

  public static class BasicEntity {
    private String city;
    private String cnty;
    private String id;
    private String lat;
    private String lon;
    /**
     * loc : 2016-04-02 17:55
     * utc : 2016-04-02 09:55
     */

    private UpdateEntity update;

    public String getCity() {
      return city;
    }

    public void setCity(String city) {
      this.city = city;
    }

    public String getCnty() {
      return cnty;
    }

    public void setCnty(String cnty) {
      this.cnty = cnty;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getLat() {
      return lat;
    }

    public void setLat(String lat) {
      this.lat = lat;
    }

    public String getLon() {
      return lon;
    }

    public void setLon(String lon) {
      this.lon = lon;
    }

    public UpdateEntity getUpdate() {
      return update;
    }

    public void setUpdate(UpdateEntity update) {
      this.update = update;
    }

    public static class UpdateEntity {
      private String loc;
      private String utc;

      public String getLoc() {
        return loc;
      }

      public void setLoc(String loc) {
        this.loc = loc;
      }

      public String getUtc() {
        return utc;
      }

      public void setUtc(String utc) {
        this.utc = utc;
      }
    }
  }

  public static class NowEntity {
    /**
     * code : 104
     * txt : 阴
     */

    private CondEntity cond;
    private String fl;
    private String hum;
    private String pcpn;
    private String pres;
    private String tmp;
    private String vis;
    /**
     * deg : 10
     * dir : 北风
     * sc : 4-5
     * spd : 19
     */

    private WindEntity wind;

    public CondEntity getCond() {
      return cond;
    }

    public void setCond(CondEntity cond) {
      this.cond = cond;
    }

    public String getFl() {
      return fl;
    }

    public void setFl(String fl) {
      this.fl = fl;
    }

    public String getHum() {
      return hum;
    }

    public void setHum(String hum) {
      this.hum = hum;
    }

    public String getPcpn() {
      return pcpn;
    }

    public void setPcpn(String pcpn) {
      this.pcpn = pcpn;
    }

    public String getPres() {
      return pres;
    }

    public void setPres(String pres) {
      this.pres = pres;
    }

    public String getTmp() {
      return tmp;
    }

    public void setTmp(String tmp) {
      this.tmp = tmp;
    }

    public String getVis() {
      return vis;
    }

    public void setVis(String vis) {
      this.vis = vis;
    }

    public WindEntity getWind() {
      return wind;
    }

    public void setWind(WindEntity wind) {
      this.wind = wind;
    }

    public static class CondEntity {
      private String code;
      private String txt;

      public String getCode() {
        return code;
      }

      public void setCode(String code) {
        this.code = code;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class WindEntity {
      private String deg;
      private String dir;
      private String sc;
      private String spd;

      public String getDeg() {
        return deg;
      }

      public void setDeg(String deg) {
        this.deg = deg;
      }

      public String getDir() {
        return dir;
      }

      public void setDir(String dir) {
        this.dir = dir;
      }

      public String getSc() {
        return sc;
      }

      public void setSc(String sc) {
        this.sc = sc;
      }

      public String getSpd() {
        return spd;
      }

      public void setSpd(String spd) {
        this.spd = spd;
      }
    }
  }

  public static class SuggestionEntity {
    /**
     * brf : 舒适
     * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
     */

    private ComfEntity comf;
    /**
     * brf : 不宜
     * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
     */

    private CwEntity cw;
    /**
     * brf : 较舒适
     * txt : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
     */

    private DrsgEntity drsg;
    /**
     * brf : 易发
     * txt : 相对于今天将会出现大幅度降温，空气湿度较大，易发生感冒，请注意适当增加衣服。
     */

    private FluEntity flu;
    /**
     * brf : 较不宜
     * txt : 有降水，且风力较强，较适宜在户内进行各种健身休闲运动；若坚持户外运动，请注意保暖。
     */

    private SportEntity sport;
    /**
     * brf : 一般
     * txt : 有降水，请尽量不要外出，若外出，请注意防雷。风稍大但温度适宜，还是可以外出游玩的。
     */

    private TravEntity trav;
    /**
     * brf : 最弱
     * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
     */

    private UvEntity uv;

    public ComfEntity getComf() {
      return comf;
    }

    public void setComf(ComfEntity comf) {
      this.comf = comf;
    }

    public CwEntity getCw() {
      return cw;
    }

    public void setCw(CwEntity cw) {
      this.cw = cw;
    }

    public DrsgEntity getDrsg() {
      return drsg;
    }

    public void setDrsg(DrsgEntity drsg) {
      this.drsg = drsg;
    }

    public FluEntity getFlu() {
      return flu;
    }

    public void setFlu(FluEntity flu) {
      this.flu = flu;
    }

    public SportEntity getSport() {
      return sport;
    }

    public void setSport(SportEntity sport) {
      this.sport = sport;
    }

    public TravEntity getTrav() {
      return trav;
    }

    public void setTrav(TravEntity trav) {
      this.trav = trav;
    }

    public UvEntity getUv() {
      return uv;
    }

    public void setUv(UvEntity uv) {
      this.uv = uv;
    }

    public static class ComfEntity {
      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class CwEntity {
      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class DrsgEntity {
      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class FluEntity {
      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class SportEntity {
      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class TravEntity {
      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }

    public static class UvEntity {
      private String brf;
      private String txt;

      public String getBrf() {
        return brf;
      }

      public void setBrf(String brf) {
        this.brf = brf;
      }

      public String getTxt() {
        return txt;
      }

      public void setTxt(String txt) {
        this.txt = txt;
      }
    }
  }

  public static class DailyForecastEntity {
    /**
     * sr : 05:46
     * ss : 18:18
     */

    private AstroEntity astro;
    /**
     * code_d : 302
     * code_n : 306
     * txt_d : 雷阵雨
     * txt_n : 中雨
     */

    private CondEntity cond;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    /**
     * max : 24
     * min : 16
     */

    private TmpEntity tmp;
    private String vis;
    /**
     * deg : 125
     * dir : 南风
     * sc : 3-4
     * spd : 10
     */

    private WindEntity wind;

    public AstroEntity getAstro() {
      return astro;
    }

    public void setAstro(AstroEntity astro) {
      this.astro = astro;
    }

    public CondEntity getCond() {
      return cond;
    }

    public void setCond(CondEntity cond) {
      this.cond = cond;
    }

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getHum() {
      return hum;
    }

    public void setHum(String hum) {
      this.hum = hum;
    }

    public String getPcpn() {
      return pcpn;
    }

    public void setPcpn(String pcpn) {
      this.pcpn = pcpn;
    }

    public String getPop() {
      return pop;
    }

    public void setPop(String pop) {
      this.pop = pop;
    }

    public String getPres() {
      return pres;
    }

    public void setPres(String pres) {
      this.pres = pres;
    }

    public TmpEntity getTmp() {
      return tmp;
    }

    public void setTmp(TmpEntity tmp) {
      this.tmp = tmp;
    }

    public String getVis() {
      return vis;
    }

    public void setVis(String vis) {
      this.vis = vis;
    }

    public WindEntity getWind() {
      return wind;
    }

    public void setWind(WindEntity wind) {
      this.wind = wind;
    }

    public static class AstroEntity {
      private String sr;
      private String ss;

      public String getSr() {
        return sr;
      }

      public void setSr(String sr) {
        this.sr = sr;
      }

      public String getSs() {
        return ss;
      }

      public void setSs(String ss) {
        this.ss = ss;
      }
    }

    public static class CondEntity {
      private String code_d;
      private String code_n;
      private String txt_d;
      private String txt_n;

      public String getCode_d() {
        return code_d;
      }

      public void setCode_d(String code_d) {
        this.code_d = code_d;
      }

      public String getCode_n() {
        return code_n;
      }

      public void setCode_n(String code_n) {
        this.code_n = code_n;
      }

      public String getTxt_d() {
        return txt_d;
      }

      public void setTxt_d(String txt_d) {
        this.txt_d = txt_d;
      }

      public String getTxt_n() {
        return txt_n;
      }

      public void setTxt_n(String txt_n) {
        this.txt_n = txt_n;
      }
    }

    public static class TmpEntity {
      private String max;
      private String min;

      public String getMax() {
        return max;
      }

      public void setMax(String max) {
        this.max = max;
      }

      public String getMin() {
        return min;
      }

      public void setMin(String min) {
        this.min = min;
      }
    }

    public static class WindEntity {
      private String deg;
      private String dir;
      private String sc;
      private String spd;

      public String getDeg() {
        return deg;
      }

      public void setDeg(String deg) {
        this.deg = deg;
      }

      public String getDir() {
        return dir;
      }

      public void setDir(String dir) {
        this.dir = dir;
      }

      public String getSc() {
        return sc;
      }

      public void setSc(String sc) {
        this.sc = sc;
      }

      public String getSpd() {
        return spd;
      }

      public void setSpd(String spd) {
        this.spd = spd;
      }
    }
  }

  public static class HourlyForecastEntity {
    private String date;
    private String hum;
    private String pop;
    private String pres;
    private String tmp;
    /**
     * deg : 53
     * dir : 东北风
     * sc : 微风
     * spd : 10
     */

    private WindEntity wind;

    public String getDate() {
      return date;
    }

    public void setDate(String date) {
      this.date = date;
    }

    public String getHum() {
      return hum;
    }

    public void setHum(String hum) {
      this.hum = hum;
    }

    public String getPop() {
      return pop;
    }

    public void setPop(String pop) {
      this.pop = pop;
    }

    public String getPres() {
      return pres;
    }

    public void setPres(String pres) {
      this.pres = pres;
    }

    public String getTmp() {
      return tmp;
    }

    public void setTmp(String tmp) {
      this.tmp = tmp;
    }

    public WindEntity getWind() {
      return wind;
    }

    public void setWind(WindEntity wind) {
      this.wind = wind;
    }

    public static class WindEntity {
      private String deg;
      private String dir;
      private String sc;
      private String spd;

      public String getDeg() {
        return deg;
      }

      public void setDeg(String deg) {
        this.deg = deg;
      }

      public String getDir() {
        return dir;
      }

      public void setDir(String dir) {
        this.dir = dir;
      }

      public String getSc() {
        return sc;
      }

      public void setSc(String sc) {
        this.sc = sc;
      }

      public String getSpd() {
        return spd;
      }

      public void setSpd(String spd) {
        this.spd = spd;
      }
    }
  }
}
