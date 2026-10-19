package com.libros.mapper;

import com.libros.dto.ErrorContainerDTO;
import com.libros.exception.MalRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MalRequestMapper implements ExceptionMapper<MalRequestException> {

    @Override
    public Response toResponse(MalRequestException exception) {
        ErrorContainerDTO errorFound = new ErrorContainerDTO("BAD REQUEST",
                                                             exception.getMessage(),
                                                              400);

        return Response.status(errorFound.statusCode).entity(errorFound).build();
    }
}
