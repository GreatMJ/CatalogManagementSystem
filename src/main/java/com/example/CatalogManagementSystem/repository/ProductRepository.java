package com.example.CatalogManagementSystem.repository;

import com.example.CatalogManagementSystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select * from products where products_brand = :brand",nativeQuery = true)
    List<Product> findProductsByBrand(@Param("brand") String brand);
}
