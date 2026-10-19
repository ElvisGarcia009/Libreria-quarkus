package com.libros.mapper;

import com.libros.dto.ErrorContainerDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        String message = exception.getConstraintViolations()
                .stream()
                .map(constraint -> constraint.getPropertyPath() + ": " + constraint.getMessage())
                .collect(Collectors.joining(", "));

        ErrorContainerDTO errorFound = new ErrorContainerDTO("BAD REQUEST",
                                                              message,
                                                             400);

        return Response.status(errorFound.statusCode).entity(errorFound).build();
    }
}
