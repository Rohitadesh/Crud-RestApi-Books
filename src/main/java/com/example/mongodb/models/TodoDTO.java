package com.example.mongodb.models;

import java.time.LocalDate;

// Java record used as DTO for immutability and auto-generated methods
public record TodoDTO(
    String id,
    String title,
    String description,
    String status,
    LocalDate createDate
) {}
