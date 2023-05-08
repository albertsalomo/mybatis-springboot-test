package com.mybatis.mysql.repository;

import com.mybatis.mysql.config.MySQLConnMapper;
import com.mybatis.mysql.dto.GetUserAndBookDetails;
import com.mybatis.mysql.dto.GetUserByEmailId;
import com.mybatis.mysql.dto.GetUsersDetails;
import com.mybatis.mysql.entity.User;

import java.util.List;

@MySQLConnMapper("MysqlUserRepository")
public interface UserRepository {

    // Get Repository
    List<GetUsersDetails> getUsersDetails();
    GetUsersDetails getUserDetailsById(Integer id);
    GetUserAndBookDetails getUserAndBookDetailsById(Integer id);
    GetUserByEmailId getTotalUserByEmailId(String emailId);
    Integer deleteUserById(Integer id);
    Integer insertUserData(User user);
    Integer updateUserData(User user);
}
