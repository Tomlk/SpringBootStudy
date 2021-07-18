package com.shirospringboot.config;

import com.shirospringboot.pojo.User;
import com.shirospringboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义Realm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthorizationInfo");


        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if(user==null){//没有这个人
            return null;
        }
        //Md5 加密 不可逆

        return new SimpleAuthenticationInfo("",user.getPwd(),"");
    }
}
