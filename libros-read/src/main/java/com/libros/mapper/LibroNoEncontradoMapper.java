package com.libros.mapper;

import com.libros.dto.ErrorContainerDTO;
import com.libros.exception.LibroNoEncontradoException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class LibroNoEncontradoMapper implements ExceptionMapper<LibroNoEncontradoException> {

    @Override
    public Response toResponse(LibroNoEncontradoException exception) {
        ErrorContainerDTO errorFound = new ErrorContainerDTO("NOT FOUND",
                                                              exception.getMessage(),
                                                              404);

        return Response.status(errorFound.statusCode).entity(errorFound).build();
    }
}
