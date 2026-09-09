 package infraestructura.puntosentrada.cli.manipulador;

import aplicacion.puertos.entrada.ConseguirContactoPorIdCasoUso;
import aplicacion.servicios.dto.ContactoRespuestaDto;
import aplicacion.servicios.dto.query.ConseguirContactoPorIdQuery;
import dominio.excepciones.ContactoNoEncontradoException;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import infraestructura.puntosentrada.cli.io.ContactoRespuestaImpresor;
import infraestructura.puntosentrada.cli.manipulador.OperacionManipulador;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ConseguirContactoPorIdManipulador
        implements OperacionManipulador {

    private final ConseguirContactoPorIdCasoUso
            conseguirContactoPorIdCasoUso;

    private final ConsolaIo consola;

    private final ContactoRespuestaImpresor impresor;

    @Override
    public void manejar() {

        final String id =
                consola.readRequired("ID del contacto: ");

        final ConseguirContactoPorIdQuery query =
                new ConseguirContactoPorIdQuery(id);

        try {

            final ContactoRespuestaDto contacto =
                    conseguirContactoPorIdCasoUso
                            .execute(query);

            impresor.imprimir(contacto);

        } catch (
                final ContactoNoEncontradoException excepcion) {

            consola.println(
                    "No encontrado: " + excepcion.getMessage()
            );
        }
    }
}