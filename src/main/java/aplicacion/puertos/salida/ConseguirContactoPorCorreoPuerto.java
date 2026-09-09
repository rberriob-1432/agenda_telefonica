package aplicacion.puertos.salida;
import dominio.modelo.Contacto;
import dominio.ov.Correo;
import java.util.Optional;
public interface ConseguirContactoPorCorreoPuerto {
    Optional<Contacto> getByEmail(Correo correo);
}