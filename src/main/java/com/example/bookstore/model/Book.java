package com.example.bookstore.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "book", schema = "book_store")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
  @NotEmpty(message = "Title is required")
	@Column(name="title")
	private String title;
	
  @NotEmpty(message = "Author is required")
	@Column(name="author")
	private String author;
	
  @NotEmpty(message = "Isbn is required")
	@Column(name="isbn")
	private String isbn;

  @NotNull(message = "Price is required")
  @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0")
  @Column(name="price")
	private BigDecimal price;

  @Column(name="description")
	private String description;

  @NotNull(message = "Inventory is required")
  @Min(value = 0, message = "Inventory must be greater than or equal to 0")
  @Column(name="inventory")
	private int inventory;
}
