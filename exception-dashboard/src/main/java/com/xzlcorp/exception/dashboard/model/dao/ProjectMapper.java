package com.xzlcorp.exception.dashboard.model.dao;

import static com.xzlcorp.exception.dashboard.model.dao.ProjectDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.xzlcorp.exception.dashboard.model.pojo.Project;
import com.xzlcorp.exception.common.utils.mybatis.IntArrayTypeHandler;
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
public interface ProjectMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, apiKey, name, type, admin, organization, users, notificationRules, notificationSetting, createdAt, updatedAt);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<Project> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<Project> records);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(MultiRowInsertStatementProvider<Project> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ProjectResult")
    Project selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ProjectResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="api_key", property="apiKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin", property="admin", jdbcType=JdbcType.INTEGER),
        @Result(column="organization", property="organization", jdbcType=JdbcType.INTEGER),
        @Result(column="users", property="users", typeHandler=IntArrayTypeHandler.class, jdbcType=JdbcType.ARRAY),
        @Result(column="notification_rules", property="notificationRules", typeHandler=IntArrayTypeHandler.class, jdbcType=JdbcType.ARRAY),
        @Result(column="notification_setting", property="notificationSetting", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Project> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, project, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, project, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Project record) {
        return MyBatis3Utils.insert(this::insert, record, project, c ->
            c.map(apiKey).toProperty("apiKey")
            .map(name).toProperty("name")
            .map(type).toProperty("type")
            .map(admin).toProperty("admin")
            .map(organization).toProperty("organization")
            .map(users).toProperty("users")
            .map(notificationRules).toProperty("notificationRules")
            .map(notificationSetting).toProperty("notificationSetting")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Project> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, project, c ->
            c.map(apiKey).toProperty("apiKey")
            .map(name).toProperty("name")
            .map(type).toProperty("type")
            .map(admin).toProperty("admin")
            .map(organization).toProperty("organization")
            .map(users).toProperty("users")
            .map(notificationRules).toProperty("notificationRules")
            .map(notificationSetting).toProperty("notificationSetting")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Project record) {
        return MyBatis3Utils.insert(this::insert, record, project, c ->
            c.map(apiKey).toPropertyWhenPresent("apiKey", record::getApiKey)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(admin).toPropertyWhenPresent("admin", record::getAdmin)
            .map(organization).toPropertyWhenPresent("organization", record::getOrganization)
            .map(users).toPropertyWhenPresent("users", record::getUsers)
            .map(notificationRules).toPropertyWhenPresent("notificationRules", record::getNotificationRules)
            .map(notificationSetting).toPropertyWhenPresent("notificationSetting", record::getNotificationSetting)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Project selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, project, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Project> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, project, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Project> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, project, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Project selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, project, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Project record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(apiKey).equalTo(record::getApiKey)
                .set(name).equalTo(record::getName)
                .set(type).equalTo(record::getType)
                .set(admin).equalTo(record::getAdmin)
                .set(organization).equalTo(record::getOrganization)
                .set(users).equalTo(record::getUsers)
                .set(notificationRules).equalTo(record::getNotificationRules)
                .set(notificationSetting).equalTo(record::getNotificationSetting)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Project record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(apiKey).equalToWhenPresent(record::getApiKey)
                .set(name).equalToWhenPresent(record::getName)
                .set(type).equalToWhenPresent(record::getType)
                .set(admin).equalToWhenPresent(record::getAdmin)
                .set(organization).equalToWhenPresent(record::getOrganization)
                .set(users).equalToWhenPresent(record::getUsers)
                .set(notificationRules).equalToWhenPresent(record::getNotificationRules)
                .set(notificationSetting).equalToWhenPresent(record::getNotificationSetting)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Project record) {
        return update(c ->
            c.set(apiKey).equalTo(record::getApiKey)
            .set(name).equalTo(record::getName)
            .set(type).equalTo(record::getType)
            .set(admin).equalTo(record::getAdmin)
            .set(organization).equalTo(record::getOrganization)
            .set(users).equalTo(record::getUsers)
            .set(notificationRules).equalTo(record::getNotificationRules)
            .set(notificationSetting).equalTo(record::getNotificationSetting)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Project record) {
        return update(c ->
            c.set(apiKey).equalToWhenPresent(record::getApiKey)
            .set(name).equalToWhenPresent(record::getName)
            .set(type).equalToWhenPresent(record::getType)
            .set(admin).equalToWhenPresent(record::getAdmin)
            .set(organization).equalToWhenPresent(record::getOrganization)
            .set(users).equalToWhenPresent(record::getUsers)
            .set(notificationRules).equalToWhenPresent(record::getNotificationRules)
            .set(notificationSetting).equalToWhenPresent(record::getNotificationSetting)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .where(id, isEqualTo(record::getId))
        );
    }
}