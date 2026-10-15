package com.libros.repository;

import com.libros.entity.Libro;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LibroRepository implements PanacheMongoRepository<Libro> {
}
