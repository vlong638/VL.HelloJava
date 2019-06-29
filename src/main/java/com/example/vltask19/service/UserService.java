package com.example.vltask19.service;

import com.example.vltask19.entity.User;

public interface UserService {

    User getUser(Integer id);

    boolean saveUser(User user);

    User getUserByUserNameAndPassword(User user);

    boolean isExist(User user);
}
