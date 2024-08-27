package com.example.bookstoreapi.model;
import org.springframework.hateoas.RepresentationModel;

import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation = "book", collectionRelation = "books")
public class BookModel extends RepresentationModel<BookModel> {

    private Long id;
    private String title;
    private String author;
    private double price;
    private String isbn;

    public BookModel(Long id, String title, String author, double price, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }
}
