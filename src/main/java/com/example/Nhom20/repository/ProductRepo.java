package com.example.Nhom20.repository;

import com.example.Nhom20.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    void deleteByCode(String productCode);
    Product findByCode(String productCode);
    Boolean existsByCode(String productCode);
}
