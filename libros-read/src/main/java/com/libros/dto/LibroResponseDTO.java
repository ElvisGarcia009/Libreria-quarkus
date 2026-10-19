package com.libros.dto;

import com.libros.entity.Libro;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

public class LibroResponseDTO {
    public String id;
    public String titulo;
    public String autor;
    public BigDecimal precio;

    public LibroResponseDTO(String id, String titulo, String autor, BigDecimal precio)
    {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    public static LibroResponseDTO TurnIntoDTO(Libro libroCont)
    {
        return new LibroResponseDTO(libroCont.id.toString(),
                                    libroCont.titulo,
                                    libroCont.autor,
                                    libroCont.precio);
    }
}
