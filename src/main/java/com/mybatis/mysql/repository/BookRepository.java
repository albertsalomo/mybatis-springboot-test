package com.mybatis.mysql.repository;

import com.mybatis.mysql.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAllBooks();
}
