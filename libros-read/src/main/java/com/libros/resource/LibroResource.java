package com.libros.resource;

import com.libros.dto.LibroResponseDTO;
import com.libros.dto.LibrosPaginacionDTO;
import com.libros.service.LibrosService;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/v1/libro")
@Produces({MediaType.APPLICATION_JSON})
public class LibroResource {

    private final LibrosService _librosService;

    public LibroResource(LibrosService librosService)
    {
        this._librosService = librosService;
    }

    @GET
    public Response getAllLibros(@QueryParam("pagina") @PositiveOrZero @DefaultValue("0") int pagina,
                                 @QueryParam("tamanoPagina") @PositiveOrZero @DefaultValue("10") int tamanoPagina)
    {
        LibrosPaginacionDTO allLibros = _librosService.getAllLibros(pagina, tamanoPagina);

        return Response.status(200).entity(allLibros).build();
    }

    @GET
    @Path("/{id}")
    public Response getLibroById(@PathParam("id") String id)
    {
        LibroResponseDTO libroEncontrado = _librosService.getLibroById(id);

        if (libroEncontrado == null)
            return Response.status(404).build();

        return Response.accepted(libroEncontrado).build();
    }

}
