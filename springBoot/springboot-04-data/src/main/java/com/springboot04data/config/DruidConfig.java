package com.springboot04data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控功能
    //因为SpringBoot内置了servlet容器，所以没有web.xml，替代方法：ServletRegistrationBean
    @Bean
    public ServletRegistrationBean a(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登陆，账号密码配置

        HashMap<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername","admin"); //登陆key，是固定的
        initParameters.put("loginPassword","123456");

        //允许谁可以访问
        initParameters.put("allow","localhost");

        //禁止谁能访问
        initParameters.put("kuangshen","192.168.11.123");

        bean.setInitParameters(initParameters);//设置初始化参数

        return bean;
    }
    //filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        //可以过滤哪些请求
        HashMap<String, String> initParamters = new HashMap<>();
        //这些东西不进行统计
        initParamters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParamters);
        return bean;
    }
}
