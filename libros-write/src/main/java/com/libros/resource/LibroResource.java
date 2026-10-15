package com.libros.resource;

import com.libros.dto.CrearLibroDTO;
import com.libros.dto.ShowLibroDTO;
import com.libros.dto.UpdateLibroDTO;
import com.libros.entity.Libro;
import com.libros.service.LibroService;
import jakarta.decorator.Delegate;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@Path("api/v1/libro")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibroResource {

    //DEPENDENCY INJECTION
    private final LibroService _libroService;
    public LibroResource(LibroService libroService)
    {
        _libroService = libroService;
    }

    @POST
    public Response createLibro(@Valid CrearLibroDTO nuevoLibro)
    {
        ShowLibroDTO mostrarLibro = _libroService.createLibro(nuevoLibro);
        return Response.accepted(mostrarLibro).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateLibro(@PathParam("id") String id, @Valid UpdateLibroDTO libroActualizado)
    {
        if (!ObjectId.isValid(id)) throw new BadRequestException("id invalido");

        return _libroService.updateLibro(id, libroActualizado) ? Response.accepted().build()
                                                               : Response.status(404).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLibro(@PathParam("id") String id)
    {
        if (!ObjectId.isValid(id))
            throw new BadRequestException("id invalido");

        return _libroService.deleteLibro(id) ? Response.noContent().build() : Response.status(404).build();
    }
}
