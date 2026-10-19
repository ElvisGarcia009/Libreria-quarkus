package com.libros.mapper;

import com.libros.dto.ErrorContainerDTO;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {

        if (exception instanceof WebApplicationException webException)
        {
            return webException.getResponse();
        }

        LOG.error("Error inesperado", exception);

        ErrorContainerDTO errorFound = new ErrorContainerDTO("INTERNAL SERVER ERROR",
                                                              exception.getMessage(),
                                                             500);

        return Response.status(errorFound.statusCode).entity(errorFound).build();
    }
}
