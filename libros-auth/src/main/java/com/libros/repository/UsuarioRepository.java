package com.libros.repository;

import com.libros.entity.Usuario;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheMongoRepository<Usuario> {

    public Usuario buscarPorUsername(String username)
    {
        return find("username", username).firstResult();
    }
}
