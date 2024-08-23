package com.example.bookstore.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookQuantity;
import com.example.bookstore.model.ShoppingCart;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;

@Controller
@RequestMapping("/member")
public class ShoppingCartController {

  @Autowired
  private ShoppingCart shoppingCart;
  @Autowired
  private BookService bookService;
  @Autowired
  private OrderService orderService;

  @GetMapping("/addToCart")
  public String addToCart(@RequestParam("isbn") String isbn) {
    Book book = bookService.findBookByIsbn(isbn);
    if (book != null) {
      shoppingCart.addBook(book);
    }
    return "redirect:/";
  }

  @GetMapping("/checkout")
  public String checkout(Model model) {
    List<BookQuantity> bookQuantities = shoppingCart.getBookQuantities();
    double totalPrice = shoppingCart.calculateTotalPrice();
    model.addAttribute("bookQuantities", bookQuantities);
    model.addAttribute("totalPrice", totalPrice);
    return "checkout";
  }

  @GetMapping("/incrementQuantity")
  public String incrementQuantity(Model model, @RequestParam("isbn") String isbn) {
    List<BookQuantity> bookQuantities = shoppingCart.getBookQuantities();
    shoppingCart.incrementQuantity(isbn);
    double totalPrice = shoppingCart.calculateTotalPrice();
    model.addAttribute("bookQuantities", bookQuantities);
    model.addAttribute("totalPrice", totalPrice);
    return "checkout";
  }

  @GetMapping("/decrementQuantity")
  public String decrementQuantity(Model model, @RequestParam("isbn") String isbn) {
    List<BookQuantity> bookQuantities = shoppingCart.getBookQuantities();
    shoppingCart.decrementQuantity(isbn);
    double totalPrice = shoppingCart.calculateTotalPrice();
    model.addAttribute("bookQuantities", bookQuantities);
    model.addAttribute("totalPrice", totalPrice);
    return "checkout";
  }

  @PostMapping("/submitOrder")
  public String submitOrder(@RequestParam("username") String username,
      @RequestParam("total") BigDecimal total,
      @RequestParam("orderTime") LocalDateTime orderTime) {
    List<BookQuantity> bookQuantities = shoppingCart.getBookQuantities();
    bookService.updateBookQuantity(bookQuantities);
    orderService.save(username, total, orderTime);
    shoppingCart.clear();
    return "orderConfirmation";
  }
}