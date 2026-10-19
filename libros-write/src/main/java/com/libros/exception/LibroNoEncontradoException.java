package com.libros.exception;

public class LibroNoEncontradoException extends RuntimeException{
    public LibroNoEncontradoException()
    {
        super("Libro no encontrado");
    }
}
