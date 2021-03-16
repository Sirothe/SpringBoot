package com.salav.cardealership.service;

import com.salav.cardealership.exception.ElementNotFoundException;
import com.salav.cardealership.model.Client;
import com.salav.cardealership.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client addClient(Client client) {
        return clientRepo.save(client);
    }

    public Page<Client> findPaginatedClients(int pageN,int pageS) {
        Pageable pageable = PageRequest.of(pageN-1,pageS);
        return this.clientRepo.findAll(pageable);
    }

    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }

    public Client findClientById(Long id) {
        return clientRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("Car by id"+ id + "was not found"));
    }

    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }
}
