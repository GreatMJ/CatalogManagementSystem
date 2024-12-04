package com.example.CatalogManagementSystem.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponse {

    String name;
    String brand;
    String description;
    String category;
    float price;
    int quantity;
    LocalDate dateAdded;
}
