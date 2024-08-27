package com.example.bookstoreapi.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CustomerDTO {
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Email
    private String email;


}
