package aplicacion.puertos.salida;

import dominio.modelo.Contacto;
import dominio.ov.Id;

import java.util.Optional;

public interface ConseguirContactoPorIdPuerto {

    Optional<Byte> getIndicePorId(Id id);
}