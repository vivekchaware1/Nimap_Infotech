package com.example.demo.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.model.Category;

@RestController
@RequestMapping
public class Category_controller {
	
	private CategoryRepository categoryRepository;
	
	@Autowired
	public Category_controller (CategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}

	@GetMapping
    public List<Category> getAllCategories(@RequestParam(defaultValue = "0") int page) {
        int pageSize = 10; // Adjust the page size according to your needs
        PageRequest pageable = PageRequest.of(page, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.getContent();
	
	}
	
	 @PostMapping
	    public Category createCategory(@RequestBody Category category) {
	        return categoryRepository.save(category);
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
	        java.util.Optional<Category> category = categoryRepository.findById(id);
	        return category.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category updatedCategory) {
	        java.util.Optional<Category> category = categoryRepository.findById(id);
	        if (category.isPresent()) {
	            Category existingCategory = category.get();
	            existingCategory.setName(updatedCategory.getName());
	            existingCategory.setDescription(updatedCategory.getDescription());
	            categoryRepository.save(existingCategory);
	            return ResponseEntity.ok(existingCategory);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
	        categoryRepository.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }
}
