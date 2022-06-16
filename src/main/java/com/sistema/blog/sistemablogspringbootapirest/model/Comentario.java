package com.sistema.blog.sistemablogspringbootapirest.model;

import javax.persistence.*;

@Entity
@Table(name="COmentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String cuerpoMensaje;

    @ManyToOne(fetch = FetchType.LAZY)//  ManytoOne(muchos comentarios en una publicacion) : Lazy (solo obtener listados cuando necesitemos)
    @JoinColumn(name="publicacion_id",nullable = false)
    private  Publicacion publicacion;

    public Comentario() {
    }

    public Comentario(Long id, String nombre, String email, String cuerpoMensaje, Publicacion publicacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cuerpoMensaje = cuerpoMensaje;
        this.publicacion = publicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }
}
