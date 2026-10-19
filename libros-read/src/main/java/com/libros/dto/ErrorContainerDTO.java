package com.libros.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorContainerDTO {

    @JsonProperty("Error_name")
    public String errorName;

    @JsonProperty("Error_message")
    public String errorMessage;

    @JsonProperty("Status_code")
    public int statusCode;

    public ErrorContainerDTO(String errorName, String errorMessage, int statusCode)
    {
        this.errorName = errorName;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }
}
