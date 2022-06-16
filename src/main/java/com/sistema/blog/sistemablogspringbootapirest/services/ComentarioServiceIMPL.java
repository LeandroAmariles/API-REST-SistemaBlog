package com.sistema.blog.sistemablogspringbootapirest.services;

import com.sistema.blog.sistemablogspringbootapirest.dto.ComentarioDTO;
import com.sistema.blog.sistemablogspringbootapirest.exceptions.BlogAppException;
import com.sistema.blog.sistemablogspringbootapirest.exceptions.ResourceNotFoundException;
import com.sistema.blog.sistemablogspringbootapirest.model.Comentario;
import com.sistema.blog.sistemablogspringbootapirest.model.Publicacion;
import com.sistema.blog.sistemablogspringbootapirest.repository.ComentarioRepository;
import com.sistema.blog.sistemablogspringbootapirest.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceIMPL implements  ComentarioService{

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentarioDTO CrearComentario(Long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = MapearEntity(comentarioDTO);
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()-> new ResourceNotFoundException("Publicacion", "id",publicacionId));

        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);

        return MapearDTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDTO> ObtenerComentariosPorPublicacionId(Long publicacionId) {

        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);

        List <ComentarioDTO> comentarioDTOSList = comentarios.stream().map(comentario -> MapearDTO(comentario)).collect(Collectors.toList());
        return comentarioDTOSList;
    }

    @Override
    public ComentarioDTO ObtenerComentarioPorId(Long publicacionId, Long comentarioID) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()-> new ResourceNotFoundException("Publicacion", "id",publicacionId));

        Comentario comentario = comentarioRepository.findById(comentarioID)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id",comentarioID));
        if(!comentario.getPublicacion().getId().equals(publicacion.getId()))
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicacion");
        return MapearDTO(comentario);
    }

    @Override
    public ComentarioDTO ActualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudComentario) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()-> new ResourceNotFoundException("Publicacion", "id",publicacionId));
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id",comentarioId ));
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }
        comentario.setNombre(solicitudComentario.getNombre());
        comentario.setEmail(solicitudComentario.getEmail());
        comentario.setCuerpoMensaje(solicitudComentario.getCuerpo());

        Comentario comentarioActualizado = comentarioRepository.save(comentario);

        return MapearDTO(comentarioActualizado);
    }

    @Override
    public void EliminarComentario(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId).orElseThrow(()-> new ResourceNotFoundException("Publicacion", "id",publicacionId));
        Comentario comentario = comentarioRepository.findById(comentarioId).orElseThrow(() -> new ResourceNotFoundException("Comentario", "id",comentarioId ));
        if(!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }
        comentarioRepository.delete(comentario);
    }

    private ComentarioDTO MapearDTO(Comentario comentario){
        ComentarioDTO comentarioDTO=new ComentarioDTO();

        comentarioDTO.setId(comentario.getId());
        comentarioDTO.setCuerpo(comentario.getCuerpoMensaje());
        comentarioDTO.setEmail(comentario.getEmail());
        comentarioDTO.setNombre(comentario.getNombre());

        return comentarioDTO;
    }

    private Comentario MapearEntity(ComentarioDTO comentarioDTO){
        Comentario comentario=new Comentario();

        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setId(comentarioDTO.getId());
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setCuerpoMensaje(comentarioDTO.getCuerpo());

        return comentario;
    }
}
