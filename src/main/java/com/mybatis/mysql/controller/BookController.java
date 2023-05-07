package com.mybatis.mysql.controller;


import com.mybatis.mysql.model.Book;
import com.mybatis.mysql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    // get all books
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }
}
