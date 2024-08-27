package com.example.bookstoreapi;

import com.example.bookstoreapi.entity.Book;
import com.example.bookstoreapi.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    public void testCreateBook() throws Exception {
        String bookJson = "{\"title\":\"Spring Boot\", \"author\":\"John Doe\", \"price\":29.99, \"isbn\":\"1234567890\"}";

        mockMvc.perform(post("/books")
                        .contentType("application/json")
                        .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Spring Boot"))
                .andExpect(jsonPath("$.author").value("John Doe"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book(1L, "Spring Boot", "John Doe", 29.99, "1234567890");
        bookRepository.save(book);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring Boot"))
                .andExpect(jsonPath("$.author").value("John Doe"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book(1L, "Spring Boot", "John Doe", 29.99, "1234567890");
        bookRepository.save(book);

        String updatedBookJson = "{\"title\":\"Spring Boot Updated\", \"author\":\"John Doe\", \"price\":39.99, \"isbn\":\"1234567890\"}";

        mockMvc.perform(put("/books/1")
                        .contentType("application/json")
                        .content(updatedBookJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring Boot Updated"))
                .andExpect(jsonPath("$.price").value(39.99));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = new Book(1L, "Spring Boot", "John Doe", 29.99, "1234567890");
        bookRepository.save(book);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }
}
