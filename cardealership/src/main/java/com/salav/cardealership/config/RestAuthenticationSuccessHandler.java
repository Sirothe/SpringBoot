package com.salav.cardealership.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.salav.cardealership.config.vault.VaultJwtPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    VaultJwtPropertiesConfig vaultJwtPropertiesConfig;

    private final long expirationTime;

    public RestAuthenticationSuccessHandler(@Value("${jwt.expirationTime}") long expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(vaultJwtPropertiesConfig.getSecret()));
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Username", principal.getUsername());
        response.addHeader("Roles", principal.getAuthorities().toString());
    }
}
