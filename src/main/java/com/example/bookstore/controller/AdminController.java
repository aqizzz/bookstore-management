package com.example.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
  @Autowired BookService bookService;

  @GetMapping("/update")
  public String showUpdatePage(@RequestParam("isbn") String isbn, Model theModel) {
    Book book = bookService.findBookByIsbn(isbn);
    theModel.addAttribute("book", book);
    return "update";
  }

  @PostMapping("/saveUpdate")
  public String updateBook(@ModelAttribute("book") Book book) {
    bookService.updateBook(book);
    return "redirect:/";
  }

	@GetMapping("/delete")
	public String delete(@RequestParam("isbn") String isbn) {

		bookService.deleteBook(isbn);

		return "redirect:/";
	}

  @GetMapping("/insert")
  public String showInsertPage(Model theModel, HttpSession session) {
    if (!theModel.containsAttribute("book") && session.getAttribute("book") == null) {
      theModel.addAttribute("book", new Book());
    }
    return "insert";
  }

  @PostMapping("/save")
  public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult theBindingResult, Model model) {
    if(theBindingResult.hasErrors()){
      model.addAttribute("book", book);
      return "insert";
    }
    else{
      bookService.save(book);
      return "redirect:/";
    }
  }
}
