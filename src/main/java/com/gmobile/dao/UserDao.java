package com.gmobile.dao;

import com.gmobile.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liwei on 2017/4/25.
 */
public interface UserDao {
    public int insert(@Param("user") User user) throws Exception;

    public int insertNotExist(@Param("user") User user) throws Exception;

    public User loginUser(@Param("user") User user) throws Exception;

    public List<User> selectAllUsers() throws Exception;

    public int deleteUser(@Param("id") int id) throws Exception;
}
