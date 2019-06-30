package com.example.vltask19.service.impl;

import com.example.vltask19.entity.User;
import com.example.vltask19.mapper.UserMapper;
import com.example.vltask19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usersMapper;

    @Override
    public User getUser(Integer id) {
        return usersMapper.getUser(id);
    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        if (user == null || user.getUsername() == null || user.getUsername().equals("") || user.getPassword() == null || user.getPassword().equals("")) {
            return false;
        }
        usersMapper.saveUser(user);
        if (user.getUserId() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUserByUserNameAndPassword(User user) {
        return usersMapper.getUserByUserNameAndPassword(user);
    }

    @Override
    public boolean isExist(User user) {
        User getUser = usersMapper.getUserByUserNameAndPassword(user);
        if (getUser != null && getUser.getUserId() > 0) {
            return true;
        } else {
            return false;
        }
    }
}