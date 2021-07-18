package com.shirospringboot.service;

import com.shirospringboot.pojo.User;

public interface UserService {
    public User queryUserByName(String name);
}
