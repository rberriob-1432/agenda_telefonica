package infraestructura.adaptador.persistencia.archivo;
import aplicacion.puertos.salida.ExportarContactosPuerto;
import dominio.modelo.Contacto;
import infraestructura.configuraciones.PropiedadesApp;
import infraestructura.adaptador.persistencia.excepcion.ExcepcionPersistencia;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public final class ExportarContactosTxt
        implements ExportarContactosPuerto {

    private static final String FORMATO_LINEA =
            "%s|%s|%s|%s";

    private final PropiedadesApp propiedadesApp;

    @Override
    public void exportar(final Contacto contacto) {

        final Path ruta =
                Paths.get(
                        propiedadesApp.obtener(
                                "agenda.exportacion"
                        )
                );

        final List<String> lineas =
                new ArrayList<>();

        for (byte i = 0;
             i < contacto.getCantidadContactos();
             i++) {

            lineas.add(
                    String.format(
                            FORMATO_LINEA,
                            contacto.getId(i).value(),
                            contacto.getNombre(i).value(),
                            contacto.getTelefono(i).value(),
                            contacto.getCorreo(i).value()
                    )
            );
        }

        try {

            Files.write(
                    ruta,
                    lineas
            );

        } catch (final IOException excepcion) {

            throw ExcepcionPersistencia
                    .porqueArchivoFallo(excepcion);
        }
    }
}