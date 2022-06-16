package com.sistema.blog.sistemablogspringbootapirest.services;

import com.sistema.blog.sistemablogspringbootapirest.dto.PublicacionDTO;
import com.sistema.blog.sistemablogspringbootapirest.dto.PublicacionRespuesta;
import com.sistema.blog.sistemablogspringbootapirest.exceptions.ResourceNotFoundException;
import com.sistema.blog.sistemablogspringbootapirest.model.Publicacion;
import com.sistema.blog.sistemablogspringbootapirest.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceIMPL implements PublicacionService {

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        //Convertimos de  DTO a entidad(moodel)

        Publicacion publicacion = MapearEntity(publicacionDTO);

        Publicacion nuevaP = publicacionRepository.save(publicacion);

        //Convertimos de entidad a DTO

        PublicacionDTO publicacionResponse= MapearDTO(nuevaP);

        return publicacionResponse;
    }

    @Override
    public PublicacionRespuesta ListarPublicaciones(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(ordenarPor).ascending():Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina, sort);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
        List<Publicacion> listadePublicaciones = publicaciones.getContent();

       List<PublicacionDTO> contenido = listadePublicaciones.stream().map(publicacion -> MapearDTO(publicacion)).collect(Collectors.toList());

       PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
       publicacionRespuesta.setContenido(contenido);
       publicacionRespuesta.setNumeroPagina(publicaciones.getNumber());
       publicacionRespuesta.setMedidaPagina(publicaciones.getSize());
       publicacionRespuesta.setTotalElementos(publicaciones.getTotalElements());
       publicacionRespuesta.setTotalPaginas(publicaciones.getTotalPages());
       publicacionRespuesta.setUltima(publicaciones.isLast());

       return publicacionRespuesta;
    }

    @Override
    public PublicacionDTO ObtenerPublicacionPorId(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("publicacion","id",id));
        return MapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO ActualizarPublicacion(PublicacionDTO publicacionDTO, Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("publicacion","id",id));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());

        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);

        return MapearDTO(publicacionActualizada);
    }

    @Override
    public void EliminarPublicacion(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("publicacion","id",id));

        publicacionRepository.delete(publicacion);
    }

    //Este metodo convierte a DTO
    private PublicacionDTO MapearDTO(Publicacion publicacion){
        PublicacionDTO publicacionDTO=new PublicacionDTO();

        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setContenido(publicacion.getContenido());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());

        return publicacionDTO;
    }
    //Este metodo convierte a Entity
    private Publicacion MapearEntity(PublicacionDTO publicacionDTO){
        Publicacion publicacion = new Publicacion();
        publicacion.setId(publicacionDTO.getId());
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        return publicacion;
    }
}
