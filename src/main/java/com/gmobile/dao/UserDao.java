package com.gmobile.dao;

import com.gmobile.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by liwei on 2017/4/25.
 */
public interface UserDao {
    public int insert(@Param("user") User user);

    public int insertNotExist(@Param("user") User user);
}
