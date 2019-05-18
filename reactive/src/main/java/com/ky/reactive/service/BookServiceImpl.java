package com.ky.reactive.service;

import com.ky.reactive.model.Book;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;

/**
 * BookServiceImpl
 *
 * @Author: kevin yang
 * @Description:
 * @Date: Created at 19-3-14
 * @Last Modified:
 **/

@Service
public class BookServiceImpl {

    public Flux<ServerSentEvent<Book>> genBooks() {
        return Flux.interval(Duration.ofSeconds(1)).map(seq -> {
            Book book = new Book();
            book.setId(seq);
            book.setTitle("Book No." + seq);
            book.setAuthor("Author No." + seq);
            book.setPublishTime(new Date());
            return book;
        }).map(book -> ServerSentEvent.<Book>builder()
                .event("book")
                .id(String.valueOf(book.getId()))
                .data(book)
                .build());
    }

}
