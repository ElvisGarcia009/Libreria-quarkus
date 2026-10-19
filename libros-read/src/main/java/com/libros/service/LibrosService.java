package com.libros.service;

import com.libros.dto.*;
import com.libros.exception.MalRequestException;
import com.libros.exception.LibroNoEncontradoException;
import com.libros.repository.LibroRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class LibrosService {

    private final LibroRepository _libroRepository;

    public LibrosService(LibroRepository libroRepository)
    {
        this._libroRepository = libroRepository;
    }

    public LibrosPaginacionDTO getAllLibros(int pagina, int tamanoPagina)
    {
        var query = _libroRepository.findAll().page(Page.of(pagina, tamanoPagina));

        List<LibroResponseDTO> libros = query.stream().map(LibroResponseDTO::TurnIntoDTO).toList();
        int totalPagina = query.pageCount();
        long total = query.count();

        return new LibrosPaginacionDTO(libros, pagina, tamanoPagina, totalPagina, total);
    }

    public LibroResponseDTO getLibroById(String id)
    {
        var reachTheBook = _libroRepository.findById(new ObjectId(id));

        if (reachTheBook == null)
            throw new LibroNoEncontradoException();

        return LibroResponseDTO.TurnIntoDTO(reachTheBook);
    }
}
