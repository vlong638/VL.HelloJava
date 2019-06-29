package com.example.vltask19.mapper;

import com.example.vltask19.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User getUser(Integer id);

    void saveUser(User user);

    User getUserByUserNameAndPassword(User user);
}
