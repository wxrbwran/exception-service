package com.xzlcorp.exception.dashboard.model.dao;

import com.xzlcorp.exception.dashboard.model.pojo.SourceMapData;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class SourceMapDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SourceMap sourceMap = new SourceMap();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = sourceMap.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> apiKey = sourceMap.apiKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> appVersion = sourceMap.appVersion;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> appType = sourceMap.appType;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<SourceMapData> data = sourceMap.data;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> createdAt = sourceMap.createdAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updatedAt = sourceMap.updatedAt;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class SourceMap extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> apiKey = column("api_key", JDBCType.VARCHAR);

        public final SqlColumn<String> appVersion = column("app_version", JDBCType.VARCHAR);

        public final SqlColumn<String> appType = column("app_type", JDBCType.VARCHAR);

        public final SqlColumn<SourceMapData> data = column("data", JDBCType.OTHER, "com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler");

        public final SqlColumn<Date> createdAt = column("created_at", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> updatedAt = column("updated_at", JDBCType.TIMESTAMP);

        public SourceMap() {
            super("s_exception.t_source_map");
        }
    }
}