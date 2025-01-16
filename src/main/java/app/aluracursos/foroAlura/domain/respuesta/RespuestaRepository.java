package app.aluracursos.foroAlura.domain.respuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {


    void deleteAllByTopicoId(Long id);

    @Query("SELECT r FROM Respuesta r WHERE r.topico.id = :id ORDER BY r.solucion DESC")
    List<Respuesta> findAllByTopicoId(Long id);

    // Este método debe retornar las respuestas filtradas por ID de

    List<Respuesta> findByTopicoId(Long topicoId);
}
