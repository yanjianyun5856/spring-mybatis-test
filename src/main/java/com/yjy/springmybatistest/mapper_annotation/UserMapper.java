package com.yjy.springmybatistest.mapper_annotation;

import com.yjy.springmybatistest.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Results(value={
            @Result(property = "id", column="id",id=true),
            @Result(property = "name", column="name"),
            @Result(property = "age", column="age")
    })
    @Select("select id,name,age from user where id=#{id}")
    User selectUser(long id);
}
