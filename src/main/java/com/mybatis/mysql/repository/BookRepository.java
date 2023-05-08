package com.mybatis.mysql.repository;

import com.mybatis.mysql.config.MySQLConnMapper;
import com.mybatis.mysql.entity.Book;

import java.util.List;

@MySQLConnMapper("MysqlBookRepository")
public interface BookRepository {
    List<Book> findAll();
}
