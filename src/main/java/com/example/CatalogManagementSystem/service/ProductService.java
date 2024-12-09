package com.example.CatalogManagementSystem.service;

import com.example.CatalogManagementSystem.dto.request.ProductRequest;
import com.example.CatalogManagementSystem.dto.response.ProductResponse;
import com.example.CatalogManagementSystem.exceptions.ResourceNotFoundException;
import com.example.CatalogManagementSystem.models.Product;
import com.example.CatalogManagementSystem.repository.ProductRepository;
import com.example.CatalogManagementSystem.transformer.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Component

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
       List<ProductResponse>productResponseList=convertProductListToProductResponseList(productList);
       return productResponseList;
   }

   // get the product of specific brand and category
    public List<ProductResponse> getProductsUnderGivenBrandAndCategory(String brand,String category){
        List<Product> productList=productRepository.findByBrandAndCategory(brand,category);

        // if no menu items found
        if(productList==null || productList.isEmpty()) throw new ResourceNotFoundException(String.format("No products found under brand %s and category %s .",brand,category));

        // initialize product response list
        List<ProductResponse>productResponseList=convertProductListToProductResponseList(productList);
        return productResponseList;

    }

    // private method to convert productList to ProductResponseList
    private List<ProductResponse> convertProductListToProductResponseList(List<Product> productList){
        List<ProductResponse>productResponseList=new ArrayList<>(productList.size());

        for(Product product:productList){
            ProductResponse productResponse=ProductTransformer.entityToResponse(product);
            productResponseList.add(productResponse);
        }
        return productResponseList;
    }



// delete product
    public String deleteProductById(int id){
        Product product=productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Product with id %s not found.",id)));

        productRepository.delete(product);
        return String.format("%s has removed from system database.",product.getName());

    }

    public List<Product> getProductUnderGivenBrandAndCategoryAndHaveId(List<String> brandList,List<String > categoryList,List<Integer> idList){
        List<Product> productList=productRepository.getProductUnderGivenBrandAndCategoryAndHaveId(brandList,categoryList,idList);

        return productList;
    }
}
