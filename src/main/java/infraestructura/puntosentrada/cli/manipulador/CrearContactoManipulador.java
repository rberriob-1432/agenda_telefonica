package infraestructura.puntosentrada.cli.manipulador;
import aplicacion.puertos.entrada.CrearContactoCasoUso;
import aplicacion.servicios.dto.comando.AgregarContactoComando;
import dominio.excepciones.CorreoYaRegistradoException;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CrearContactoManipulador
        implements OperacionManipulador {

    private final CrearContactoCasoUso crearContactoCasoUso;
    private final ConsolaIo consola;

    @Override
    public void manejar() {

        final String nombre =
                consola.readRequired("Nombre   : ");

        final String telefono =
                consola.readRequired("Teléfono : ");

        final String correo =
                consola.readRequired("Correo   : ");

        final AgregarContactoComando comando =
                new AgregarContactoComando(
                        nombre,
                        telefono,
                        correo
                );

        try {

            crearContactoCasoUso.execute(comando);

            consola.println(
                    "\nContacto creado correctamente."
            );

        } catch (
                final CorreoYaRegistradoException excepcion) {

            consola.println(
                    "Error: " + excepcion.getMessage()
            );
        }
    }
}

