package com.example.bookstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository  extends JpaRepository<Orders, Integer>{
  
  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(
      value =
          "insert into orders(username, total, order_time) values(:username, :total, :orderTime)",
      nativeQuery = true)
  void insertOrder(
      @Param("username") String username,
      @Param("total") BigDecimal total,
      @Param("orderTime") LocalDateTime orderTime);
}
