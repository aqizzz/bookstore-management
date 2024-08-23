package com.example.bookstore.model;

import lombok.Data;

@Data
public class BookQuantity {
  private Book book;
  private int quantity;

  public BookQuantity() {
  }

  public BookQuantity(Book book, int quantity) {
    this.book = book;
    this.quantity = quantity;
  }
  
  public void incrementQuantity() {
    if (this.quantity < this.book.getInventory()) {
      this.quantity++;
    } 
  }

  public void decrementQuantity() {
    if (this.quantity > 0) {
        this.quantity--;
    }
  }
}