package com.shirospringboot;

import com.shirospringboot.pojo.User;
import com.shirospringboot.service.UserService;
import com.shirospringboot.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() {
        User user = userService.queryUserByName("小王");
        System.out.println(user);
    }

}
