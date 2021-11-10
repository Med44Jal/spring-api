package com.spring.datajpa.repository;

import com.spring.datajpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContaining(String name);

    @Query("SELECT p FROM Product p WHERE p.subcategory = ?1")
    List<Product> findBySubcategoryContaining(Long id);
}
