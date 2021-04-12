package com.salav.cardealership.repo;

import com.salav.cardealership.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Page<Order> findAllByClientNameContains(String name, Pageable pageable);

    Page<Order> findAllByCarNameContains(String name, Pageable pageable);
}
