package com.sistema.blog.sistemablogspringbootapirest.services;

import com.sistema.blog.sistemablogspringbootapirest.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioService {

    public ComentarioDTO CrearComentario(Long publicacionId, ComentarioDTO comentarioDTO);

    public List<ComentarioDTO> ObtenerComentariosPorPublicacionId(Long publicacionId);

    public ComentarioDTO ObtenerComentarioPorId(Long publicacionId, Long comentarioID);

    public ComentarioDTO ActualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudComentario);

    public void EliminarComentario(Long publicacionId, Long comentarioId);
}
