package com.libros.exception;

public class LibroNoEncontradoException extends RuntimeException{
    public LibroNoEncontradoException()
    {
        super("El libro no existe.");
    }
}
