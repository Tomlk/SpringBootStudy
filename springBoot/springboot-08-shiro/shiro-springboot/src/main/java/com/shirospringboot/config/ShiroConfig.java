package com.shirospringboot.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //shiroFilterFactoryBean：3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);


        /*
            anon:无需认证即可访问
            authc:必须认证才能访问
            user:必须拥有 "记住我" 才能用
            perms: 拥有对某个资源的权限才能访问
            role:拥有某个角色权限才能访问
         */

        Map<String, String> filterMap=new LinkedHashMap<>();

        //权限
        filterMap.put("/user/add","perms[user:add]");
        //权限
        filterMap.put("/user/update","perms[user:update]");

        filterMap.put("/user/*","anon"); //无需认证

        filterMap.put("/user/update","authc"); //需要认证
        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录的请求
        bean.setLoginUrl("/toLogin");

        //未授权页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;

    }

    //DefaultWebSecurityManager:2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;

    }

    //创建 realm对象 ，需要自定义:1
    @Bean(name="userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }



}
