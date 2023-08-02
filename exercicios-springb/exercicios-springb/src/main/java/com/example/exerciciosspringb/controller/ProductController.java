package com.example.exerciciosspringb.controller;

import com.example.exerciciosspringb.domain.dto.ProductDto;
import com.example.exerciciosspringb.domain.model.Product;
import com.example.exerciciosspringb.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getOneById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long id,
                                                 @RequestBody @Valid ProductDto productDto) {
        Optional<Product> productOptional = Optional.ofNullable(productService.getOneById(id));
        Product product = productOptional.get();
        BeanUtils.copyProperties(productDto, product);
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") Long id) {
        Optional<Product> productOptional = Optional.ofNullable(productService.getOneById(id));
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productOptional.get()));
    }

}
