package com.example.CatalogManagementSystem.service;

import com.example.CatalogManagementSystem.dto.request.ProductRequest;
import com.example.CatalogManagementSystem.dto.response.ProductResponse;
import com.example.CatalogManagementSystem.exceptions.ResourceNotFoundException;
import com.example.CatalogManagementSystem.models.Product;
import com.example.CatalogManagementSystem.repository.ProductRepository;
import com.example.CatalogManagementSystem.transformer.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // method to add product
    public String add(ProductRequest productRequest){
        Product product= ProductTransformer.requestToEntity(productRequest);
        productRepository.save(product);
        return "Product added successfully.";
    }

    // method to get the product
    public ProductResponse getById(int id){
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("No item found for given id : %d.",id)));
        ProductResponse productResponse=ProductTransformer.entityToResponse(product);
        return productResponse;
    }
}
