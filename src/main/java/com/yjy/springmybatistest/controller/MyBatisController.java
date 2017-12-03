package com.yjy.springmybatistest.controller;

import com.yjy.springmybatistest.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyBatisController {

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable int id){

        User user = sessionTemplate.selectOne("com.yjy.springmybatistest.mapper.UserMapper.selectOneUser",id);
        return user;
    }

}
