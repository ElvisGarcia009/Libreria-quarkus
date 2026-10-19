package com.libros.exception;

public class MalRequestException extends RuntimeException{
    public MalRequestException()
    {
        super("Id suministrado inválido.");
    }
}
