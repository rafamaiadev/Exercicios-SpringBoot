package com.example.exerciciosspringb.service;

import com.example.exerciciosspringb.domain.model.Product;
import com.example.exerciciosspringb.exception.ServiceException;
import com.example.exerciciosspringb.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getOneById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ServiceException("Product not found"));
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public String deleteProduct(Product product) {
        productRepository.delete(product);
        return "Product deleted successfully.";
    }
}
