//package com.example.crud.config;
//
//import com.example.crud.bean.ApplicationUser;
//import com.example.crud.dao.UserDao;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class UserReaml extends AuthorizingRealm {
//    @Autowired
//    private UserDao userDao;
//
//    /**
//     * 执行授权逻辑
//     * @param principalCollection
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("执行授权逻辑");
//        return null;
//    }
//
//    /**
//     * 执行认证逻辑
//     * @param authenticationToken
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("执行认证逻辑");
//
//        UsernamePasswordToken Token = (UsernamePasswordToken) authenticationToken;
//
//        //数据库账户密码
//        List<ApplicationUser> userList = userDao.findByName(Token.getUsername());
//        //1.判断名
//        System.out.println("登录帐号为:" + Token.getUsername() + "密码为：" + Token.getPassword());
//        if (userList == null || userList.size() != 1) {
//            System.out.println("未查询到信息");
//            return null;
//        }
//        //2.判断密码
//        ApplicationUser dbUser = userList.get(0);
//        System.out.println("数据库查询到的密码为："+dbUser.getUserpassword());
//        return new SimpleAuthenticationInfo("", dbUser.getUserpassword(),"");
//    }
//}
