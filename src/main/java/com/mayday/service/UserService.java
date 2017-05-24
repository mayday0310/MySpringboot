package com.mayday.service;

import com.mayday.dao.UserDao;
import com.mayday.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *用户服务层
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public UserModel login(UserModel userModel){
      return  userDao.login(userModel);
    }


}
