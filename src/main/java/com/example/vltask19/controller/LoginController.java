package com.example.vltask19.controller;

import com.example.vltask19.model.UseLoginInfo;
import com.example.vltask19.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class LoginController {
    @Autowired
    private RedisUtil redisUtils;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public String UserLogin(String userName, String userPassword) {
        UseLoginInfo user = new UseLoginInfo();
        user.setName(userName);
        user.setPassword(userPassword);
        user.setLoginTime(new Date());
        String sessionId = String.valueOf(user.hashCode());
        if (redisUtils.getString(sessionId) != null) {
            return "登录失败,用户已登录";
        }
        redisUtils.setString(sessionId, sessionId,3000);
        return "登录成功";

//        userService.saveUser(user);
    }
}
