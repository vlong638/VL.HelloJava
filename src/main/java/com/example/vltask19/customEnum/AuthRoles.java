package com.example.vltask19.customEnum;

public enum AuthRoles {
    Admin("Admin"), UserManager("UserManager"), AuthorityManager("AuthorityManager"), RoleManager("RoleManager");
    public final String role;

    AuthRoles(String role) {
        this.role = role;
    }
}