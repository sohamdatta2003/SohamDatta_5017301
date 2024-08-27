package com.example.bookstoreapi.service;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.repository.BookRepository;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookstoreapi.exception.ResourceNotFoundException;
import java.util.Optional;

public interface BookService {
    Book createBook(Book book);
    void deleteBook(Long id);
    Optional<Book> getBookById(Long id);

}

