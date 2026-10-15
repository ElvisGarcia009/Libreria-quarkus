package com.libros.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@MongoEntity(collection = "libros")
public class Libro {
    public ObjectId id;
    public String titulo;
    public String autor;
    public BigDecimal precio;
}
