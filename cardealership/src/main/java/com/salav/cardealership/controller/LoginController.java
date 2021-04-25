package com.salav.cardealership.controller;

import com.salav.cardealership.config.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

    @Autowired
    private VaultTemplate vaultTemplate;

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
        //method to login
    }

    @GetMapping("/dupa")
    public Object getCredentials() {
        VaultResponse response = vaultTemplate.read("/secret/data/myapp");
        if(response!=null) {
            Object username = response.getData().get("data");
            return username;
        }
        return null;
    }
}
