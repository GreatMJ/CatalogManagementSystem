package com.example.CatalogManagementSystem.service;

import com.example.CatalogManagementSystem.dto.request.ProductRequest;
import com.example.CatalogManagementSystem.dto.response.ProductResponse;
import com.example.CatalogManagementSystem.exceptions.ResourceNotFoundException;
import com.example.CatalogManagementSystem.models.Product;
import com.example.CatalogManagementSystem.repository.ProductRepository;
import com.example.CatalogManagementSystem.transformer.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

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

    // method to products by brand
   public List<ProductResponse> getProductsByBrand(String brand){

       // trim the category
       String trimmedBrand=brand.trim();
       if(trimmedBrand.isEmpty()) throw new IllegalArgumentException("Category must not be empty.");


       List<Product> productList=productRepository.findProductsByBrand(trimmedBrand);
       // if no menu items found
        if(productList==null || productList.isEmpty()) throw new ResourceNotFoundException(String.format("No products found for brand %s .",trimmedBrand));

        // initialize product response list
       List<ProductResponse>productResponseList=new ArrayList<>(productList.size());

       for(Product product:productList){
           ProductResponse productResponse=ProductTransformer.entityToResponse(product);
           productResponseList.add(productResponse);
       }

       return productResponseList;
   }
}
