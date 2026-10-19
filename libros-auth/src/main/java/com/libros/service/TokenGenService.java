package com.libros.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;

@ApplicationScoped
public class TokenGenService {

    public String generarToken(String username, Set<String> roles){
        return Jwt.upn(username).groups(roles).sign();
    }
}
