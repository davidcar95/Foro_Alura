package app.aluracursos.foroAlura.domain.respuesta;

import app.aluracursos.foroAlura.domain.topico.TopicoRepository;
import app.aluracursos.foroAlura.domain.usuario.UsuarioRepository;

import java.time.LocalDateTime;

public record DatosConfirmacionRespuesta(
        Long id,

        String nombreUsuario,

        String tituloTopico,

        String mensaje,

        LocalDateTime fechaDeCreacion
) {
    public DatosConfirmacionRespuesta(Respuesta respuesta, RespuestaRepository repository,
                                      UsuarioRepository uRepository, TopicoRepository tRepository) {
        this(respuesta.getId(), uRepository.getReferenceById(repository.getReferenceById(respuesta.getId()).getUsuario().getId()).getNombre()
                , tRepository.getReferenceById(repository.getReferenceById(respuesta.getId()).getTopico().getId()).getTitulo()
                ,respuesta.getMensaje(), respuesta.getFechaDeCreacion());
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String nombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String tituloTopico() {
        return tituloTopico;
    }

    @Override
    public String mensaje() {
        return mensaje;
    }

    @Override
    public LocalDateTime fechaDeCreacion() {
        return fechaDeCreacion;
    }
}
