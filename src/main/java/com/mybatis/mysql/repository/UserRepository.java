package com.mybatis.mysql.repository;

import com.mybatis.mysql.model.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findById(Integer id);

    int deleteById(Integer id);

    int insert(User user);

    int update(User user);
}
