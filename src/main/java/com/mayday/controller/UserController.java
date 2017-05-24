package com.mayday.controller;

import com.mayday.model.UserModel;
import com.mayday.service.UserService;
import com.mayday.utils.JsonUtils;
import com.mayday.utils.Mayday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by EricAi on 2017/5/23.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String getLogin(){
        return "login";
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(HttpServletRequest request, String userName, String password, HttpServletResponse response)throws Exception{

        UserModel userModel=new UserModel();
        userModel.setUserName(userName);
        userModel.setPassword(password);

        UserModel u=userService.login(userModel);
        if(u!=null){
            if(u.getStatus()==1){  //如果状态为1
                //将登录信息保存到session里
                request.getSession().setAttribute(Mayday.SESSION_LOGIN,u);
                JsonUtils.renderData(response,"success");
               return "login";
            }else{
               JsonUtils.renderData(response,"用户被锁定");
                return "login";
            }
        }else{
            JsonUtils.renderData(response,"用户名或者密码错误");
            return "login";
        }

   }
}
