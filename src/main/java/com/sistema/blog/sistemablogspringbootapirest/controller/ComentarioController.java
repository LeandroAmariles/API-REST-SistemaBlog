package com.sistema.blog.sistemablogspringbootapirest.controller;

import com.sistema.blog.sistemablogspringbootapirest.dto.ComentarioDTO;
import com.sistema.blog.sistemablogspringbootapirest.model.Comentario;
import com.sistema.blog.sistemablogspringbootapirest.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> GuardarComentario(@PathVariable Long publicacionId, @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(comentarioService.CrearComentario(publicacionId,comentarioDTO), HttpStatus.OK);
    }
    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> ListarComentariosPorPublicacionId(@PathVariable Long publicacionId){
        return comentarioService.ObtenerComentariosPorPublicacionId(publicacionId);
    }
    @GetMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> ObtenerComentarioPorId(@PathVariable Long publicacionId, @PathVariable Long comentarioId){
        return new ResponseEntity<>(comentarioService.ObtenerComentarioPorId(publicacionId, comentarioId),HttpStatus.OK);
    }
    @PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public ResponseEntity<ComentarioDTO> ActualizarComentarioPorId(@PathVariable Long publicacionId, @PathVariable Long comentarioId, @RequestBody ComentarioDTO comentarioDTO){
        return new ResponseEntity<>(comentarioService.ActualizarComentario(publicacionId,comentarioId,comentarioDTO), HttpStatus.OK);
    }
    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
    public void EliminarComentarioPorId(@PathVariable Long publicacionId, @PathVariable Long comentarioId){
        comentarioService.EliminarComentario(publicacionId, comentarioId);
    }
;
}
