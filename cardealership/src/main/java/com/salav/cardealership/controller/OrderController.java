package com.salav.cardealership.controller;

import com.salav.cardealership.model.Client;
import com.salav.cardealership.model.Order;
import com.salav.cardealership.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/page/{pageNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> findPaginatedOrders(@PathVariable (value = "pageNumber") int pageN) {
        int pageS=5;
        Page<Order> page = orderService.findPaginatedOrders(pageN,pageS);
        HttpHeaders headers = new HttpHeaders();
        headers.add("pageNow", String.valueOf(pageN));
        headers.add("pageMax",String.valueOf(page.getTotalPages()));
        headers.add("TotalItems",String.valueOf(page.getTotalElements()));
        List<Order> listOrders = page.getContent();

        return new ResponseEntity<>(listOrders,headers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> getOrderById (@PathVariable("id") Long id) {
        Order order = orderService.findOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order newOrder = orderService.addOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order newOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(newOrder,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
