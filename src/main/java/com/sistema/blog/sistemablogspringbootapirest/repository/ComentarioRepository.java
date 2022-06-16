package com.sistema.blog.sistemablogspringbootapirest.repository;

import com.sistema.blog.sistemablogspringbootapirest.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    public List<Comentario> findByPublicacionId(Long publicacionId);

}
