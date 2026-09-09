package infraestructura.puntosentrada.cli.io;

import aplicacion.servicios.dto.ContactoRespuestaDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ContactoRespuestaImpresor {

    private static final String SEPARADOR = "-".repeat(52);
    private static final String FORMATO_FILA = "  %-10s : %s%n";

    private final ConsolaIo consola;

    public void imprimir(final ContactoRespuestaDto respuesta) {
        consola.println(SEPARADOR);
        consola.printf(FORMATO_FILA, "ID", respuesta.id());
        consola.printf(FORMATO_FILA, "Nombre", respuesta.nombre());
        consola.printf(FORMATO_FILA, "Teléfono", respuesta.telefono());
        consola.printf(FORMATO_FILA, "Correo", respuesta.correo());
        consola.println(SEPARADOR);
    }
}