package com.gmobile.service;

import com.gmobile.domain.User;
import com.gmobile.domain.UserRole;

import java.util.List;

/**
 * Created by liwei on 2017/4/25.
 */
public interface UserService {

    int addUser(User user) throws Exception;

    User login(User user) throws Exception;

    List<User> selectAllUsers() throws Exception;

    List<User> selectUsersByPage(int pageNumber,int pageSize) throws Exception;

    List<UserRole> selectAllRoles() throws Exception;

    int deleteUser(int id) throws Exception;
}
