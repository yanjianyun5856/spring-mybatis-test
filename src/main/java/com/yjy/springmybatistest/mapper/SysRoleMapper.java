package com.yjy.springmybatistest.mapper;

import com.yjy.springmybatistest.entity.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysRoleMapper {

    @Select({ "select id , role_name roleName , " +
            " enable, create_by createBy, " +
            "create_time createTime " +
            "from sys_role where id = #{id } " })
    SysRole selectByid(Long id) ;


    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enable", column = "enable"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select id,role_name , enable , create_by , create_time from sys_role where id = #{id }")
    SysRole selectByid2(Long id);

    @ResultMap("roleResultMap")
    @Select(" select * from sys_role ")
    List<SysRole> selectAll();

    @Insert({"insert into sys_role (role_name, enable, create_by, create_time) " +
            " values(#{roleName}, #{enable}, #{createBy },#{ createTime, jdbcType=TIMESTAMP })"})
    @Options(useGeneratedKeys =true, keyProperty ="id") //返回自增主键
    int insert2(SysRole sysRole);

    @Insert ({"insert into sys_role(role_name, enable, create_by, create_time ) " +
            "values(#{roleName} , #{enable}, #{createBy},#{ createTime , jdbcType= TIMESTAMP }) " })
    @SelectKey(
            statement = "SELECT LAST_INSERT_ID()",
            keyColumn = "id",
            keyProperty = "id",
            resultType = Long.class,
            before = false
    )
    int insert3(SysRole sysRole);

    @Update({ " update sys_role set role_name = #{roleName }, enable = #{enable}, create_by = #{createBy }, create_time = #{createTime, jdbcType=TIMESTAMP } where id = #{id }"})
    int updateByid(SysRole sysRole);

    @Delete ("delete from sys role where id = #{id }")
    int deleteByid(Long id);

}
