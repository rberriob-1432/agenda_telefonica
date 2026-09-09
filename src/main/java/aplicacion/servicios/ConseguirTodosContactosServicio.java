package aplicacion.servicios;

import aplicacion.puertos.entrada.ConseguirTodosContactosCasoUso;
import aplicacion.puertos.salida.ConseguirTodosContactosPuerto;
import dominio.modelo.Contacto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ConseguirTodosContactosServicio
        implements ConseguirTodosContactosCasoUso {

    private final ConseguirTodosContactosPuerto conseguirTodosContactosPuerto;

    @Override
    public Contacto execute() {
        return conseguirTodosContactosPuerto.getAll();
    }

}
