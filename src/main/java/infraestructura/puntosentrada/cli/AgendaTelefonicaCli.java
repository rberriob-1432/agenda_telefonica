package infraestructura.puntosentrada.cli;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import infraestructura.puntosentrada.cli.manipulador.*;
import infraestructura.puntosentrada.cli.menu.OpcionMenu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class AgendaTelefonicaCli {

    private final ConsolaIo consola;
    private final ListarContactosManipulador listarManipulador;
    private final ConseguirContactoPorIdManipulador buscarManipulador;
    private final CrearContactoManipulador crearManipulador;
    private final ActualizarContactoManipulador actualizarManipulador;
    private final BuscarContactosManipulador buscarContactosManipulador;
    private final EliminarContactoManipulador eliminarManipulador;
    private final ExportarContactosManipulador exportarContactosManipulador;
    public void start() {

        boolean ejecutando = true;

        while (ejecutando) {

            mostrarMenu();

            final int numero =
                    consola.readInt("Seleccione una opción: ");

            final OpcionMenu opcion =
                    OpcionMenu.desdeNumero(numero)
                            .orElse(null);

            if (opcion == null) {
                consola.println(
                        "Opción inválida. Intente nuevamente."
                );
                continue;
            }

            switch (opcion) {

                case LISTAR_CONTACTOS ->
                        listarManipulador.manejar();

                case BUSCAR_CONTACTO ->
                        buscarManipulador.manejar();

                case BUSCAR_CONTACTOS ->
                        buscarContactosManipulador.manejar();

                case CREAR_CONTACTO ->
                        crearManipulador.manejar();

                case ACTUALIZAR_CONTACTO ->
                        actualizarManipulador.manejar();

                case ELIMINAR_CONTACTO ->
                        eliminarManipulador.manejar();
                case EXPORTAR_CONTACTOS ->
                        exportarContactosManipulador.manejar();

                case SALIR -> {
                    consola.println(
                            "Saliendo de la Agenda Telefónica..."
                    );
                    ejecutando = false;
                }
            }

            if (ejecutando) {
                consola.println();
            }
        }
    }

    private void mostrarMenu() {

        consola.println(
                "=========================================="
        );
        consola.println(
                "          AGENDA TELEFÓNICA"
        );
        consola.println(
                "=========================================="
        );

        for (final OpcionMenu opcion : OpcionMenu.values()) {

            consola.printf(
                    "%d. %s%n",
                    opcion.getNumero(),
                    opcion.getDescripcion()
            );
        }

        consola.println(
                "=========================================="
        );
    }
}

