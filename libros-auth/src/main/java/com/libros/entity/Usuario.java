package com.libros.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.Set;

@MongoEntity(collection = "usuarios")
public class Usuario {

    public ObjectId id;
    public String username;
    public String passwordHashed;

    public Set<String> roles;
}
