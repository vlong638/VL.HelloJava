package com.example.vltask19.mapper;

import com.example.vltask19.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    /**
     * @param user
     */
    void saveRole(Role user);

    /**
     * @param id
     * @return
     */
    Role getById(Long id);

    /**
     * @param
     * @return
     */
    List<Role> getAllRoles();
}
