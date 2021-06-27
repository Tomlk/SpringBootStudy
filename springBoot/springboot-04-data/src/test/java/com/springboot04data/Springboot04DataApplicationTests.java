package com.springboot04data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        //默认的数据源 com.zaxxer.hikari.HikariDataSource;
        System.out.println(dataSource.getClass());

        //获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        //xxxx Template: SpringBoot已经配置好的模板bean，拿来即用 CRUD
        //jbcd
        //redis


        //关闭
        connection.close();
    }

}
