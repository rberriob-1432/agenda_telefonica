package infraestructura.puntosentrada.cli.manipulador;
import aplicacion.puertos.entrada.ConseguirTodosContactosCasoUso;
import dominio.modelo.Contacto;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListarContactosManipulador
        implements OperacionManipulador {

    private final ConseguirTodosContactosCasoUso
            conseguirTodosContactosCasoUso;

    private final ConsolaIo consola;

    @Override
    public void manejar() {

        final Contacto contactos =
                conseguirTodosContactosCasoUso.execute();

        if (contactos.getCantidadContactos() == 0) {
            consola.println(
                    "No hay contactos registrados."
            );
            return;
        }

        consola.printf(
                "%nTotal: %d contacto(s)%n",
                contactos.getCantidadContactos()
        );

        for (byte i = 0;
             i < contactos.getCantidadContactos();
             i++) {

            consola.println("--------------------");
            consola.printf(
                    "ID       : %s%n",
                    contactos.getId(i).value()
            );
            consola.printf(
                    "Nombre   : %s%n",
                    contactos.getNombre(i).value()
            );
            consola.printf(
                    "Teléfono : %s%n",
                    contactos.getTelefono(i).value()
            );
            consola.printf(
                    "Correo   : %s%n",
                    contactos.getCorreo(i).value()
            );
        }

        consola.println("--------------------");
    }
}