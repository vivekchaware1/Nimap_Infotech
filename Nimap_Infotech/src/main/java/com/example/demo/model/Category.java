package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class  Category {
	
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
		
		
		public Category(Long id, String name, String description) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
		}
		
		
		public Category() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		@Override
		public String toString() {
			return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
		}
	    
	    

}
