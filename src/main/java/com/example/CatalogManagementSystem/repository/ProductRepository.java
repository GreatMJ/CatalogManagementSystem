package com.example.CatalogManagementSystem.repository;

import com.example.CatalogManagementSystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select * from products where brand = :brand",nativeQuery = true)
    List<Product> findProductsByBrand(@Param("brand") String brand);

    // using jpql query
    @Query("SELECT p FROM Product p WHERE p.brand = :brand AND p.category = :category")
    List<Product> findByBrandAndCategory(@Param("brand") String brand, @Param("category") String category);

//    @Query(value = "select *,avg(price) as avgprice from products where price >= 500 AND date >= :from AND date <= :to ",nativeQuery = true)
//    List<Product> getProductsInDateRange(@Param("from")LocalDate from, @Param("to") LocalDate to);

    @Query(value = "Select p FROM Product  p WHERE p.brand IN :brandList AND p.category IN :categoryList AND p.id IN :idList")
    List<Product> getProductUnderGivenBrandAndCategoryAndHaveId(@Param("brandList")List<String> brandList,@Param("categoryList") List<String> categoryList, List<Integer> idList);

//    @Query("SELECT new com.example.dto.EmployeeDTO(e.id, e.name) FROM Employee e WHERE e.department = :department")
// //   List<EmployeeDTO> findEmployeesByDepartment(@Param("department") String department);

}

// get api
// product ids , category list, brandList