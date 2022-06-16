package com.sistema.blog.sistemablogspringbootapirest.dto;

import java.util.List;

public class PublicacionRespuesta {

    private List<PublicacionDTO> Contenido;

    private int numeroPagina;
    private int medidaPagina;
    private Long totalElementos;
    private int totalPaginas;
    private Boolean ultima;

    public PublicacionRespuesta() {
    }

    public List<PublicacionDTO> getContenido() {
        return Contenido;
    }

    public void setContenido(List<PublicacionDTO> contenido) {
        Contenido = contenido;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getMedidaPagina() {
        return medidaPagina;
    }

    public void setMedidaPagina(int medidaPagina) {
        this.medidaPagina = medidaPagina;
    }

    public Long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(Long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public Boolean getUltima() {
        return ultima;
    }

    public void setUltima(Boolean ultima) {
        this.ultima = ultima;
    }
}
