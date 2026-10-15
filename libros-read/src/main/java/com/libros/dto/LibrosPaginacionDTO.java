package com.libros.dto;

import java.util.List;

public class LibrosPaginacionDTO {

    public List<LibroResponseDTO> libros;

    public int pagina;
    public int tamanoPagina;
    public int totalPaginas;
    public long total;

    public LibrosPaginacionDTO(List<LibroResponseDTO> libros, int pagina, int tamanoPagina, int totalPaginas, long total)
    {
        this.libros = libros;
        this.pagina = pagina;
        this.tamanoPagina = tamanoPagina;
        this.totalPaginas = totalPaginas;
        this.total = total;
    }
}
