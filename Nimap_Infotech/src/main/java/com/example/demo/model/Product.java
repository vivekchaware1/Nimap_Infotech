package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String description;
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		
		public Product(Long id, String name, String description) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
		}
		
		
		public Product() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		@Override
		public String toString() {
			return "Product [id=" + id + ", name=" + name + ", description=" + description + "]";
		}
	    
		 @ManyToOne
		    @JoinColumn(name = "category_id")
		    private Category category;


		public Category getCategory1() {
			// TODO Auto-generated method stub
			return null;
		}
		public void setCategory(Object object) {
			// TODO Auto-generated method stub
			
		}
		public Object getCategory() {
			// TODO Auto-generated method stub
			return null;
		}

}
