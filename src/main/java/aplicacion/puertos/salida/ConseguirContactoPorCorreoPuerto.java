package aplicacion.puertos.salida;

import dominio.modelo.Contacto;
import dominio.ov.Correo;

public interface ConseguirContactoPorCorreoPuerto {

    boolean existePorCorreo(Correo correo);
}