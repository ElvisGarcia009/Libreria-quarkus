package com.libros.dto;

import com.libros.entity.Libro;

import java.math.BigDecimal;

public class LibroResponseDTO {
    public String titulo;
    public String autor;
    public BigDecimal precio;

    public LibroResponseDTO(String titulo, String autor, BigDecimal precio)
    {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    public static LibroResponseDTO TurnIntoDTO(Libro libroCont)
    {
        return new LibroResponseDTO(libroCont.titulo,
                                    libroCont.autor,
                                    libroCont.precio);
    }
}
