package com.salav.cardealership.controller;

import com.salav.cardealership.mapper.ClientMapper;
import com.salav.cardealership.model.Client;
import com.salav.cardealership.model.dto.ClientDTO;
import com.salav.cardealership.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private static final String PAGE_MAX = "pageMax";
    private static final String TOTAL_ITEMS = "TotalItems";

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/p={pageNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClientDTO>> findPaginatedClients(@PathVariable(value = "pageNumber") int pageN) {
        int pageS = 5;
        Page<Client> page = clientService.findPaginatedClients(pageN, pageS);
        HttpHeaders headers = new HttpHeaders();
        headers.add(PAGE_MAX, String.valueOf(page.getTotalPages()));
        headers.add(TOTAL_ITEMS, String.valueOf(page.getTotalElements()));
        List<ClientDTO> listClients = clientMapper.toClientDto(page.getContent());
        return new ResponseEntity<>(listClients, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
        ClientDTO client = clientMapper.toDto(clientService.findClientById(id));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/nm={name}/p={pageNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClientDTO>> findPaginatedClientsByName(@PathVariable(value = "pageNumber") int pageN, @PathVariable(value = "name") String name) {
        int pageS = 5;
        Page<Client> page = clientService.findPaginatedClientsByName(pageN, pageS, name);
        HttpHeaders headers = new HttpHeaders();
        headers.add(PAGE_MAX, String.valueOf(page.getTotalPages()));
        headers.add(TOTAL_ITEMS, String.valueOf(page.getTotalElements()));
        List<ClientDTO> listClients = clientMapper.toClientDto(page.getContent());
        return new ResponseEntity<>(listClients, headers, HttpStatus.OK);
    }

    @GetMapping("/nm={name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClientDTO>> findAllClientsByName(@PathVariable(value = "name") String name) {
        List<ClientDTO> listClients = clientMapper.toClientDto(clientService.findAllByName(name));
        return new ResponseEntity<>(listClients, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO client) {
        Client newClient = clientMapper.fromDto(client);
        clientService.addClient(newClient);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO client) {
        Client newClient = clientMapper.fromDto(client);
        clientService.updateClient(newClient);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
