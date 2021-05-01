package com.salav.cardealership.repo;

import com.salav.cardealership.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Page<Client> findByNameContains(String name, Pageable pageable);

    List<Client> findAllByNameContains(String name, Sort sort);
}
