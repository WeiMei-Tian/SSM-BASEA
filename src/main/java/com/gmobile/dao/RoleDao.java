package com.gmobile.dao;

import com.gmobile.domain.User;
import com.gmobile.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liwei on 2017/5/4.
 */
public interface RoleDao {

    public List<UserRole> selectRolesByUserId(@Param("user")User user);

    public List<UserRole> selectAllRoles();

}
