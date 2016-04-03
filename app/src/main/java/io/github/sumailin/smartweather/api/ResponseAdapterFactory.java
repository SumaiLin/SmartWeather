package io.github.sumailin.smartweather.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 * Created by zhonglin on 16/4/3.
 * response应答转换器
 */
public class ResponseAdapterFactory implements TypeAdapterFactory {

  private static final String DATA = "HeWeather data service 3.0";
  private static final String STATUS_CODE = "status";

  @Override public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

    final TypeAdapter<JsonElement> elementTypeAdapter = gson.getAdapter(JsonElement.class);
    final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
    return new TypeAdapter<T>() {
      @Override public void write(JsonWriter out, T value) throws IOException {
        JsonElement tree = delegate.toJsonTree(value);
        if (value == null) {
          out.nullValue();
        } else {
          elementTypeAdapter.write(out, tree);
        }
      }

      @Override public T read(JsonReader in) throws IOException {
        JsonElement jsonElement = elementTypeAdapter.read(in);
        if (jsonElement.isJsonObject()) {
          JsonObject jsonObject = jsonElement.getAsJsonObject();
          if (jsonObject.has(DATA)) {
            JsonArray jsonArray = jsonObject.get(DATA).getAsJsonArray();
            //判断返回数据中的status
            JsonObject pbParsedResponse = jsonArray.get(0).getAsJsonObject();
            if (pbParsedResponse.has(STATUS_CODE)) {
              String code = pbParsedResponse.get(STATUS_CODE).getAsString();
              if (code.compareTo(ResponseException.OK) != 0) {
                throw new ResponseException(code);
              } else {
                return new Gson().fromJson(jsonObject.get(DATA), type.getType());
              }
            }
          }
        }
        throw new ResponseException(ResponseException.UNKNOW_ERROR);
      }
    }.nullSafe();
  }
}
