package com.ky.reactive.controller;

import com.ky.reactive.model.Book;
import com.ky.reactive.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * DemoController
 *
 * @Author: kevin yang
 * @Description:
 * @Date: Created at 19-3-14
 * @Last Modified:
 **/

@RequestMapping("/demo")
@RestController
public class DemoController {

    @Autowired
    BookServiceImpl bookService;

    @GetMapping("/books")
    Flux<ServerSentEvent<Book>> getBooks() {
        return bookService.genBooks();
    }

}
