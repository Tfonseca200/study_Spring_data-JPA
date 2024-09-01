package com.bookStore.Jpa.controller;

import com.bookStore.Jpa.dtos.BookRecordDto;
import com.bookStore.Jpa.model.BookModel;
import com.bookStore.Jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/bookStore/book")
public class BookController {

    private final BookService bookService;

    public BookController( BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookModel> save (@RequestBody BookRecordDto bookRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks (){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete (@PathVariable UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("book deleted sucessfully");
    }
}

