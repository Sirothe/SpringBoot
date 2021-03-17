package com.salav.cardealership.controller;

import com.salav.cardealership.mapper.ClientMapper;
import com.salav.cardealership.model.Client;
import com.salav.cardealership.model.DTO.ClientDTO;
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

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping("/page/{pageNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ClientDTO>> findPaginatedClients(@PathVariable (value = "pageNumber") int pageN) {
        int pageS=5;
        Page<Client> page = clientService.findPaginatedClients(pageN,pageS);
        HttpHeaders headers = new HttpHeaders();
        headers.add("pageNow", String.valueOf(pageN));
        headers.add("pageMax",String.valueOf(page.getTotalPages()));
        headers.add("TotalItems",String.valueOf(page.getTotalElements()));
        List<ClientDTO> listClients = clientMapper.toClientDto(page.getContent());
        return new ResponseEntity<>(listClients,headers,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
        ClientDTO client = clientMapper.toDto(clientService.findClientById(id));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> addClient(@RequestBody Client client) {
        ClientDTO newClient = clientMapper.toDto(clientService.addClient(client));
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody Client client) {
        ClientDTO newClient = clientMapper.toDto(clientService.updateClient(client));
        return new ResponseEntity<>(newClient,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
