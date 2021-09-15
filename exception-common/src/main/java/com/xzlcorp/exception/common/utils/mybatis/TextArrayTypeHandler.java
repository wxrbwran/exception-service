package com.xzlcorp.exception.common.utils.mybatis;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author Manni Wood
 */
@MappedJdbcTypes(JdbcType.OTHER)
@MappedTypes(String[].class)
public class TextArrayTypeHandler extends BaseTypeHandler<String[]> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i,
                                  String[] parameter, JdbcType jdbcType) throws SQLException {
    Connection c = ps.getConnection();
    Array inArray = c.createArrayOf("text", parameter);
    ps.setArray(i, inArray);
  }

  @Override
  public String[] getNullableResult(ResultSet rs, String columnName)
      throws SQLException {
    Array outputArray = rs.getArray(columnName);
    if (outputArray == null) {
      return null;
    }
    return (String[])outputArray.getArray();
  }

  @Override
  public String[] getNullableResult(ResultSet rs, int columnIndex)
      throws SQLException {
    Array outputArray = rs.getArray(columnIndex);
    if (outputArray == null) {
      return null;
    }
    return (String[])outputArray.getArray();
  }

  @Override
  public String[] getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    Array outputArray = cs.getArray(columnIndex);
    if (outputArray == null) {
      return null;
    }
    return (String[])outputArray.getArray();
  }
}