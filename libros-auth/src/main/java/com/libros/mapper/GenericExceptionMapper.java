package com.libros.mapper;


import com.libros.dto.ErrorDTO;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception>
{

    private final static Logger LOG = Logger.getLogger(GenericExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {

        if (exception instanceof WebApplicationException webException)
            return webException.getResponse();


        LOG.error("Error inesperado", exception);
        ErrorDTO error = new ErrorDTO("INTERNAL_SERVER_ERROR",
                "Ocurrió un error inesperado",
                500);

        return Response.status(error.statusCode).entity(error).build();
    }
}