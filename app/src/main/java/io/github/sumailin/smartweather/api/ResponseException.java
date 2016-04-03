package io.github.sumailin.smartweather.api;

import io.github.sumailin.smartweather.R;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhonglin on 16/4/3.
 */
public class ResponseException extends RuntimeException {

  public static final String OK = "ok";
  public static final String INVALID_KEY = "invalid key";
  public static final String UNKNOWN_CITY = "unknown city";
  public static final String NO_MORE_REQUESTS = "no more requests";
  public static final String ANR = "anr";
  public static final String PERMISSION_DENIED = "permission denied";
  public static final String UNKNOW_ERROR = "unknow error";

  private static Map<String, Integer> map = new HashMap<>();

  static {
    map.put(INVALID_KEY, R.string.api_error_1);
    map.put(UNKNOWN_CITY, R.string.api_error_2);
    map.put(NO_MORE_REQUESTS, R.string.api_error_3);
    map.put(ANR, R.string.api_error_4);
    map.put(PERMISSION_DENIED, R.string.api_error_5);
    map.put(UNKNOW_ERROR, R.string.unknow_error);
  }

  private String errNo;

  public ResponseException(String errNo) {
    this.errNo = errNo;
  }

  public Integer getErrMessage() {
    return map.get(errNo);
  }
}
