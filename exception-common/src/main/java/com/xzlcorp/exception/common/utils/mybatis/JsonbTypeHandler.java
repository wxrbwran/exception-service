package com.xzlcorp.exception.common.utils.mybatis;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

/**
 * @author daqiang
 * @date 2020/8/25 18:55
 **/
public class JsonbTypeHandler<T> extends JsonTypeHandler<T> {

  public JsonbTypeHandler(Class<T> clazz) {
    super(clazz);
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType)
      throws SQLException {
    PGobject jsonObject = new PGobject();
    jsonObject.setType("jsonb");
    jsonObject.setValue(toJson(parameter));
    ps.setObject(i, jsonObject);
  }
}
