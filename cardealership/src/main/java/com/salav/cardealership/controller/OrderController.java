package com.salav.cardealership.controller;

import com.salav.cardealership.mapper.OrderMapper;
import com.salav.cardealership.model.Order;
import com.salav.cardealership.model.dto.OrderDTO;
import com.salav.cardealership.service.OrderService;
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
    private final OrderMapper orderMapper;
    private static final String PAGE_MAX = "pageMax";
    private static final String TOTAL_ITEMS = "TotalItems";

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/p={pageNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> findPaginatedOrders(@PathVariable(value = "pageNumber") int pageN) {
        int pageS = 5;
        Page<Order> page = orderService.findPaginatedOrders(pageN, pageS);
        HttpHeaders headers = new HttpHeaders();
        headers.add(PAGE_MAX, String.valueOf(page.getTotalPages()));
        headers.add(TOTAL_ITEMS, String.valueOf(page.getTotalElements()));
        List<OrderDTO> listOrders = orderMapper.toDtoList(page.getContent());
        return new ResponseEntity<>(listOrders, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        OrderDTO order = orderMapper.toDto(orderService.findOrderById(id));
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/clinm={name}/p={pageNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> findPaginatedOrdersByClientName(@PathVariable(value = "pageNumber") int pageN, @PathVariable(value = "name") String name) {
        int pageS = 5;
        Page<Order> page = orderService.findPaginatedOrdersByClientName(name, pageN, pageS);
        HttpHeaders headers = new HttpHeaders();
        headers.add(PAGE_MAX, String.valueOf(page.getTotalPages()));
        headers.add(TOTAL_ITEMS, String.valueOf(page.getTotalElements()));
        List<OrderDTO> listOrders = orderMapper.toDtoList(page.getContent());
        return new ResponseEntity<>(listOrders, headers, HttpStatus.OK);
    }

    @GetMapping("/carnm={name}/p={pageNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> findPaginatedOrdersByCarName(@PathVariable(value = "pageNumber") int pageN, @PathVariable(value = "name") String name) {
        int pageS = 5;
        Page<Order> page = orderService.findPaginatedOrdersByCarName(name, pageN, pageS);
        HttpHeaders headers = new HttpHeaders();
        headers.add(PAGE_MAX, String.valueOf(page.getTotalPages()));
        headers.add(TOTAL_ITEMS, String.valueOf(page.getTotalElements()));
        List<OrderDTO> listOrders = orderMapper.toDtoList(page.getContent());
        return new ResponseEntity<>(listOrders, headers, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO order) {
        Order newOrder = orderMapper.fromDto(order);
        orderService.updateOrder(newOrder);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO order) {
        Order newOrder = orderMapper.fromDto(order);
        orderService.updateOrder(newOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
