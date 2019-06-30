package com.example.vltask19.service;

import com.example.vltask19.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUser(Integer id);

    boolean saveUser(User user);

    User getUserByUserNameAndPassword(User user);

    boolean isExist(User user);
}
