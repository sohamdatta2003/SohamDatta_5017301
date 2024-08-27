package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bookstoreapi.exception.ResourceNotFoundException;
import org.springframework.hateoas.CollectionModel;


import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookstoreAPI");

        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.OK)  // Custom status for successful retrieval
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));


        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookDetails");

        return new ResponseEntity<>(book, headers, HttpStatus.OK);
    }



    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);

        // Create custom headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookCreation");

        return new ResponseEntity<>(savedBook, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setPrice(bookDetails.getPrice());
        book.setIsbn(bookDetails.getIsbn());

        Book updatedBook = bookRepository.save(book);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookUpdate");

        return new ResponseEntity<>(updatedBook, headers, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);

        // Create custom headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "BookDeletion");

        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);

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
