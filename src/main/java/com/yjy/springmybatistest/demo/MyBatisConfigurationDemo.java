package com.yjy.springmybatistest.demo;

import com.yjy.springmybatistest.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;


import java.io.Reader;
import java.util.Properties;

public class MyBatisConfigurationDemo {

    public static void main(String[] args) throws Exception {

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/mybatis/mybatis-config.xml");
        EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");
        Reader reader = encodedResource.getReader();
        SqlSessionFactoryBuilder sessionFactoryBuilder =new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sessionFactoryBuilder.build(reader,"dev",new Properties());
        SqlSession session = sessionFactory.openSession();
        User user = session.selectOne("com.yjy.springmybatistest.mapper.UserMapper.selectOneUser",1);
        System.out.println(user.toString());
        session.close();
    }

}
