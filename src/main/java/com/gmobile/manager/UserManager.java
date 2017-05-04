package com.gmobile.manager;

import com.gmobile.dao.RoleDao;
import com.gmobile.domain.User;
import com.gmobile.domain.UserBo;
import com.gmobile.domain.UserRole;
import com.gmobile.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by liwei on 2017/5/4.
 */
public class UserManager {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    RoleDao roleDao;

    public User login(User user) throws Exception{
        user = userService.login(user);
        List<UserRole> roles = roleDao.selectRolesByUserId(user);
        UserBo userBo = new UserBo();
        logger.info("******************************" + roles.size());
        return user;
    }

}
