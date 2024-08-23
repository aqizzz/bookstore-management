package com.example.bookstore.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class ShoppingCart {
  private Map<String, BookQuantity> bookQuantities = new HashMap<>();

  public void addBook(Book book) {
    String isbn = book.getIsbn();
    if (bookQuantities.containsKey(isbn)) {
      bookQuantities.get(isbn).incrementQuantity();
    } else {
      bookQuantities.put(isbn, new BookQuantity(book, 1));
    }
  }

  public List<BookQuantity> getBookQuantities() {
    return new ArrayList<>(bookQuantities.values());
  }

  public void incrementQuantity(String isbn) {
    if (bookQuantities.containsKey(isbn)) {
      bookQuantities.get(isbn).incrementQuantity();
    }
  }

  public void decrementQuantity(String isbn) {
    if (bookQuantities.containsKey(isbn)) {
      bookQuantities.get(isbn).decrementQuantity();
    }
  }

  public double calculateTotalPrice() {
    BigDecimal totalPrice = bookQuantities.values().stream()
            .map(bookQuantity -> bookQuantity.getBook().getPrice().multiply(BigDecimal.valueOf(bookQuantity.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    DecimalFormat df = new DecimalFormat("#.##");
    return Double.parseDouble(df.format(totalPrice));
  }

  public void clear() {
    bookQuantities.clear();
  }
}