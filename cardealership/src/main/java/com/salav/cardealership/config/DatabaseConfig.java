package com.salav.cardealership.config;

import com.salav.cardealership.config.vault.VaultDatabasePropertiesConfig;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(VaultDatabasePropertiesConfig vaultDatabasePropertiesConfig) {

        return DataSourceBuilder
                .create()
                .username(vaultDatabasePropertiesConfig.getUsername())
                .url("jdbc:mysql://localhost:3306/dealership")
                .password(vaultDatabasePropertiesConfig.getPassword())
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public VaultDatabasePropertiesConfig vaultPropertiesConfig(VaultTemplate vaultTemplate) {
        VaultDatabasePropertiesConfig config = new VaultDatabasePropertiesConfig();
        VaultResponse response = vaultTemplate.read("/secret/data/properties");
        Map<String, Object> vaultPropertiesConfig = response.getData();
        if (vaultPropertiesConfig != null) {
            LinkedHashMap<String, String> vaultConfig = (LinkedHashMap<String, String>) vaultPropertiesConfig.get("data");
            config.setPassword(vaultConfig.get("password"));
            config.setUsername(vaultConfig.get("username"));
        }
        return config;
    }
}
