package com.xzlcorp.exception.dashboard.model.dao;

import static com.xzlcorp.exception.dashboard.model.dao.InviteDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.xzlcorp.exception.dashboard.model.pojo.Invite;
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
public interface InviteMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, uuid, hash, auth, url, expires, projects, organization, inviter);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.id")
    int insert(InsertStatementProvider<Invite> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @Insert({
        "${insertStatement}"
    })
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<Invite> records);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(MultiRowInsertStatementProvider<Invite> multipleInsertStatement) {
        return insertMultiple(multipleInsertStatement.getInsertStatement(), multipleInsertStatement.getRecords());
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("InviteResult")
    Optional<Invite> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="InviteResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uuid", property="uuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="hash", property="hash", jdbcType=JdbcType.VARCHAR),
        @Result(column="auth", property="auth", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="expires", property="expires", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="projects", property="projects", typeHandler=IntArrayTypeHandler.class, jdbcType=JdbcType.ARRAY),
        @Result(column="organization", property="organization", jdbcType=JdbcType.INTEGER),
        @Result(column="inviter", property="inviter", jdbcType=JdbcType.INTEGER)
    })
    List<Invite> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, invite, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, invite, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(Invite record) {
        return MyBatis3Utils.insert(this::insert, record, invite, c ->
            c.map(uuid).toProperty("uuid")
            .map(hash).toProperty("hash")
            .map(auth).toProperty("auth")
            .map(url).toProperty("url")
            .map(expires).toProperty("expires")
            .map(projects).toProperty("projects")
            .map(organization).toProperty("organization")
            .map(inviter).toProperty("inviter")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<Invite> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, invite, c ->
            c.map(uuid).toProperty("uuid")
            .map(hash).toProperty("hash")
            .map(auth).toProperty("auth")
            .map(url).toProperty("url")
            .map(expires).toProperty("expires")
            .map(projects).toProperty("projects")
            .map(organization).toProperty("organization")
            .map(inviter).toProperty("inviter")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(Invite record) {
        return MyBatis3Utils.insert(this::insert, record, invite, c ->
            c.map(uuid).toPropertyWhenPresent("uuid", record::getUuid)
            .map(hash).toPropertyWhenPresent("hash", record::getHash)
            .map(auth).toPropertyWhenPresent("auth", record::getAuth)
            .map(url).toPropertyWhenPresent("url", record::getUrl)
            .map(expires).toPropertyWhenPresent("expires", record::getExpires)
            .map(projects).toPropertyWhenPresent("projects", record::getProjects)
            .map(organization).toPropertyWhenPresent("organization", record::getOrganization)
            .map(inviter).toPropertyWhenPresent("inviter", record::getInviter)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Invite> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, invite, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Invite> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, invite, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<Invite> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, invite, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<Invite> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, invite, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(Invite record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(uuid).equalTo(record::getUuid)
                .set(hash).equalTo(record::getHash)
                .set(auth).equalTo(record::getAuth)
                .set(url).equalTo(record::getUrl)
                .set(expires).equalTo(record::getExpires)
                .set(projects).equalTo(record::getProjects)
                .set(organization).equalTo(record::getOrganization)
                .set(inviter).equalTo(record::getInviter);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Invite record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(uuid).equalToWhenPresent(record::getUuid)
                .set(hash).equalToWhenPresent(record::getHash)
                .set(auth).equalToWhenPresent(record::getAuth)
                .set(url).equalToWhenPresent(record::getUrl)
                .set(expires).equalToWhenPresent(record::getExpires)
                .set(projects).equalToWhenPresent(record::getProjects)
                .set(organization).equalToWhenPresent(record::getOrganization)
                .set(inviter).equalToWhenPresent(record::getInviter);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(Invite record) {
        return update(c ->
            c.set(uuid).equalTo(record::getUuid)
            .set(hash).equalTo(record::getHash)
            .set(auth).equalTo(record::getAuth)
            .set(url).equalTo(record::getUrl)
            .set(expires).equalTo(record::getExpires)
            .set(projects).equalTo(record::getProjects)
            .set(organization).equalTo(record::getOrganization)
            .set(inviter).equalTo(record::getInviter)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(Invite record) {
        return update(c ->
            c.set(uuid).equalToWhenPresent(record::getUuid)
            .set(hash).equalToWhenPresent(record::getHash)
            .set(auth).equalToWhenPresent(record::getAuth)
            .set(url).equalToWhenPresent(record::getUrl)
            .set(expires).equalToWhenPresent(record::getExpires)
            .set(projects).equalToWhenPresent(record::getProjects)
            .set(organization).equalToWhenPresent(record::getOrganization)
            .set(inviter).equalToWhenPresent(record::getInviter)
            .where(id, isEqualTo(record::getId))
        );
    }
}