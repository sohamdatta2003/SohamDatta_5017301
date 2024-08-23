package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bookstoreapi.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }




    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setPrice(bookDetails.getPrice());
                    book.setIsbn(bookDetails.getIsbn());
                    Book updatedBook = bookRepository.save(book);
                    return ResponseEntity.ok().body(updatedBook);
                }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
        return ResponseEntity.ok(book);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {

        List<Book> books;

        if (title != null && author != null) {
            books = bookRepository.findByTitleAndAuthor(title, author);
        } else if (title != null) {
            books = bookRepository.findByTitle(title);
        } else if (author != null) {
            books = bookRepository.findByAuthor(author);
        } else {
            books = bookRepository.findAll();
        }

        return ResponseEntity.ok(books);
    }
}
