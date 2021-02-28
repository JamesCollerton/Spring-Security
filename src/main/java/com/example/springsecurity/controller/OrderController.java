package com.example.springsecurity.controller;

import com.example.springsecurity.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class OrderController {

  @GetMapping("/order")
  public ResponseEntity<Order> getOrder() {

    Order order = Order.builder().name("Order One").description("Order One Description").build();

    return ResponseEntity.ok(order);
  }
}
