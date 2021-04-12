package com.salav.cardealership.service;

import com.salav.cardealership.exception.ElementNotFoundException;
import com.salav.cardealership.model.Client;
import com.salav.cardealership.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        client.setName(client.getName().substring(0, 1).toUpperCase() + client.getName().substring(1));
        client.setSurname(client.getSurname().substring(0, 1).toUpperCase() + client.getSurname().substring(1));
        return clientRepo.save(client);
    }

    public Page<Client> findPaginatedClients(int pageN, int pageS) {
        Pageable pageable = PageRequest.of(pageN - 1, pageS);
        return this.clientRepo.findAll(pageable);
    }

    public List<Client> findAllByName(String name) {
        return this.clientRepo.findAllByNameContains(name, Sort.by("surname"));
    }

    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }

    public Client findClientById(Long id) {
        return clientRepo.findById(id).orElseThrow(() -> new ElementNotFoundException("Car by id" + id + "was not found"));
    }

    public Page<Client> findPaginatedClientsByName(int pageN, int pageS, String name) {
        Pageable pageable = PageRequest.of(pageN - 1, pageS);
        return clientRepo.findByNameContains(name, pageable);
    }

    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }
}
