package app.aluracursos.foroAlura.domain.respuesta;

import java.time.LocalDateTime;

public class DatosRespuesta {
    private Long id;
    private String contenido;
    private String autor;
    private boolean solucion;

    public DatosRespuesta(Respuesta respuesta) {
        this.id = respuesta.getId();
        this.contenido = respuesta.getMensaje();
        this.autor = respuesta.getUsuario().getNombre();
        this.solucion = respuesta.getSolucion();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isSolucion() {
        return solucion;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }
}



