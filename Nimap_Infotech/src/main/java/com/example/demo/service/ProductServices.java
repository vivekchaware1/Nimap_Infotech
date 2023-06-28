package com.example.demo.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductRepository;
import com.example.demo.model.Product;

@Service
@Lazy
public class ProductServices {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductServices (ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	 public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    public Product createProduct(Product product) {
	        return productRepository.save(product);
	    }

	    public java.util.Optional<Product> getProductById(Long id) {
	        return productRepository.findById(id);
	    }

	    public Product updateProduct(Long id, Product updatedProduct) {
	        java.util.Optional<Product> product = productRepository.findById(id);
	        if (product.isPresent()) {
	            Product existingProduct = product.get();
	            existingProduct.setName(updatedProduct.getName());
	            existingProduct.setDescription(updatedProduct.getDescription());
	            return productRepository.save(existingProduct);
	        } else {
	            throw new RuntimeException("Product not found with id: " + id);
	        }
	    }

	    public void deleteProduct(Long id) {
	        productRepository.deleteById(id);
	    }
}
