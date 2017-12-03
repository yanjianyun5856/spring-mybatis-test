package com.yjy.springmybatistest.demo;

import com.yjy.springmybatistest.entity.SysRole;
import com.yjy.springmybatistest.entity.SysUser;
import com.yjy.springmybatistest.mapper.SysRoleMapper;
import com.yjy.springmybatistest.mapper.SysUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {
    @Test
    public void testSelectRoleByid() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession() ;
        try {
            //获取 SysRoleMapper 接 口
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class) ;
            //调用 selectByid 方法，查询 id = 1 的用户
            SysRole role = roleMapper.selectByid(1L);
            //user 不为空
            Assert.assertNotNull(role) ;
            //userName = admin
            Assert.assertEquals("管理员",role.getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession . close() ;
        }
    }

    @Test
    public void testSelectRoleByid2() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession() ;
        try {
            //获取 SysRoleMapper 接 口
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class) ;
            //调用 selectByid 方法，查询 id = 1 的用户
            SysRole role = roleMapper.selectByid2(1L);
            //user 不为空
            Assert.assertNotNull(role) ;
            //userName = admin
            Assert.assertEquals("管理员",role.getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession . close() ;
        }
    }
    @Test
    public void testSelectAll() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession() ;
        try {
            //获取 SysRoleMapper 接 口
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class) ;
            //调用 selectAll
            List<SysRole> roles = roleMapper.selectAll();
            //user 不为空
            Assert.assertNotNull(roles) ;
            //userName = admin
            Assert.assertEquals("管理员",roles.get(0).getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession . close() ;
        }
    }

    @Test
    public void testinsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            //创建一个 SysRole 对象
            SysRole role= new SysRole() ;
            role.setCreateBy(1L);
            role.setCreateTime(new Date());
            role.setEnable(1);
            role.setRoleName("管理员");
            //将新建的对象插入数据库中，特别注意这里的返回值 result 是执行的 SQL 影响的行数
            //int result= roleMapper.insert2(role);
            int result= roleMapper.insert3(role);
            //只插入 l 条数据
            Assert.assertEquals(1, result);
            //id 不为空
            Assert.assertNotNull(role.getId());
            System.out.println(role.getId());
        } finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession （）是不自动提交的
            //因此不手动执行 commit 也不会提交到数据库
            //sqlSession.commit();
            sqlSession.rollback();
            //不要忘记关闭 sqlSessio口
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);

            //调用 selectByid 方法，查询 id = 1 的用户
            SysRole role= roleMapper.selectByid(3L);

            role.setCreateBy(1L);
            role.setCreateTime(new Date());
            role.setEnable(1);
            role.setRoleName("管理员1");
            //更新数据
            int result= roleMapper.updateByid(role);
            //只插入 l 条数据
            Assert.assertEquals(1, result);

        } finally {
            //为了不影响其他测试，这里选择回滚
            //由于默认的 sqlSessionFactory.openSession （）是不自动提交的
            //因此不手动执行 commit 也不会提交到数据库
            sqlSession.commit();
            //sqlSession.rollback();
            //不要忘记关闭 sqlSessio口
            sqlSession.close();
        }

    }

    @Test
    public void testSelectRolesByUseridAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession() ;
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            //调用 selectRolesByUseridAndRoleEnabled 方法查询用户的角色
            List<SysRole> userList =
                    userMapper.selectRolesByUseridAndRoleEnable(1L,1);
            //结果不为空
            Assert.assertNotNull(userList);
            //角色数量大于 0 个
            Assert.assertTrue(userList.size() > 0);
        } finally{
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
