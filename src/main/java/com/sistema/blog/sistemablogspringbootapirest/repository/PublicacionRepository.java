package com.sistema.blog.sistemablogspringbootapirest.repository;

import com.sistema.blog.sistemablogspringbootapirest.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

}
