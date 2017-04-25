package com.gmobile.service.imp;

import com.gmobile.dao.UserDao;
import com.gmobile.domain.User;
import com.gmobile.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liwei on 2017/4/25.
 */
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    public int login(User user) {
        int statue = userDao.insert(user);
        Logger.getLogger(UserServiceImp.class).error(statue);
        return statue;
    }
}
