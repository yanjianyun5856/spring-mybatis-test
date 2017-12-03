package com.yjy.springmybatistest.demo;

import com.yjy.springmybatistest.entity.SysRole;
import com.yjy.springmybatistest.entity.SysUser;
import com.yjy.springmybatistest.mapper.SysUserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class SysUserMappertTest extends BaseMapperTest {
    @Test
    public void testSelectUserByid() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession() ;
        try {
            //获取 UserMapper 接 口
            SysUserMapper userMapper = sqlSession.getMapper (SysUserMapper.class) ;
            //调用 selectByid 方法，查询 id = 1 的用户
            SysUser user= userMapper.selectUserById("1");
            //user 不为空
            Assert.assertNotNull(user) ;
            //userName = admin
            Assert.assertEquals("admin",user.getUserName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession . close() ;
        }
    }

    @Test
    public void testSelectAll () {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接 口
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            //调用 selectAll 方法查询所有用户
            List<SysUser> userList = userMapper.selectUserAll();
            //结采不为空
            Assert.assertNotNull(userList);
            //用户数量大于 0 个
            Assert.assertTrue(userList.size() > 0);
        } finally {
            //不妥忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId () {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接 口
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            //调用 selectAll 方法查询所有用户
            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            //结采不为空
            Assert.assertNotNull(roleList);
            //用户数量大于 0 个
            Assert.assertTrue(roleList.size() > 0);
            //Assert.assertEquals("普通用户",roleList.get(0).getRoleName());
            Assert.assertEquals("admin",roleList.get(0).getSysUser().getUserName());
        } finally {
            //不妥忘记关闭 sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testinsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            //创建一个 user 对象
            SysUser user= new SysUser() ;
            user.setId(1002L);
            user.setUserName ("testl");
            user.setUserPassword ("123456");
            user.setUserEmail ("test@mybatis.tk" );
            user.setUserinfo ("testinfo") ;
            //正常情况下应该读入一张图片存到 byte 数纽中
            user.setHeadimg(new byte[] {1 , 2 , 3});
            user.setCreateTime(new Date());
            //将新建的对象插入数据库中，特别注意这里的返回值 result 是执行的 SQL 影响的行数
            int result= userMapper.addUser(user);
            //只插入 l 条数据
            Assert.assertEquals(1, result);
            //id 为 null ，没有给 id 赋佳，并且没有配直回写 id 的值
            //Assert.assertNull(user.getId());
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
    public void testinsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            //创建一个 user 对象
            SysUser user= new SysUser() ;
            //user.setId(1002L);
            user.setUserName ("testl");
            user.setUserPassword ("123456");
            user.setUserEmail ("test@mybatis.tk" );
            user.setUserinfo ("testinfo") ;
            //正常情况下应该读入一张图片存到 byte 数纽中
            user.setHeadimg(new byte[] {1 , 2 , 3});
            user.setCreateTime(new Date());
            //将新建的对象插入数据库中，特别注意这里的返回值 result 是执行的 SQL 影响的行数
            int result= userMapper.addUser2(user);
            //只插入 l 条数据
            Assert.assertEquals(1, result);
            //id 不为空
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
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
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);

            //调用 selectByid 方法，查询 id = 1 的用户
            SysUser user= userMapper.selectUserById("1");

            user.setUserName ("admin");
            user.setUserEmail ("testupdate111@mybatis.tk" );
            //更新数据
            int result= userMapper.updateById(user);
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
