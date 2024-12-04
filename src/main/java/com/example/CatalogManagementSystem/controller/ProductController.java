package com.example.CatalogManagementSystem.controller;

import com.example.CatalogManagementSystem.dto.request.ProductRequest;
import com.example.CatalogManagementSystem.dto.response.ProductResponse;
import com.example.CatalogManagementSystem.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private  final ProductService productService;

    public ProductController (ProductService productService){
        this.productService=productService;
    }

    // add
    @PostMapping
    public ResponseEntity<String> add(@Valid @RequestBody ProductRequest productRequest){
        String res= productService.add(productRequest);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public  ResponseEntity<ProductResponse> getById( @RequestParam @Min(value = 1, message = "Product ID must be greater than 0") int id){
        ProductResponse productResponse=productService.getById(id);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/brand")
    public ResponseEntity<List<ProductResponse>> getProductsByBrand(@RequestParam  @NotBlank(message = "Brand must not be empty") String brand){
        List<ProductResponse> res=productService.getProductsByBrand(brand);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/brand/{brand}/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsUnderGivenBrandAndCategory(@PathVariable @NotBlank(message = "Brand must not be empty") String brand,@PathVariable @NotBlank(message = "Category must not be empty") String category){
        List<ProductResponse> res=productService.getProductsUnderGivenBrandAndCategory(brand,category);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProductById( @RequestParam  @Min(value = 1, message = "Product ID must be greater than 0")int id){

        String res=productService.deleteProductById(id);
        return ResponseEntity.ok(res);

    }
}
