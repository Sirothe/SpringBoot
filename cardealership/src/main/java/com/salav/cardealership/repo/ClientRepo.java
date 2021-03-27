package com.salav.cardealership.repo;

import com.salav.cardealership.model.Car;
import com.salav.cardealership.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Page<Client> findByNameContains(String name, Pageable pageable);
}
