package com.example.springbootdemo.mapper.goodhope;

import com.example.springbootdemo.pojo.goodhope.User;

import java.util.List;

public interface UserMapper {
    //这个方式我自己加的
    List<User> selectAllUser();
}
