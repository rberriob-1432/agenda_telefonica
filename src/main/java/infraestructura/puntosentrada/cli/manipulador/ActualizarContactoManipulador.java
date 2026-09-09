package infraestructura.puntosentrada.cli.manipulador;
import aplicacion.puertos.entrada.ActualizarContactoCasoUso;
import aplicacion.servicios.dto.comando.ActualizarContactoComando;
import dominio.excepciones.ContactoNoEncontradoException;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import infraestructura.puntosentrada.cli.manipulador.OperacionManipulador;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ActualizarContactoManipulador
        implements OperacionManipulador {

    private final ActualizarContactoCasoUso actualizarContactoCasoUso;
    private final ConsolaIo consola;

    @Override
    public void manejar() {

        final String id =
                consola.readRequired(
                        "ID del contacto                         : "
                );

        final String nombre =
                consola.readRequired(
                        "Nuevo nombre                            : "
                );

        final String correo =
                consola.readRequired(
                        "Nuevo correo                            : "
                );
        final String telefono =
                consola.readRequired(
                        "Nuevo teléfono                          : "
                );



        final ActualizarContactoComando comando =
                new ActualizarContactoComando(
                        id,
                        nombre,
                        telefono,
                        correo
                );

        try {

            actualizarContactoCasoUso.execute(comando);

            consola.println(
                    "\nContacto actualizado correctamente."
            );

        } catch (
                final ContactoNoEncontradoException excepcion) {

            consola.println(
                    "No encontrado: " + excepcion.getMessage()
            );

        } catch (
                final ConstraintViolationException excepcion) {

            excepcion.getConstraintViolations()
                    .forEach(violacion ->
                            consola.println(
                                    "Error: "
                                            + violacion.getMessage()
                            )
                    );
        }
    }
}
