package com.salav.cardealership.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class VaultConfig extends AbstractVaultConfiguration {

    @Override
    public VaultEndpoint vaultEndpoint() {
        VaultEndpoint endpoint = VaultEndpoint.create("localhost", 8200);
        endpoint.setScheme("http");
        return endpoint;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication("s.kcFJdZZMS67NzZ4luTZAiG23");
    }

    @Bean
    public VaultJwtPropertiesConfig vaultJwtPropertiesConfig(VaultTemplate vaultTemplate) {
        VaultJwtPropertiesConfig config = new VaultJwtPropertiesConfig();
        VaultResponse response = vaultTemplate.read("/secret/data/propertiesJwt");
        Map<String, Object> vaultJwtPropertiesConfig = response.getData();
        if (vaultJwtPropertiesConfig != null) {
            LinkedHashMap<String, String> vaultConfig = (LinkedHashMap<String, String>) vaultJwtPropertiesConfig.get("data");
            config.setSecret(vaultConfig.get("secret"));
        }
        return config;
    }
}
