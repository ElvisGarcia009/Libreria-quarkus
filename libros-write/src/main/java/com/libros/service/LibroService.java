package com.libros.service;

import com.libros.dto.CrearLibroDTO;
import com.libros.dto.ShowLibroDTO;
import com.libros.dto.UpdateLibroDTO;
import com.libros.entity.Libro;
import com.libros.repository.LibroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class LibroService {

    //DEPENDENCY INJECTION
    private final LibroRepository _libroRepository;
    public LibroService(LibroRepository libroRepository)
    {
        _libroRepository = libroRepository;
    }

    //POST FUNCTION
    public ShowLibroDTO createLibro(CrearLibroDTO nuevoLibro)
    {
        Libro nuevoLibroRepository = new Libro();
        nuevoLibroRepository.titulo = nuevoLibro.titulo;
        nuevoLibroRepository.autor = nuevoLibro.autor;
        nuevoLibroRepository.precio = nuevoLibro.precio;

        _libroRepository.persist(nuevoLibroRepository);

        return ShowLibroDTO.TurnIntoDTO(nuevoLibroRepository);
    }

    //PUT FUNCTION
    public boolean updateLibro(String id, UpdateLibroDTO actualizarLibro)
    {
        Libro libroCursor = _libroRepository.findById(new ObjectId(id));

        if (libroCursor == null)
            return false;

        libroCursor.titulo = actualizarLibro.titulo;
        libroCursor.autor = actualizarLibro.autor;
        libroCursor.precio = actualizarLibro.precio;

        _libroRepository.persist(libroCursor);

        return false;
    }

    //DELETE FUNCTION
    public boolean deleteLibro (String id)
    {
        return _libroRepository.deleteById(new ObjectId(id));
    }
}
