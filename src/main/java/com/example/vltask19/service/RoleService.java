package com.example.vltask19.service;

import com.example.vltask19.entity.Role;

public interface RoleService {
    /**
     *
     * @param role
     * @return
     */
    Integer saveRole(Role role);

    /**
     *
     * @param id
     * @return
     */
    Role getById(Long id);
}
