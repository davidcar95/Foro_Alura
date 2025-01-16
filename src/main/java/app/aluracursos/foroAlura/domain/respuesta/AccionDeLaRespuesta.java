package app.aluracursos.foroAlura.domain.respuesta;

import app.aluracursos.foroAlura.domain.topico.TopicoRepository;
import app.aluracursos.foroAlura.domain.usuario.UsuarioRepository;
import app.aluracursos.foroAlura.infra.errores.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccionDeLaRespuesta {

    @Autowired
    private RespuestaRepository rRepository;

    @Autowired
    private UsuarioRepository uRepository;

    @Autowired
    private TopicoRepository tRepository;


    public DatosConfirmacionRespuesta crearNuevaRespuesta(DatosNuevaRespuesta datos, UsuarioRepository uRepository, TopicoRepository tRepository){
        Respuesta respuesta = new Respuesta(datos, uRepository, tRepository);
        if (!uRepository.existsById(datos.usuarioId())){
            throw new ValidacionException("El id de usuario ingresado no existe.");
        }
        if (!tRepository.existsById(datos.topicoId())){
            throw new ValidacionException("El id de tópico ingresado no existe.");
        }
        rRepository.save(respuesta);
        return new DatosConfirmacionRespuesta(respuesta, rRepository, uRepository, tRepository);
    }
}
