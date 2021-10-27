package com.xzlcorp.exception.dashboard.model.dao;

import static com.xzlcorp.exception.dashboard.model.dao.NotificationSettingDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.xzlcorp.exception.dashboard.model.pojo.notification.NotificationSetting;
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
public interface NotificationSettingMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, emails, browser, webhooks, project, createdAt, updatedAt);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<NotificationSetting> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<NotificationSetting> records);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(MultiRowInsertStatementProvider<NotificationSetting> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("NotificationSettingResult")
    NotificationSetting selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="NotificationSettingResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="emails", property="emails", typeHandler=JsonbTypeHandler.class, jdbcType=JdbcType.OTHER),
        @Result(column="browser", property="browser", typeHandler=JsonbTypeHandler.class, jdbcType=JdbcType.OTHER),
        @Result(column="webhooks", property="webhooks", typeHandler=JsonbTypeHandler.class, jdbcType=JdbcType.OTHER),
        @Result(column="project", property="project", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<NotificationSetting> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, notificationSetting, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, notificationSetting, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(NotificationSetting record) {
        return MyBatis3Utils.insert(this::insert, record, notificationSetting, c ->
            c.map(emails).toProperty("emails")
            .map(browser).toProperty("browser")
            .map(webhooks).toProperty("webhooks")
            .map(project).toProperty("project")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<NotificationSetting> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, notificationSetting, c ->
            c.map(emails).toProperty("emails")
            .map(browser).toProperty("browser")
            .map(webhooks).toProperty("webhooks")
            .map(project).toProperty("project")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(NotificationSetting record) {
        return MyBatis3Utils.insert(this::insert, record, notificationSetting, c ->
            c.map(emails).toPropertyWhenPresent("emails", record::getEmails)
            .map(browser).toPropertyWhenPresent("browser", record::getBrowser)
            .map(webhooks).toPropertyWhenPresent("webhooks", record::getWebhooks)
            .map(project).toPropertyWhenPresent("project", record::getProject)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default NotificationSetting selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, notificationSetting, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<NotificationSetting> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, notificationSetting, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<NotificationSetting> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, notificationSetting, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default NotificationSetting selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, notificationSetting, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(NotificationSetting record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(emails).equalTo(record::getEmails)
                .set(browser).equalTo(record::getBrowser)
                .set(webhooks).equalTo(record::getWebhooks)
                .set(project).equalTo(record::getProject)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(NotificationSetting record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(emails).equalToWhenPresent(record::getEmails)
                .set(browser).equalToWhenPresent(record::getBrowser)
                .set(webhooks).equalToWhenPresent(record::getWebhooks)
                .set(project).equalToWhenPresent(record::getProject)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(NotificationSetting record) {
        return update(c ->
            c.set(emails).equalTo(record::getEmails)
            .set(browser).equalTo(record::getBrowser)
            .set(webhooks).equalTo(record::getWebhooks)
            .set(project).equalTo(record::getProject)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(NotificationSetting record) {
        return update(c ->
            c.set(emails).equalToWhenPresent(record::getEmails)
            .set(browser).equalToWhenPresent(record::getBrowser)
            .set(webhooks).equalToWhenPresent(record::getWebhooks)
            .set(project).equalToWhenPresent(record::getProject)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}