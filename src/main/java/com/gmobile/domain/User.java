package com.gmobile.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by liwei on 2017/4/25.
 */
public class User {

    private String name;
    private String password;

    @NotEmpty(message="用户名不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message="密码不能为空")
    @Size(min = 6, max = 12,message = "密码长度必须在6-12位之间")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
