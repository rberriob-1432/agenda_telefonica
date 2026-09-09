package infraestructura.puntosentrada.cli.manipulador;

import aplicacion.puertos.entrada.EliminarContactoCasoUso;
import aplicacion.servicios.dto.comando.EliminarContactoComando;
import dominio.excepciones.ContactoNoEncontradoException;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class EliminarContactoManipulador
        implements OperacionManipulador {

    private final EliminarContactoCasoUso eliminarContactoCasoUso;
    private final ConsolaIo consola;

    @Override
    public void manejar() {

        final String id =
                consola.readRequired(
                        "ID del contacto a eliminar: "
                );

        final EliminarContactoComando comando =
                new EliminarContactoComando(id);

        try {

            eliminarContactoCasoUso.execute(comando);

            consola.println(
                    "Contacto eliminado correctamente."
            );

        } catch (
                final ContactoNoEncontradoException excepcion) {

            consola.println(
                    "No encontrado: " + excepcion.getMessage()
            );
        }
    }
}

