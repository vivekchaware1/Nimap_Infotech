package com.example.demo.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.model.Category;

@Service
@Lazy
public class CategoryService {
	 private CategoryRepository categoryRepository;
	 
	 @Autowired
	 public CategoryService (CategoryService categoryService) {
		this.categoryRepository=categoryRepository;
	}

	 public List<Category> getAllCategories() {
	        return categoryRepository.findAll();
	    }

	    public Category createCategory(Category category) {
	        return categoryRepository.save(category);
	    }

	    public java.util.Optional<Category> getCategoryById(Long id) {
	        return categoryRepository.findById(id);
	    }

	    public Category updateCategory(Long id, Category updatedCategory) {
	        java.util.Optional<Category> category = categoryRepository.findById(id);
	        if (category.isPresent()) {
	            Category existingCategory = category.get();
	            existingCategory.setName(updatedCategory.getName());
	            existingCategory.setDescription(updatedCategory.getDescription());
	            return categoryRepository.save(existingCategory);
	        } else {
	            throw new RuntimeException("Category not found with id: " + id);
	        }
	    }

	    public void deleteCategory(Long id) {
	        categoryRepository.deleteById(id);
	    }

		public Page<Category> findAll1(PageRequest pageable) {
			// TODO Auto-generated method stub
			return null;
		}

		public Page<Category> findAll(PageRequest pageable) {
			// TODO Auto-generated method stub
			return null;
		}
}
