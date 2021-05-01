package com.salav.cardealership.repo;

import com.salav.cardealership.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    Page<Car> findByNameContains(String name, Pageable pageable);

    List<Car> findAllByNameContains(String name, Sort sort);
}
