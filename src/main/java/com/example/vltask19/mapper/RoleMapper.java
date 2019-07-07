package com.example.vltask19.mapper;

import com.example.vltask19.entity.Role;
import org.springframework.stereotype.Repository;

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
}
