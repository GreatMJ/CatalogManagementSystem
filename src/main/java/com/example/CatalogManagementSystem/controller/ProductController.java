package com.example.CatalogManagementSystem.controller;

import com.example.CatalogManagementSystem.dto.request.ProductRequest;
import com.example.CatalogManagementSystem.dto.response.ProductResponse;
import com.example.CatalogManagementSystem.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private  final ProductService productService;

    // add
    @PostMapping
    public ResponseEntity<String> add(@Valid @RequestBody ProductRequest productRequest){
        String res= productService.add(productRequest);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public  ResponseEntity<ProductResponse> getById( @RequestParam int id){
        ProductResponse productResponse=productService.getById(id);
        return ResponseEntity.ok(productResponse);
    }
}
