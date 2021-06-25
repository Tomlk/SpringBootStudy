package com.springboot03web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

//扩展MVC  dispatchservlet

//如果我们要扩展SpringMVC，官方建议我们这样去做
@Configuration
//@EnableWebMvc  //这玩意就是导入了一个类：DelegatingWebMvcConfiguration：从容器中获取所有的webMvcconfig：自动注解失效
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");
//        registry.addViewController("/index.html").setViewName("index");
    }

    //自定义的国际化组件生效
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).
                addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/css/**","/img/**","/js/**");
    }
}

