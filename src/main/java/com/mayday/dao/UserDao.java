package com.mayday.dao;

import com.mayday.mapper.UserMapper;
import com.mayday.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    //用户登录
    public UserModel login(UserModel userModel){
      return   userMapper.login(userModel);
    }

}
