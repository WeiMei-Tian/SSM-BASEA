package com.gmobile.manager;

import com.gmobile.dao.FunDao;
import com.gmobile.dao.RoleDao;
import com.gmobile.domain.*;
import com.gmobile.service.UserService;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
    @Autowired
    FunDao funDao;

    public UserBo login(User user) throws Exception{
        user = userService.login(user);
        if(user != null){
            List<UserRole> roles = roleDao.selectRolesByUserId(user);//查找出用户所拥有的所有角色
            List<UserRoleBO> roleBOs = new ArrayList<UserRoleBO>();
            if(roles != null && roles.size() > 0){
                for(UserRole role : roles){
                    //根据角色查询所有权限
                    List<UserFun> funcs = funDao.selectUserFunByRoleId(role.getId());
                    logger.info("***************方法集合*****" + funcs.size());
                    UserRoleBO roleBO = new UserRoleBO();
                    roleBO.setId(role.getId());
                    roleBO.setRoleName(role.getRoleName());
                    roleBO.setDescription(role.getDescription());
                    roleBO.setFuns(funcs); //角色对应的所有权限

                    roleBOs.add(roleBO);
                }
            }
            UserBo userBo = new UserBo();
            userBo.setRoles(roleBOs);
            userBo.setUsername(user.getUsername());
            userBo.setPassword(user.getPassword());
            userBo.setCreateTime(user.getCreateTime());
            userBo.setMobile(user.getMobile());
            logger.info("*********************角色集合*********" + roles.size());
            return userBo;
        }else {
            return null;
        }

    }

}
