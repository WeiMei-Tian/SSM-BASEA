package com.gmobile.domain;

import java.util.List;

/**
 * Created by liwei on 2017/5/4.
 */
public class UserRoleBO extends UserRole {

    private List<UserFun> funs;

    public List<UserFun> getFuns() {
        return funs;
    }

    public void setFuns(List<UserFun> funs) {
        this.funs = funs;
    }
}
