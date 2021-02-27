package com.example.springsecurity.controller;

import com.example.springsecurity.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
   TODO: What is PreAuthorize
*/
@Slf4j
@PreAuthorize("isAuthenticated()")
@Controller
public class OrderController {

  @GetMapping("/order")
  public ResponseEntity<Order> getOrder() {

    Order order = Order.builder().name("Order One").description("Order One Description").build();

    return ResponseEntity.ok(order);
  }
}
