package com.libros.dto;

import com.libros.entity.Libro;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

public class ShowLibroDTO {
    public String id;
    public String titulo;
    public String autor;
    public BigDecimal precio;

    public ShowLibroDTO(String id, String titulo, String autor, BigDecimal precio)
    {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    public static ShowLibroDTO TurnIntoDTO(Libro libro)
    {
        return new ShowLibroDTO(libro.id.toString(),
                                libro.titulo,
                                libro.autor,
                                libro.precio);
    }
}
