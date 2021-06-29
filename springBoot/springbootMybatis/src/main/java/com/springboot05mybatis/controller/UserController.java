package com.springboot05mybatis.controller;

import com.springboot05mybatis.mapper.UserMapper;
import com.springboot05mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<User> queryUserList(){
        List<User> userList = userMapper.queryUserList();
        for(User user:userList){
            System.out.println(user);
        }
        return userList;
    }

    @GetMapping("/addUser")
    public String addUser(){
        userMapper.addUser(new User(11,"阿毛","456789"));
        return "ok";
    }

    @GetMapping("/updateUser")
    public String updateUser(){
        userMapper.updateUser(new User(11,"阿毛","421319"));
        return "ok";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(){
        userMapper.deleteUser(11);
        return "ok";
    }

}
