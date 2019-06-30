package com.example.vltask19.service.impl;

import com.example.vltask19.entity.User;
import com.example.vltask19.entity.Role;
import com.example.vltask19.mapper.UserMapper;
import com.example.vltask19.model.VlUserDetails;
import com.example.vltask19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String defaultName = "role2";
        if (!username.equals(defaultName)) {
            throw new UsernameNotFoundException("样例仅支持role2");
        }
        VlUserDetails details = new VlUserDetails();
        User user = new User();
        user.setUsername("role2");
        user.setPassword(new BCryptPasswordEncoder(10).encode("456789"));
        List<Role> roles = new ArrayList<Role>();
        Role role1 = new Role();
        role1.setName("Admin");
        roles.add(role1);
        Role role2 = new Role();
        role2.setName("RoleManager");
        roles.add(role2);
        details.setUser(user);
        details.setRoles(roles);
        return details;
    }
}