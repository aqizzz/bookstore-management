package com.example.bookstore.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.model.OrdersRepository;

@Service
public class OrderService {
  @Autowired
  private OrdersRepository ordersRepository;

  @Transactional
  public void save(String username, BigDecimal total, LocalDateTime orderTime) {
    ordersRepository.insertOrder(username, total, orderTime);
  }
}
