package com.example.vltask19.controller;


import com.example.vltask19.customEnum.AuthRoles;
import com.example.vltask19.service.RoleService;
import com.example.vltask19.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role/saveRole")
    public String saveRole() {
        Role testRole =new Role("Test",1,true);
        roleService.saveRole(testRole);
        return "创建角色成功";
    }


    @PreAuthorize("hasRole('UserManager')")/*独立在Authorize前执行的方法*/
    @GetMapping("/role/saveRole2")
    public String saveRole2() {
        Role testRole =new Role("Test2",1,true);
        roleService.saveRole(testRole);
        return "创建角色成功";
    }

    @GetMapping("/role/getRoleById")
    public String getRoleById(long id){
        Role role = roleService.getById(id);
        return "成功获取角色";
    }

    @GetMapping("/role/getAllRoles")
    public String getAllRoles(){
        List<Role> roles = roleService.getAllRoles();
        return "成功获取角色";
    }
}
