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
    public ResponseMessage findAllMessage() {
        List<ApplicationUser> list = userDao.findAll();
        if (list != null && list.size() > 0){
            return ResponseMessage.<ApplicationUser>builder().successStatus(true).responseData(list)
                    .httpStatus(HttpStatus.OK).build();
        }else {
            return ResponseMessage.builder().successStatus(false).httpStatus(HttpStatus.CONFLICT).messageContent("用户信息为空").build();
        }
    }

    @Override
    public ResponseMessage error() {
        return null;
    }

    @Override
    public ResponseMessage insertUserMessage(ApplicationUser user) {
        userDao.insertUserMessage(user);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("添加成功").build();
    }

    @Override
    public ResponseMessage deleteUserMessage(int id) {
        userDao.deleteUserMessage(id);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("删除成功").build();
    }

    @Override
    public ResponseMessage updateUserMessage(ApplicationUser user) {
        String password = user.getPassword();
        String role = user.getRole();
        String username = user.getUsername();
        System.out.println("password === "+user.getPassword());
        System.out.println("role === "+user.getRole());
        System.out.println("username === "+user.getUsername());
        userDao.updateUserMessage(password,role,username);
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("更新成功").build();
    }

    @Override
    public ResponseMessage findById(int id) {
        List<ApplicationUser> users = userDao.findById(id);
        ApplicationUser user = users.get(0);
        return  ResponseMessage.<ApplicationUser>builder().successStatus(true).responseUser(user)
                .httpStatus(HttpStatus.OK).build();
    }


}
