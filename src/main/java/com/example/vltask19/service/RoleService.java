package com.example.vltask19.service;

import com.example.vltask19.entity.Role;

import java.util.List;

public interface RoleService {
    /**
     * @param role
     * @return
     */
    Integer saveRole(Role role);

    /**
     * @param id
     * @return
     */
    Role getById(Long id);

    /**
     * @return
     */
    List<Role> getAllRoles();
}
