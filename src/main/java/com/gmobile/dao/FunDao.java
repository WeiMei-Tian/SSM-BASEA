package com.gmobile.dao;

import com.gmobile.domain.UserFun;
import com.gmobile.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liwei on 2017/5/5.
 */
public interface FunDao {

    public List<UserFun> selectUserFunByRoleId(@Param("roleId") int roleId);
}
