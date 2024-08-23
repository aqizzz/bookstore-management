package com.example.bookstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "orders", schema = "book_store")
public class Orders {
  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

  @NotEmpty(message = "Username is required")
	@Column(name="username")
	private String username;

  @NotNull(message = "Total is required")
  @DecimalMin(value = "0.0", message = "Total must be greater than or equal to 0")
  @Column(name="total")
	private BigDecimal total;

  @NotNull(message = "Order time is required")
  @Column(name="order_time")
	private LocalDateTime orderTime;
}
