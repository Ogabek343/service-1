package com.product.service.controller;

import com.product.service.dto.ApiResponseWithList;
import com.product.service.dto.ApiResponseWithObject;
import com.product.service.dto.productdetails.ProductDetailsDto;
import com.product.service.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/{id}")
    public ApiResponseWithObject findProductById(@PathVariable("id") Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/all")
    public ApiResponseWithList getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/listProduct")
    public ApiResponseWithList getAllListProducts() {
        return productService.getListOfProducts();
    }

    @PostMapping("/creation")
    public ApiResponseWithObject createProduct(@ModelAttribute ProductDetailsDto productDetailsDto, @RequestParam("file") MultipartFile[] files) {
        return productService.createProduct(productDetailsDto,files);
    }

    @PutMapping("/edition")
    public ApiResponseWithObject updateProduct(@RequestBody ProductDetailsDto productDetailsDto) {
        return productService.updateProduct(productDetailsDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponseWithObject deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProduct(productId);
    }
}
