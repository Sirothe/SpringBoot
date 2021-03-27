package com.salav.cardealership.service;

import com.salav.cardealership.exception.ElementNotFoundException;
import com.salav.cardealership.model.Order;
import com.salav.cardealership.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Page<Order> findPaginatedOrders(int pageN, int pageS) {
        Pageable pageable = PageRequest.of(pageN - 1, pageS);
        return this.orderRepo.findAll(pageable);
    }

    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    public Order updateOrder(Order order) {
        return orderRepo.save(order);
    }

    public Order findOrderById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("Order by id" + id + "was not found"));
    }

    public Page<Order> findPaginatedOrdersByClientName(String name, int pageN, int pageS) {
        Pageable pageable = PageRequest.of(pageN - 1, pageS);
        return orderRepo.findAllByClientName(name, pageable);
    }

    public Page<Order> findPaginatedOrdersByCarName(String name, int pageN, int pageS) {
        Pageable pageable = PageRequest.of(pageN - 1, pageS);
        return orderRepo.findAllByCarName(name, pageable);
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
