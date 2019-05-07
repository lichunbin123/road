package com.example.crud.service.serviceImpl;

import com.example.crud.bean.ApplicationUser;
import com.example.crud.dao.UserDao;
import com.example.crud.service.UserService;
import com.example.crud.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public ResponseMessage insert(ApplicationUser user) {
        if (user.getUsername() ==null || user.getUserpassword()== null){
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("登录信息不能为空").build();
        }
        List<ApplicationUser> datalist = userDao.findByName(user.getUsername());
        if (datalist!=null && datalist.size()>1){
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("账户已经存在").build();
        }
        userDao.insert(user);
        System.out.println("注册的用户名是===="+user.getUsername());
        System.out.println("注册的密码是===="+user.getUserpassword());
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("注册成功").build();
    }

    @Override
    public ResponseMessage verify(ApplicationUser loginUser) {
        return null;
    }

//    @Override
//    public ResponseMessage verify(ApplicationUser loginUser) {
//        /**
//         * 使用shiro编写认证逻辑
//         */
//        //1.获取subject
//        Subject subject = SecurityUtils.getSubject();
//        //2.封装用户数据
//        System.out.println("loginuser的密码是：==="+loginUser.getUserpassword());
//        UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUsername(),loginUser.getUserpassword());
//        //3.执行登录方法
//        try {
//            subject.login(token);
//            return ResponseMessage.<ApplicationUser>builder().successStatus(true)
//                    .messageContent("密码正确，允许登录").httpStatus(HttpStatus.OK).build();
//        }catch (AuthenticationException e){
//            System.out.println("认证出现错误");
//            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.NOT_ACCEPTABLE).messageContent("登录信息有误").build();
//        }

//        List<ApplicationUser> list = userDao.findByName(loginUser.getUsername());
//        if (list==null||list.size()!=1){
//            System.out.println("未查询到此信息");
//            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.NOT_ACCEPTABLE).messageContent("无此帐号").build();
//        }
//        ApplicationUser user = list.get(0);
//        if (user.getUserpassword().equals(loginUser.getUserpassword())){
//            List<ApplicationUser> tmpList = new ArrayList<>();
//            tmpList.add(user);
//            return ResponseMessage.<ApplicationUser>builder().successStatus(true).responseData(tmpList)
//                    .httpStatus(HttpStatus.OK).build();
//        }
//        return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.NOT_ACCEPTABLE).messageContent("登录信息有误").build();
//    }

    @Override
    public ResponseMessage error() {
        return null;
    }

//    @Override
//    public ResponseMessage error() {
//        //资源被拦截，重定向到login页面
//        return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.NOT_ACCEPTABLE).build();
//    }
}
