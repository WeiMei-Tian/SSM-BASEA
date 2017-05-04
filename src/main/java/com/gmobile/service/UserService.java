package com.gmobile.service;

import com.gmobile.domain.User;

/**
 * Created by liwei on 2017/4/25.
 */
public interface UserService {

    int addUser(User user) throws Exception;

    User login(User user) throws Exception;
}
