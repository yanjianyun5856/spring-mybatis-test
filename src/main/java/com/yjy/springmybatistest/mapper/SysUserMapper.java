package com.yjy.springmybatistest.mapper;

import com.yjy.springmybatistest.entity.SysRole;
import com.yjy.springmybatistest.entity.SysUser;
import com.yjy.springmybatistest.entity2.User;
import com.yjy.springmybatistest.entity2.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    SysUser selectUserById(String id);

    List<SysUser> selectUserAll ();

    List<SysRole> selectRolesByUserId(Long id);

    int addUser(SysUser user);

    //返回自增主键
    int addUser2(SysUser user);

    int addUser3(SysUser user);

    int updateById(SysUser user);

    int deleteById(Long id);

    //多个参数时使用＠Param 注解
    List<SysRole> selectRolesByUseridAndRoleEnable(
            @Param("userId") Long userId ,
            @Param("enable") Integer enable ) ;

    /**
     * 根据动态条件查询用户信息
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 根据用户 id 或用户名查询
     * @param sysUser
     * @return
     */
    SysUser selectByidOrUserName(SysUser sysUser) ;
}