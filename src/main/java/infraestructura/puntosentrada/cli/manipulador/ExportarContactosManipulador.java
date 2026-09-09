package infraestructura.puntosentrada.cli.manipulador;

import aplicacion.puertos.entrada.ExportarContactosCasoUso;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ExportarContactosManipulador
        implements OperacionManipulador {

    private final ExportarContactosCasoUso
            exportarContactosCasoUso;

    private final ConsolaIo consola;

    @Override
    public void manejar() {

        try {

            exportarContactosCasoUso.execute();

            consola.println(
                    "Contactos exportados correctamente."
            );

        } catch (final RuntimeException excepcion) {

            consola.println(
                    "Error al exportar: "
                            + excepcion.getMessage()
            );
        }
    }
}