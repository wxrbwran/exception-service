package com.xzlcorp.exception.dashboard.model.dao;

import static com.xzlcorp.exception.dashboard.model.dao.OrganizationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.xzlcorp.exception.dashboard.model.pojo.Organization;
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
public interface OrganizationMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, introduction, admin, projects, count, createdAt, updatedAt, users);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<Organization> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<Organization> records);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(MultiRowInsertStatementProvider<Organization> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrganizationResult")
    Organization selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrganizationResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin", property="admin", jdbcType=JdbcType.INTEGER),
        @Result(column="projects", property="projects", typeHandler=IntArrayTypeHandler.class, jdbcType=JdbcType.ARRAY),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="users", property="users", typeHandler=IntArrayTypeHandler.class, jdbcType=JdbcType.ARRAY)
    })
    List<Organization> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, organization, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, organization, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Organization record) {
        return MyBatis3Utils.insert(this::insert, record, organization, c ->
            c.map(name).toProperty("name")
            .map(introduction).toProperty("introduction")
            .map(admin).toProperty("admin")
            .map(projects).toProperty("projects")
            .map(count).toProperty("count")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(users).toProperty("users")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Organization> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, organization, c ->
            c.map(name).toProperty("name")
            .map(introduction).toProperty("introduction")
            .map(admin).toProperty("admin")
            .map(projects).toProperty("projects")
            .map(count).toProperty("count")
            .map(createdAt).toProperty("createdAt")
            .map(updatedAt).toProperty("updatedAt")
            .map(users).toProperty("users")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Organization record) {
        return MyBatis3Utils.insert(this::insert, record, organization, c ->
            c.map(name).toPropertyWhenPresent("name", record::getName)
            .map(introduction).toPropertyWhenPresent("introduction", record::getIntroduction)
            .map(admin).toPropertyWhenPresent("admin", record::getAdmin)
            .map(projects).toPropertyWhenPresent("projects", record::getProjects)
            .map(count).toPropertyWhenPresent("count", record::getCount)
            .map(createdAt).toPropertyWhenPresent("createdAt", record::getCreatedAt)
            .map(updatedAt).toPropertyWhenPresent("updatedAt", record::getUpdatedAt)
            .map(users).toPropertyWhenPresent("users", record::getUsers)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Organization selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, organization, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Organization> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, organization, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Organization> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, organization, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Organization selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, organization, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Organization record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(record::getName)
                .set(introduction).equalTo(record::getIntroduction)
                .set(admin).equalTo(record::getAdmin)
                .set(projects).equalTo(record::getProjects)
                .set(count).equalTo(record::getCount)
                .set(createdAt).equalTo(record::getCreatedAt)
                .set(updatedAt).equalTo(record::getUpdatedAt)
                .set(users).equalTo(record::getUsers);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Organization record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(record::getName)
                .set(introduction).equalToWhenPresent(record::getIntroduction)
                .set(admin).equalToWhenPresent(record::getAdmin)
                .set(projects).equalToWhenPresent(record::getProjects)
                .set(count).equalToWhenPresent(record::getCount)
                .set(createdAt).equalToWhenPresent(record::getCreatedAt)
                .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
                .set(users).equalToWhenPresent(record::getUsers);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Organization record) {
        return update(c ->
            c.set(name).equalTo(record::getName)
            .set(introduction).equalTo(record::getIntroduction)
            .set(admin).equalTo(record::getAdmin)
            .set(projects).equalTo(record::getProjects)
            .set(count).equalTo(record::getCount)
            .set(createdAt).equalTo(record::getCreatedAt)
            .set(updatedAt).equalTo(record::getUpdatedAt)
            .set(users).equalTo(record::getUsers)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Organization record) {
        return update(c ->
            c.set(name).equalToWhenPresent(record::getName)
            .set(introduction).equalToWhenPresent(record::getIntroduction)
            .set(admin).equalToWhenPresent(record::getAdmin)
            .set(projects).equalToWhenPresent(record::getProjects)
            .set(count).equalToWhenPresent(record::getCount)
            .set(createdAt).equalToWhenPresent(record::getCreatedAt)
            .set(updatedAt).equalToWhenPresent(record::getUpdatedAt)
            .set(users).equalToWhenPresent(record::getUsers)
            .where(id, isEqualTo(record::getId))
        );
    }
}