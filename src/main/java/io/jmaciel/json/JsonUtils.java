package io.jmaciel.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static String toJson(Object object) throws JsonProcessingException {
    if (object == null) {
      throw new IllegalArgumentException("Object to serialize must not be null");
    }
    return objectMapper.writeValueAsString(object);
  }

  public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
    if (json == null || json.isEmpty()) {
      throw new IllegalArgumentException("JSON string must not be null or empty");
    }
    return objectMapper.readValue(json, clazz);
  }

}
