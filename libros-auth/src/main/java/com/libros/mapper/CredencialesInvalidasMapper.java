package com.libros.mapper;

import com.libros.dto.ErrorDTO;
import com.libros.exception.CredencialesInvalidasException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CredencialesInvalidasMapper implements ExceptionMapper<CredencialesInvalidasException>
{
    @Override
    public Response toResponse(CredencialesInvalidasException exception) {
        ErrorDTO credencialesError = new ErrorDTO("UNAUTHORIZED",
                                                   exception.getMessage(),
                                                  401);

        return Response.status(credencialesError.statusCode).entity(credencialesError).build();
    }
}