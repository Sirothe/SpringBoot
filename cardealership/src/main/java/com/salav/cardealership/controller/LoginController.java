package com.salav.cardealership.controller;

import com.salav.cardealership.config.database.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

    @Autowired
    private VaultTemplate vaultTemplate;

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
        //method to login
    }
}
