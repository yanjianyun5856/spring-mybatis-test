package com.yjy.springmybatistest.demo;

import com.yjy.springmybatistest.entity2.User;
import com.yjy.springmybatistest.entity2.UserExample;
import com.yjy.springmybatistest.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

public class MyBatisGeneratorDemo {

    public static void main(String[] args) throws Exception {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("mybatis/mybatis-config.xml");
        Reader reader = new InputStreamReader(stream,"UTF-8");
        //ResourceLoader resourceLoader = new DefaultResourceLoader();
        //Resource resource = resourceLoader.getResource("classpath:/mybatis/mybatis-config.xml");
        //EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        //Reader reader = encodedResource.getReader();
        SqlSessionFactoryBuilder sessionFactoryBuilder =new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sessionFactoryBuilder.build(reader,"dev",new Properties());
        SqlSession session = sessionFactory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);
        UserExample example = new UserExample();

        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(1);

        List<User> users = userMapper.selectByExample(example);
        User user = users.get(0);
        //User user = session.selectOne("com.yjy.springmybatistest.mapper.UserMapper.selectOneUser",1);
        System.out.println(user.toString());
        session.close();
    }

}
