package com.xzlcorp.exception.dashboard.model.dao;

import static com.xzlcorp.exception.dashboard.model.dao.SourceMapDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.xzlcorp.exception.dashboard.model.pojo.SourceMap;
import com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface SourceMapMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, apiKey, appVersion, appType, data, createdAt, updatedAt);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<SourceMap> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<SourceMap> records);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(MultiRowInsertStatementProvider<SourceMap> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SourceMapResult")
    Optional<SourceMap> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SourceMapResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="api_key", property="apiKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="app_version", property="appVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="app_type", property="appType", jdbcType=JdbcType.VARCHAR),
        @Result(column="data", property="data", typeHandler=JsonbTypeHandler.class, jdbcType=JdbcType.OTHER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SourceMap> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sourceMap, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sourceMap, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(SourceMap record) {
        return MyBatis3Utils.insert(this::insert, record, sourceMap, c ->
            c.map(apiKey).toProperty("apiKey")
            .map(appVersion).toProperty("appVersion")
            .map(appType).toProperty("appType")
            .map(data).toProperty("data")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<SourceMap> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sourceMap, c ->
            c.map(apiKey).toProperty("apiKey")
            .map(appVersion).toProperty("appVersion")
            .map(appType).toProperty("appType")
            .map(data).toProperty("data")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(SourceMap record) {
        return MyBatis3Utils.insert(this::insert, record, sourceMap, c ->
            c.map(apiKey).toPropertyWhenPresent("apiKey", record::getApiKey)
            .map(appVersion).toPropertyWhenPresent("appVersion", record::getAppVersion)
            .map(appType).toPropertyWhenPresent("appType", record::getAppType)
            .map(data).toPropertyWhenPresent("data", record::getData)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SourceMap> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sourceMap, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SourceMap> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sourceMap, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SourceMap> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sourceMap, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SourceMap> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sourceMap, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(SourceMap record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(apiKey).equalTo(record::getApiKey)
                .set(appVersion).equalTo(record::getAppVersion)
                .set(appType).equalTo(record::getAppType)
                .set(data).equalTo(record::getData)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SourceMap record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(apiKey).equalToWhenPresent(record::getApiKey)
                .set(appVersion).equalToWhenPresent(record::getAppVersion)
                .set(appType).equalToWhenPresent(record::getAppType)
                .set(data).equalToWhenPresent(record::getData)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(SourceMap record) {
        return update(c ->
            c.set(apiKey).equalTo(record::getApiKey)
            .set(appVersion).equalTo(record::getAppVersion)
            .set(appType).equalTo(record::getAppType)
            .set(data).equalTo(record::getData)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(SourceMap record) {
        return update(c ->
            c.set(apiKey).equalToWhenPresent(record::getApiKey)
            .set(appVersion).equalToWhenPresent(record::getAppVersion)
            .set(appType).equalToWhenPresent(record::getAppType)
            .set(data).equalToWhenPresent(record::getData)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}