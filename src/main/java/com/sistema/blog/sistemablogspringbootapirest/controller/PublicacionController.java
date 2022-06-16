package com.sistema.blog.sistemablogspringbootapirest.controller;

import com.sistema.blog.sistemablogspringbootapirest.dto.PublicacionDTO;
import com.sistema.blog.sistemablogspringbootapirest.dto.PublicacionRespuesta;
import com.sistema.blog.sistemablogspringbootapirest.services.PublicacionService;
import com.sistema.blog.sistemablogspringbootapirest.utilities.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public PublicacionRespuesta ListarPublicaciones(@RequestParam(value = "NumeroDePagina",defaultValue = AppConstantes.numero_de_pagina_por_defecto, required = false)int numeroPagina, @RequestParam(value="pageSize", defaultValue = AppConstantes.medida_de_pagina_por_defecto,required = false)int tamanioPagina,
                                                    @RequestParam (value = "sortBy", defaultValue = AppConstantes.ordenar_por_defecto, required = false) String ordenarPor,
                                                    @RequestParam(value="sortDir",defaultValue = AppConstantes.ordenar_direccion_por_defecto, required = false ) String sortDir){ //@RequestParam es para la paginacion empieza en la pag 0 y 10 resgitros por pagina
        return publicacionService.ListarPublicaciones(numeroPagina,tamanioPagina,ordenarPor,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> ObtenerPublicacionPorId(@PathVariable Long id){
        return ResponseEntity.ok(publicacionService.ObtenerPublicacionPorId(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionDTO> GuardarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO){
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> ActualizarPublicacion(@Valid @RequestBody PublicacionDTO publicacionDTO, @PathVariable Long id){
        PublicacionDTO publicacionRespuesta = publicacionService.ActualizarPublicacion(publicacionDTO, id);
        return new ResponseEntity<>(publicacionRespuesta, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> EliminarPublicacion(@PathVariable Long id){
        publicacionService.EliminarPublicacion(id);
        return new ResponseEntity<>("Publicacion eliminada con exito", HttpStatus.OK);
    }

}
