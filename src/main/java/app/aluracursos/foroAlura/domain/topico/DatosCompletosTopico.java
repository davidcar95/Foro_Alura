package app.aluracursos.foroAlura.domain.topico;

import app.aluracursos.foroAlura.domain.respuesta.DatosLimitadosRespuesta;
import app.aluracursos.foroAlura.domain.respuesta.RespuestaRepository;
import app.aluracursos.foroAlura.domain.usuario.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;

public record DatosCompletosTopico(
        Long topicoId,

        String titulo,

        String mensaje,

        LocalDateTime fechaDeCreacion,

        Long usuarioId,

        String nombre,

        Cursos curso,

        List<DatosLimitadosRespuesta> respuestas
) {
    public DatosCompletosTopico(Topico topico, UsuarioRepository uRepository, RespuestaRepository rRepository) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(), topico.getUsuario().getId()
                , uRepository.getReferenceById(topico.getUsuario().getId()).getNombre(),
                topico.getCurso(),
                rRepository.findAllByTopicoId(topico.getId()).stream().map(r -> new DatosLimitadosRespuesta(r.getId(), uRepository.getReferenceById
                        (topico.getUsuario().getId()).getNombre(), r.getMensaje(), r.getFechaDeCreacion(), r.getSolucion())).toList());
    }

}
