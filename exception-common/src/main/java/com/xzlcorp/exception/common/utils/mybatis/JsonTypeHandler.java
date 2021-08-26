package com.xzlcorp.exception.common.utils.mybatis;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;

/**
 * @author daqiang
 * @date 2020/8/25 18:54
 **/
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

  private static final ObjectMapper mapper = new ObjectMapper();
  Class<T> clazz;

  public void registerTimeModule(ObjectMapper m) {
    m.setSerializationInclusion(Include.NON_NULL);
    m.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    JavaTimeModule timeModule = new JavaTimeModule();
    timeModule.addSerializer(LocalDateTime.class, new JsonSerializer<>() {
      @Override
      public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
          throws IOException {
        gen.writeNumber(value.toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
      }
    });
    timeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<>() {
      @Override
      public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
          throws IOException, JsonProcessingException {
        Long timestamp = p.getLongValue();
        return LocalDateTime.ofEpochSecond(timestamp / 1000, 0, ZoneOffset.ofHours(8));
      }
    });
    m.registerModule(timeModule);
  }

  public JsonTypeHandler(Class<T> clazz) {
    this.clazz = Objects.requireNonNull(clazz, "Type argument cannot be null");
    registerTimeModule(mapper);
    mapper.setSerializationInclusion(Include.NON_NULL);
  }


  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType)
      throws SQLException {
    PGobject jsonObject = new PGobject();
    jsonObject.setType("json");
    jsonObject.setValue(toJson(parameter));
    ps.setObject(i, jsonObject);
  }

  @Override
  public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return toObject(Objects.toString(rs.getObject(columnName)), clazz);
  }

  @Override
  public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return toObject(Objects.toString(rs.getObject(columnIndex)), clazz);
  }

  @Override
  public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return toObject(Objects.toString(cs.getObject(columnIndex)), clazz);
  }

  String toJson(T object) {
    try {
      return mapper.writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private T toObject(String content, Class<T> clazz) {
    if (!StringUtils.isEmpty(content)) {
      try {
        return mapper.readValue(content, clazz);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      return null;
    }
  }
}
