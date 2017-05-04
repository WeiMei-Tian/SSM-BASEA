package com.gmobile.domain;

import java.util.List;

/**
 * Created by liwei on 2017/5/4.
 */
public class UserBo extends User {

    private List<UserRoleBO> roles;

    public List<UserRoleBO> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleBO> roles) {
        this.roles = roles;
    }
}
