package com.libros.resource;

import com.libros.dto.CrearLibroDTO;
import com.libros.dto.ShowLibroDTO;
import com.libros.dto.UpdateLibroDTO;
import com.libros.entity.Libro;
import com.libros.exception.MalRequestException;
import com.libros.service.LibroService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.decorator.Delegate;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

@Path("api/v1/libro")
@RolesAllowed("escritor")
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
    public ShowLibroDTO createLibro(@Valid CrearLibroDTO nuevoLibro)
    {
        return _libroService.createLibro(nuevoLibro);
    }

    @PUT
    @Path("/{id}")
    public ShowLibroDTO updateLibro(@PathParam("id") String id, @Valid UpdateLibroDTO libroActualizado)
    {
        return _libroService.updateLibro(parsearId(id), libroActualizado);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLibro(@PathParam("id") String id)
    {
        _libroService.deleteLibro(parsearId(id));
        return Response.noContent().build();
    }



    //HELPERS

    private ObjectId parsearId(String id)
    {
        if (!ObjectId.isValid(id))
            throw new MalRequestException("Id inválido");

        return new ObjectId(id);
    }
}
