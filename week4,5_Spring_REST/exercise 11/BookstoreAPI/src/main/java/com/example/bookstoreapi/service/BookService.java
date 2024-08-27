package com.example.bookstoreapi.service;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.repository.BookRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookstoreapi.exception.ResourceNotFoundException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    private final MeterRegistry meterRegistry;

    public BookService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void createBook(Book book) {

        meterRegistry.counter("bookstore.books.created").increment();
    }

}
