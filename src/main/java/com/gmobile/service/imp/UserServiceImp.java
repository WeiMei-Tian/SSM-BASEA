package com.gmobile.service.imp;

import com.gmobile.dao.RoleDao;
import com.gmobile.dao.UserDao;
import com.gmobile.domain.User;
import com.gmobile.domain.UserRole;
import com.gmobile.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liwei on 2017/4/25.
 */
@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    private Logger logger = Logger.getLogger(UserServiceImp.class);

    public int addUser(User user){
        return userDao.insert(user);
    }

    public User login(User user){
        return userDao.loginUser(user);
    }

    public List<User> selectAllUsers() {
        return userDao.selectAllUsers();
    }

    public List<User> selectUsersByPage(int pageNumber, int pageSize) throws Exception {
        return userDao.selectUsersByPage((pageNumber+1)*pageSize,pageSize);
    }

    public List<UserRole> selectAllRoles(){
        return roleDao.selectAllRoles();
    }

    public int deleteUser(int id){
        return userDao.deleteUser(id);
    }

}
