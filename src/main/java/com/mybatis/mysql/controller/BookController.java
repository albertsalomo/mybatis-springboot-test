package com.mybatis.mysql.controller;


import com.mybatis.mysql.entity.Book;
import com.mybatis.mysql.repository.BookRepository;
import com.mybatis.mysql.response.APIResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    // GET All Books
    @GetMapping("/list")
    public APIResponseModel<?> getAllBooks() {
        APIResponseModel<List<Book>> response = new APIResponseModel<>();
        List<Book> books = bookRepository.findAll();
        if (books != null) {
            response.SetJSONResponseWithData(books, "books found !", HttpStatus.OK);
        } else {
            response.SetJSONResponse("server error !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
