package com.example.vltask19.controller;

import com.example.vltask19.entity.User;
import com.example.vltask19.redis.RedisUtil;
import com.example.vltask19.service.UserService;
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
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public String userLogin(String userName, String userPassword) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(userPassword);
        user.setLoginTime(new Date());
        String sessionId = String.valueOf(user.hashCode());
        if (redisUtils.getString(sessionId) != null) {
            return "登录失败,用户已登录";
        }

        User dbUser = userService.getUserByUserNameAndPassword(user);
        if (dbUser == null)
        {
            userService.saveUser(user);
        }
        redisUtils.setString(sessionId, sessionId,3000);
        return "登录成功";
    }
}
