package com.example.bookstore.service;

import com.example.bookstore.model.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testSave() {
        // Arrange
        String username = "testUser";
        BigDecimal total = BigDecimal.valueOf(100);
        LocalDateTime orderTime = LocalDateTime.now();

        // Act
        orderService.save(username, total, orderTime);

        // Assert
        verify(ordersRepository).insertOrder(username, total, orderTime);
    }
}