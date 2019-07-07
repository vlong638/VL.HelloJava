package com.example.vltask19.service.impl;

import com.example.vltask19.entity.Role;
import com.example.vltask19.mapper.RoleMapper;
import com.example.vltask19.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Transactional
    public Integer saveRole(Role role) {
        if (role == null || role.getName() == null || role.getName().equals("")) {
            return -1;
        }
        roleMapper.saveRole(role);
        return role.getId();
    }

    @Override
    public Role getById(Long id) {
        return roleMapper.getById(id);
    }
}