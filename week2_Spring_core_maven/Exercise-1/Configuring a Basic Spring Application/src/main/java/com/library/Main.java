package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Load Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the bookService bean
        BookService bookService = (BookService) context.getBean("bookService");

        // Call the printBookDetails method
        bookService.printBookDetails();
    }
}
