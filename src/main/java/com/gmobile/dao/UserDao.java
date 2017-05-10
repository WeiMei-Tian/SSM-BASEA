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
     int insert(@Param("user") User user);

     int insertNotExist(@Param("user") User user);

     User loginUser(@Param("user") User user);

     List<User> selectAllUsers();

     int deleteUser(@Param("id") int id);

     List<User> selectUsersByPage(@Param("pageStartNm") int pageStartNm,@Param("pageSize") int pageSize);
}
