package com.example.Nhom20.service.impl;

import com.example.Nhom20.entity.Product;
import com.example.Nhom20.repository.ProductRepo;
import com.example.Nhom20.service.ProductService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteByCode(String productCode) {
        productRepo.deleteByCode(productCode);
    }

    @Override
    public Product findByCode(String productCode) {
        return productRepo.findByCode(productCode);
    }

    @Override
    public Integer validate(Product product) {

        if (product.getDescription().trim().isEmpty()) {
            return ERR_INVALID_DESCRIPTION;
        }
        if (product.getPrice() <= 0.0) {
            return ERR_INVALID_PRICE;
        }
        return VALID_INPUT;
    }
}
