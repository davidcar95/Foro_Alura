package app.aluracursos.foroAlura.domain.respuesta;

public record DatosEliminarRespuesta(
        Long topicoId,
        Long usuarioId,
        Long respuestaId
) {
}
