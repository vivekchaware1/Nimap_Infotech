package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductServices;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Lazy
public class ProductController {
    private final ProductServices productServices;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductServices productService, CategoryService categoryService) {
        this.productServices = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
    	int pageSize = 10; // Adjust the page size according to your needs
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<Category> categoryPage = categoryService.findAll(pageable);
        return productServices.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productServices.createProduct(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Optional<Product> product = productServices.getProductById(id);
        if (product.isPresent()) {
            Product responseProduct = product.get();
            responseProduct.setCategory(categoryService.getCategoryById(((Category) responseProduct.getCategory()).getId()).orElse(null));
            return ResponseEntity.ok(responseProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product updatedProduct) {
        Optional<Product> product = productServices.getProductById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());

            // Update the category if it has changed
            if (!existingProduct.getCategory().equals(updatedProduct.getCategory())) {
                existingProduct.setCategory(updatedProduct.getCategory());
            }

            Product updated = productServices.updateProduct(id, existingProduct);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
