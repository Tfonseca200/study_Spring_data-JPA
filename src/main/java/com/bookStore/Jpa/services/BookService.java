package com.bookStore.Jpa.services;

import com.bookStore.Jpa.dtos.BookRecordDto;
import com.bookStore.Jpa.model.BookModel;
import com.bookStore.Jpa.model.ReviewModel;
import com.bookStore.Jpa.repositories.AuthorRepository;
import com.bookStore.Jpa.repositories.BookRepository;
import com.bookStore.Jpa.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository , AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;

    }

    @Transactional // o @Transactional é usado quando tem muita transações no banco dados pra que ser der algo errado, irá ser realizado um rowback pra retorna todas as trasações
    public BookModel saveBook(BookRecordDto bookRecordDto) {
        BookModel book = new BookModel();
        book.setTitle(bookRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookRecordDto.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReview(reviewModel);

        return bookRepository.save(book);
    }

    public List<BookModel> getAllBooks (){
        return bookRepository.findAll();
    }

    @Transactional
    public void deleteBook (UUID id){
        bookRepository.deleteById(id);
    }
}
