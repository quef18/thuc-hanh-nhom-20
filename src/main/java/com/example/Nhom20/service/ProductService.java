package com.example.Nhom20.service;

import com.example.Nhom20.entity.Product;

import java.util.Arrays;
import java.util.List;

public interface ProductService {
    int VALID_INPUT = 0;
    int ERR_CODE_EXISTED = 1;
    int ERR_INVALID_DESCRIPTION = 2;
    int ERR_INVALID_PRICE = 3;

    static String getErrorMessage(Integer resultCode) {
        List<String> errorMessages = Arrays.asList(
                "",
                "Mã đã tồn tại",
                "Mô tả không hợp lệ",
                "Giá không hợp lệ"
        );
        return errorMessages.get(resultCode);
    }

    Product save(Product product);

    List<Product> findAll();

    void deleteByCode(String productCode);

    Product findByCode(String productCode);

    Integer validate(Product product);
}
