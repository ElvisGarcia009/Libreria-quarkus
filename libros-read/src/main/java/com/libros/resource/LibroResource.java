package com.libros.resource;

import com.libros.dto.LibroResponseDTO;
import com.libros.dto.LibrosPaginacionDTO;
import com.libros.exception.MalRequestException;
import com.libros.service.LibrosService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@Path("api/v1/libro")
@Produces({MediaType.APPLICATION_JSON})
@RolesAllowed({"lector", "escritor"})
public class LibroResource {

    private final LibrosService _librosService;

    public LibroResource(LibrosService librosService)
    {
        this._librosService = librosService;
    }


    @GET
    public LibrosPaginacionDTO getAllLibros(@QueryParam("pagina") @PositiveOrZero @DefaultValue("0") int pagina,
                                 @QueryParam("tamanoPagina") @PositiveOrZero @DefaultValue("10") int tamanoPagina)
    {
        return _librosService.getAllLibros(pagina, tamanoPagina);
    }


    @GET
    @Path("/{id}")
    public Response getLibroById(@PathParam("id") String id)
    {
        if (!ObjectId.isValid(id))
            throw new MalRequestException();

        LibroResponseDTO libroEncontrado = _librosService.getLibroById(id);
        return Response.accepted(libroEncontrado).build();
    }

}
