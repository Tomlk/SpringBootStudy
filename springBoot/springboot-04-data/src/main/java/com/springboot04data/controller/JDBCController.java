package com.springboot04data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    //没有实体类，数据库中的东西，如何获取。
    @GetMapping("/userList")
    public List<Map<String,Object>> userList(){
        String sql="select * from user";
        List<Map<String, Object>> list_map = jdbcTemplate.queryForList(sql);
        return list_map;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql="insert into mybatis.user values (12,'小明','123456')";
        int update = jdbcTemplate.update(sql);
        return "add ok"+update;

    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql="update mybatis.user set name=?,pwd=? where id="+id;

        Object[] objects = new Object[2];
        objects[0]="小明2";
        objects[0]="zzzzz";
        jdbcTemplate.update(sql,objects);
        return "update ok";

    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql="delete from mybatis.user where id="+id;
        jdbcTemplate.update(sql);
        return "delete ok";

    }

}
