package com.xzlcorp.exception.manager.model.dao;

import static com.xzlcorp.exception.manager.model.dao.IssueDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler;
import com.xzlcorp.exception.common.utils.mybatis.JsonbTypeHandler;
import com.xzlcorp.exception.common.utils.mybatis.TextArrayTypeHandler;
import com.xzlcorp.exception.manager.model.pojo.Issue;
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
public interface IssueMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, intro, apiKey, type, metadata, events, eventsCount, users, usersCount, createdAt, updatedAt);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<Issue> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<Issue> records);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(MultiRowInsertStatementProvider<Issue> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("IssueResult")
    Issue selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="IssueResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="intro", property="intro", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_key", property="apiKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="metadata", property="metadata", typeHandler=JsonbTypeHandler.class, jdbcType=JdbcType.OTHER),
        @Result(column="events", property="events", typeHandler=TextArrayTypeHandler.class, jdbcType=JdbcType.ARRAY),
        @Result(column="events_count", property="eventsCount", jdbcType=JdbcType.INTEGER),
        @Result(column="users", property="users", typeHandler=IntArrayTypeHandler.class, jdbcType=JdbcType.ARRAY),
        @Result(column="users_count", property="usersCount", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Issue> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, issue, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, issue, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Issue record) {
        return MyBatis3Utils.insert(this::insert, record, issue, c ->
            c.map(intro).toProperty("intro")
            .map(apiKey).toProperty("apiKey")
            .map(type).toProperty("type")
            .map(metadata).toProperty("metadata")
            .map(events).toProperty("events")
            .map(eventsCount).toProperty("eventsCount")
            .map(users).toProperty("users")
            .map(usersCount).toProperty("usersCount")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Issue> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, issue, c ->
            c.map(intro).toProperty("intro")
            .map(apiKey).toProperty("apiKey")
            .map(type).toProperty("type")
            .map(metadata).toProperty("metadata")
            .map(events).toProperty("events")
            .map(eventsCount).toProperty("eventsCount")
            .map(users).toProperty("users")
            .map(usersCount).toProperty("usersCount")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Issue record) {
        return MyBatis3Utils.insert(this::insert, record, issue, c ->
            c.map(intro).toPropertyWhenPresent("intro", record::getIntro)
            .map(apiKey).toPropertyWhenPresent("apiKey", record::getApiKey)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(metadata).toPropertyWhenPresent("metadata", record::getMetadata)
            .map(events).toPropertyWhenPresent("events", record::getEvents)
            .map(eventsCount).toPropertyWhenPresent("eventsCount", record::getEventsCount)
            .map(users).toPropertyWhenPresent("users", record::getUsers)
            .map(usersCount).toPropertyWhenPresent("usersCount", record::getUsersCount)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Issue selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, issue, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Issue> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, issue, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Issue> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, issue, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Issue selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, issue, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Issue record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(intro).equalTo(record::getIntro)
                .set(apiKey).equalTo(record::getApiKey)
                .set(type).equalTo(record::getType)
                .set(metadata).equalTo(record::getMetadata)
                .set(events).equalTo(record::getEvents)
                .set(eventsCount).equalTo(record::getEventsCount)
                .set(users).equalTo(record::getUsers)
                .set(usersCount).equalTo(record::getUsersCount)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Issue record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(intro).equalToWhenPresent(record::getIntro)
                .set(apiKey).equalToWhenPresent(record::getApiKey)
                .set(type).equalToWhenPresent(record::getType)
                .set(metadata).equalToWhenPresent(record::getMetadata)
                .set(events).equalToWhenPresent(record::getEvents)
                .set(eventsCount).equalToWhenPresent(record::getEventsCount)
                .set(users).equalToWhenPresent(record::getUsers)
                .set(usersCount).equalToWhenPresent(record::getUsersCount)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Issue record) {
        return update(c ->
            c.set(intro).equalTo(record::getIntro)
            .set(apiKey).equalTo(record::getApiKey)
            .set(type).equalTo(record::getType)
            .set(metadata).equalTo(record::getMetadata)
            .set(events).equalTo(record::getEvents)
            .set(eventsCount).equalTo(record::getEventsCount)
            .set(users).equalTo(record::getUsers)
            .set(usersCount).equalTo(record::getUsersCount)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Issue record) {
        return update(c ->
            c.set(intro).equalToWhenPresent(record::getIntro)
            .set(apiKey).equalToWhenPresent(record::getApiKey)
            .set(type).equalToWhenPresent(record::getType)
            .set(metadata).equalToWhenPresent(record::getMetadata)
            .set(events).equalToWhenPresent(record::getEvents)
            .set(eventsCount).equalToWhenPresent(record::getEventsCount)
            .set(users).equalToWhenPresent(record::getUsers)
            .set(usersCount).equalToWhenPresent(record::getUsersCount)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}