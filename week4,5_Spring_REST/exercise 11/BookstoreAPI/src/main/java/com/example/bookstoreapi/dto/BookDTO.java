package com.example.bookstoreapi.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

public class BookDTO {
    private Long id;

    @NotNull
    @Size(min = 2, max = 100)
    private String title;

    @NotNull
    @Size(min = 2, max = 100)
    private String author;

    @NotNull
    @Min(value = 1)
    private Double price;

    @NotNull
    @Size(min = 10, max = 13)
    private String isbn;


}
