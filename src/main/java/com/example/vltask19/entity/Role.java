package com.example.vltask19.entity;


import java.io.Serializable;

/**
 * 类型
 */
public class Role implements Serializable {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getSys() {
        return isSys;
    }

    public void setSys(Boolean sys) {
        isSys = sys;
    }

    private Integer id;
    private String name;
    private Integer level;
    private Boolean isSys;

    public Role() {
    }

    public Role(String name, Integer level, Boolean isSys) {
        this.name = name;
        this.level = level;
        this.isSys = isSys;
    }
}
