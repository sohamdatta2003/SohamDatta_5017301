package com.example.bookstoreapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.exception.ResourceNotFoundException;
import com.example.bookstoreapi.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.bookstoreapi.repository.BookRepository;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetBookById_Success() {
        // Given
        Book mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("Spring in Action");
        mockBook.setAuthor("Craig Walls");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));

        // When
        Book book = bookService.getBookById(1L).orElse(null);

        // Then
        assertNotNull(book);
        assertEquals("Spring in Action", book.getTitle());
        assertEquals("Craig Walls", book.getAuthor());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBookById_NotFound() {
        // Given
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById(1L));
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateBook() {
        // Given
        Book mockBook = new Book();
        mockBook.setTitle("Spring in Action");
        mockBook.setAuthor("Craig Walls");

        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        // When
        Book createdBook = bookService.createBook(mockBook);

        // Then
        assertNotNull(createdBook);
        assertEquals("Spring in Action", createdBook.getTitle());
        verify(bookRepository, times(1)).save(mockBook);
    }

    @Test
    void testDeleteBook_Success() {
        // Given
        Book mockBook = new Book();
        mockBook.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));
        doNothing().when(bookRepository).deleteById(1L);

        // When
        bookService.deleteBook(1L);

        // Then
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

}
