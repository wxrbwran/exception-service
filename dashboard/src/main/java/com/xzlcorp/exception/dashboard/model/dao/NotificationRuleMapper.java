package com.xzlcorp.exception.dashboard.model.dao;

import static com.xzlcorp.exception.dashboard.model.dao.NotificationRuleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationRule;
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
public interface NotificationRuleMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, data, whiteList, blackList, level, interval, open, recently, count, project, createdAt, updatedAt);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<NotificationRule> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<NotificationRule> records);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(MultiRowInsertStatementProvider<NotificationRule> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("NotificationRuleResult")
    Optional<NotificationRule> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="NotificationRuleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="data", property="data", typeHandler=JsonbTypeHandler.class, jdbcType=JdbcType.OTHER),
        @Result(column="white_list", property="whiteList", typeHandler=JsonbTypeHandler.class, jdbcType=JdbcType.OTHER),
        @Result(column="black_list", property="blackList", typeHandler=JsonbTypeHandler.class, jdbcType=JdbcType.OTHER),
        @Result(column="level", property="level", jdbcType=JdbcType.VARCHAR),
        @Result(column="interval", property="interval", jdbcType=JdbcType.INTEGER),
        @Result(column="open", property="open", jdbcType=JdbcType.BIT),
        @Result(column="recently", property="recently", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<NotificationRule> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, notificationRule, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, notificationRule, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(NotificationRule record) {
        return MyBatis3Utils.insert(this::insert, record, notificationRule, c ->
            c.map(name).toProperty("name")
            .map(data).toProperty("data")
            .map(whiteList).toProperty("whiteList")
            .map(blackList).toProperty("blackList")
            .map(level).toProperty("level")
            .map(interval).toProperty("interval")
            .map(open).toProperty("open")
            .map(recently).toProperty("recently")
            .map(count).toProperty("count")
            .map(project).toProperty("project")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<NotificationRule> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, notificationRule, c ->
            c.map(name).toProperty("name")
            .map(data).toProperty("data")
            .map(whiteList).toProperty("whiteList")
            .map(blackList).toProperty("blackList")
            .map(level).toProperty("level")
            .map(interval).toProperty("interval")
            .map(open).toProperty("open")
            .map(recently).toProperty("recently")
            .map(count).toProperty("count")
            .map(project).toProperty("project")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(NotificationRule record) {
        return MyBatis3Utils.insert(this::insert, record, notificationRule, c ->
            c.map(name).toPropertyWhenPresent("name", record::getName)
            .map(data).toPropertyWhenPresent("data", record::getData)
            .map(whiteList).toPropertyWhenPresent("whiteList", record::getWhiteList)
            .map(blackList).toPropertyWhenPresent("blackList", record::getBlackList)
            .map(level).toPropertyWhenPresent("level", record::getLevel)
            .map(interval).toPropertyWhenPresent("interval", record::getInterval)
            .map(open).toPropertyWhenPresent("open", record::getOpen)
            .map(recently).toPropertyWhenPresent("recently", record::getRecently)
            .map(count).toPropertyWhenPresent("count", record::getCount)
            .map(project).toPropertyWhenPresent("project", record::getProject)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<NotificationRule> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, notificationRule, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<NotificationRule> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, notificationRule, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<NotificationRule> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, notificationRule, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<NotificationRule> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, notificationRule, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(NotificationRule record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(record::getName)
                .set(data).equalTo(record::getData)
                .set(whiteList).equalTo(record::getWhiteList)
                .set(blackList).equalTo(record::getBlackList)
                .set(level).equalTo(record::getLevel)
                .set(interval).equalTo(record::getInterval)
                .set(open).equalTo(record::getOpen)
                .set(recently).equalTo(record::getRecently)
                .set(count).equalTo(record::getCount)
                .set(project).equalTo(record::getProject)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(NotificationRule record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(record::getName)
                .set(data).equalToWhenPresent(record::getData)
                .set(whiteList).equalToWhenPresent(record::getWhiteList)
                .set(blackList).equalToWhenPresent(record::getBlackList)
                .set(level).equalToWhenPresent(record::getLevel)
                .set(interval).equalToWhenPresent(record::getInterval)
                .set(open).equalToWhenPresent(record::getOpen)
                .set(recently).equalToWhenPresent(record::getRecently)
                .set(count).equalToWhenPresent(record::getCount)
                .set(project).equalToWhenPresent(record::getProject)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(NotificationRule record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(data).equalTo(record::getData)
            .set(whiteList).equalTo(record::getWhiteList)
            .set(blackList).equalTo(record::getBlackList)
            .set(level).equalTo(record::getLevel)
            .set(interval).equalTo(record::getInterval)
            .set(open).equalTo(record::getOpen)
            .set(recently).equalTo(record::getRecently)
            .set(count).equalTo(record::getCount)
            .set(project).equalTo(record::getProject)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(NotificationRule record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(data).equalToWhenPresent(record::getData)
            .set(whiteList).equalToWhenPresent(record::getWhiteList)
            .set(blackList).equalToWhenPresent(record::getBlackList)
            .set(level).equalToWhenPresent(record::getLevel)
            .set(interval).equalToWhenPresent(record::getInterval)
            .set(open).equalToWhenPresent(record::getOpen)
            .set(recently).equalToWhenPresent(record::getRecently)
            .set(count).equalToWhenPresent(record::getCount)
            .set(project).equalToWhenPresent(record::getProject)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}