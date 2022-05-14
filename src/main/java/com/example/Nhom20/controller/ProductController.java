package com.example.Nhom20.controller;

import com.example.Nhom20.entity.Product;
import com.example.Nhom20.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/viewProducts")
    public String displayAllProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/deleteProduct")
    public String getDeletePage(@RequestParam("productCode") String productCode, Model model) {
        Product product = productService.findByCode(productCode);
        model.addAttribute("product", product);
        return "deleteProduct";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productCode") String productCode) {
        productService.deleteByCode(productCode);
        return "redirect:/viewProducts";
    }

    @GetMapping("/updateProduct")
    public String getUpdatePage(@RequestParam("productCode") Optional<String> productCode, Model model) {
        String errorMessage = "";
        Product product = new Product();
        if (productCode.isPresent()) {
            product = productService.findByCode(productCode.get());
            if (product == null) {
                product = new Product();
                errorMessage = "Không có mặt hàng nào có mã: " + productCode.get();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("product") Product product, Model model) {
        Integer resultCode = productService.validate(product);
        if (resultCode == ProductService.VALID_INPUT) {
            productService.save(product);
            return "redirect:/viewProducts";
        } else {
            model.addAttribute("errorMessage", ProductService.getErrorMessage(resultCode));
            model.addAttribute("product", product);
            return "updateProduct";
        }
    }
}
