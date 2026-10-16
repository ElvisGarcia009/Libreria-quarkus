package com.libros.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioLoginDTO {

    @NotBlank
    public String username;

    @NotBlank
    public String password;
}
