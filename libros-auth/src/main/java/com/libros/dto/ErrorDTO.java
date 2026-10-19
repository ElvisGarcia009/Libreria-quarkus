package com.libros.dto;

public class ErrorDTO {

    public String errorName;
    public String errorMessage;
    public int statusCode;

    public ErrorDTO(String errorName, String errorMessage, int statusCode)
    {
        this.errorName = errorName;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

}
