package com.example.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;

@Controller
public class BookStoreController {

  @Autowired BookService bookService;
  
  @GetMapping("/")
  public String showHome(Model theModel) {
    List<Book> books = bookService.getBooks();
    theModel.addAttribute("books", books);
    return "home";
  }

  @GetMapping("/searchBook")
  public String searchBook(@RequestParam("searchOption") String searchOption, @RequestParam("searchQuery") String searchQuery, Model theModel) {
    List<Book> books = bookService.getBooks(searchOption, searchQuery);
    theModel.addAttribute("books", books);
    return "home";
  }

  @GetMapping("/showDetail")
  public String showBookDetail(@RequestParam("isbn") String isbn, Model model) {
    Book book = bookService.findBookByIsbn(isbn);
    if (book != null) {
      model.addAttribute("book", book);
    }
    return "detail";
  }
}
