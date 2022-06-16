package com.sistema.blog.sistemablogspringbootapirest.services;

import com.sistema.blog.sistemablogspringbootapirest.dto.PublicacionDTO;
import com.sistema.blog.sistemablogspringbootapirest.dto.PublicacionRespuesta;

import java.util.List;

public interface PublicacionService {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public PublicacionRespuesta ListarPublicaciones(int numeroDePagina, int medidaDePagina, String ordernarPor, String sortDir);

    public PublicacionDTO ObtenerPublicacionPorId(long id);

    public PublicacionDTO ActualizarPublicacion(PublicacionDTO publicacionDTO, Long id);

    public void EliminarPublicacion(Long id);
}
