package com.libros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CrearLibroDTO {

    @NotBlank(message = "Titulo no puede estar en blanco")
    @Size(max = 30, message = "Titulo no puede tener mas de 30 caracteres")
    public String titulo;

    @NotBlank(message = "Autor no puede estar en blanco")
    @Size(max = 30, message = "Autor no puede tener mas de 30 caracteres")
    public String autor;

    @NotNull(message = "Precio no puede estar vacio")
    @Positive(message = "Precio debe ser mayor a 0")
    public BigDecimal precio;
}
