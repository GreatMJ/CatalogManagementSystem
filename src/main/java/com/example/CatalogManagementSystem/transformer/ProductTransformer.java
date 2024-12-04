package com.example.CatalogManagementSystem.transformer;

import com.example.CatalogManagementSystem.dto.request.ProductRequest;
import com.example.CatalogManagementSystem.dto.response.ProductResponse;
import com.example.CatalogManagementSystem.models.Product;

import java.time.LocalDate;

public class ProductTransformer {

    public static Product requestToEntity(ProductRequest productRequest){
         return Product.builder()
                 .brand(productRequest.getBrand())
                 .name(productRequest.getName())
                 .quantity(productRequest.getQuantity())
                 .price(productRequest.getPrice())
                 .category(productRequest.getCategory())
                 .dateAdded(LocalDate.now())
                       .build();

    }

    public static ProductResponse entityToResponse(Product product){
        return ProductResponse.builder()
                .brand(product.getBrand())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .dateAdded(product.getDateAdded())
                .build();
    }
}
