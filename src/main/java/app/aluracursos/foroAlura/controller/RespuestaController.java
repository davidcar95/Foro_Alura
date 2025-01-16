package app.aluracursos.foroAlura.controller;

import app.aluracursos.foroAlura.domain.respuesta.*;
import app.aluracursos.foroAlura.domain.topico.Topico;
import app.aluracursos.foroAlura.domain.topico.TopicoRepository;
import app.aluracursos.foroAlura.domain.usuario.Usuario;
import app.aluracursos.foroAlura.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos/{topicoId}")

@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private AccionDeLaRespuesta validar;


    @PostMapping
    public ResponseEntity<DatosConfirmacionRespuesta> nuevaRespuesta(@RequestBody @Valid DatosNuevaRespuesta datos,
                                                              UriComponentsBuilder uriComponentsBuilder){
        Respuesta respuesta = new Respuesta(datos, usuarioRepository, topicoRepository);
        respuestaRepository.save(respuesta);
        URI url = uriComponentsBuilder.path("/topicos/{topicoId}/{respuestaId}").buildAndExpand(respuesta.getTopico().getId(), respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosConfirmacionRespuesta(
                respuesta, respuestaRepository, usuarioRepository, topicoRepository));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@RequestBody DatosEliminarRespuesta datos,@PathVariable Long id){
        if (!respuestaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);

        return ResponseEntity.noContent().build();
    }


    // Nuevo endpoint para listar todas las respuestas de un tópico
    @GetMapping
    public ResponseEntity<List<DatosRespuesta>> listarRespuestasPorTopico(@PathVariable Long topicoId) {
        if (!topicoRepository.existsById(topicoId)) {
            return ResponseEntity.notFound().build();
        }
        List<Respuesta> respuestas = respuestaRepository.findByTopicoId(topicoId);
        List<DatosRespuesta> datosRespuestas = respuestas.stream()
                .map(DatosRespuesta::new)
                .toList();
        return ResponseEntity.ok(datosRespuestas);
    }


    // Nuevo endpoint para listar todas las respuestas de todos los tópicos
    @GetMapping("/respuestas")
    public ResponseEntity<?> listarTodasLasRespuestas() {
        List<Respuesta> respuestas = respuestaRepository.findAll();
        if (respuestas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay respuestas registradas.");
        }
        List<DatosRespuesta> datosRespuestas = respuestas.stream()
                .map(DatosRespuesta::new)
                .toList();
        return ResponseEntity.ok(datosRespuestas);
    }




}
