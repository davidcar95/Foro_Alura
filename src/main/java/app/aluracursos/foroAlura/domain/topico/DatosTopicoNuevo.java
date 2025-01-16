package app.aluracursos.foroAlura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTopicoNuevo(
        @NotNull
        Long usuarioId,

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotBlank
        String status,

        @NotNull
        Cursos curso
) {
}
