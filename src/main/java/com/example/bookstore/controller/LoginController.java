package com.example.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
  @GetMapping("/login")
  public String showLoginPage() {
      return "login";
  }
  
  @GetMapping("/access-denied")
  public String showAccessDenied() {
      return "access-denied";
  }
}
