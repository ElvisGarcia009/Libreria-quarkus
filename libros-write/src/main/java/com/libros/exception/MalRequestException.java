package com.libros.exception;

public class MalRequestException extends RuntimeException {
    public MalRequestException(String error)
    {
        super(error);
    }
}
