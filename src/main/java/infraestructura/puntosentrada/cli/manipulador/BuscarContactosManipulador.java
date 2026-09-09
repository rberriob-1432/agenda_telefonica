package infraestructura.puntosentrada.cli.manipulador;

import aplicacion.puertos.entrada.BuscarContactosCasoUso;
import aplicacion.servicios.dto.ContactoRespuestaDto;
import aplicacion.servicios.dto.comando.BuscarContactosComando;
import aplicacion.servicios.dto.comando.TipoBusqueda;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class BuscarContactosManipulador
        implements OperacionManipulador {

    private final BuscarContactosCasoUso buscarContactosCasoUso;

    private final ConsolaIo consola;

    @Override
    public void manejar() {

        consola.println(
                "=========================================="
        );

        consola.println(
                "          BÚSQUEDA AVANZADA"
        );

        consola.println(
                "=========================================="
        );

        consola.println("1. Buscar por nombre");
        consola.println("2. Buscar por correo");
        consola.println("0. Volver");

        final int opcion =
                consola.readInt(
                        "Seleccione una opción: "
                );

        final TipoBusqueda tipoBusqueda;

        switch (opcion) {

            case 1 ->
                    tipoBusqueda = TipoBusqueda.NOMBRE;

            case 2 ->
                    tipoBusqueda = TipoBusqueda.CORREO;

            case 0 -> {
                return;
            }

            default -> {
                consola.println(
                        "Opción inválida."
                );
                return;
            }
        }

        final String criterio =
                consola.readRequired(
                        "Ingrese el criterio de búsqueda: "
                );

        final BuscarContactosComando comando =
                new BuscarContactosComando(
                        criterio,
                        tipoBusqueda
                );

        try {

            final List<ContactoRespuestaDto> resultados =
                    buscarContactosCasoUso.execute(comando);

            imprimirResultados(resultados);

        } catch (final RuntimeException excepcion) {

            consola.println(
                    "Error: " + excepcion.getMessage()
            );
        }
    }

    private void imprimirResultados(
            final List<ContactoRespuestaDto> resultados) {

        if (resultados.isEmpty()) {

            consola.println(
                    "No se encontraron contactos."
            );

            return;
        }

        consola.println();
        consola.printf(
                "Contactos encontrados: %d%n",
                resultados.size()
        );

        consola.println();

        for (final ContactoRespuestaDto contacto :
                resultados) {

            consola.println(
                    "----------------------------------------"
            );

            consola.printf(
                    "ID       : %s%n",
                    contacto.id()
            );

            consola.printf(
                    "Nombre   : %s%n",
                    contacto.nombre()
            );

            consola.printf(
                    "Teléfono : %s%n",
                    contacto.telefono()
            );

            consola.printf(
                    "Correo   : %s%n",
                    contacto.correo()
            );
        }

        consola.println(
                "----------------------------------------"
        );
    }
}