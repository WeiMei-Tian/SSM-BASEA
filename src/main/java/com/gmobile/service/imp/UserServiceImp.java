package com.gmobile.service.imp;

import com.alibaba.druid.pool.ExceptionSorter;
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

    private Logger logger = Logger.getLogger(UserServiceImp.class);

    public int login(User user) {
        try{
            int statue = userDao.insert(user);
            logger.error(statue);
            return statue;
        }catch (Exception e){
            logger.error("==========插入异常=====");
            logger.error(e.getMessage());
            return 0;
        }
    }
}
