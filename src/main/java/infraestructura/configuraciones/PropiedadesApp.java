package infraestructura.configuraciones;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public final class PropiedadesApp {

    private static final String ARCHIVO_PROPIEDADES =
            "application.properties";

    private final Properties propiedades;

    public PropiedadesApp() {
        this(
                PropiedadesApp.class
                        .getClassLoader()
                        .getResourceAsStream(ARCHIVO_PROPIEDADES)
        );
    }

    PropiedadesApp(final InputStream flujo) {
        this.propiedades = cargar(flujo);
    }

    private static Properties cargar(
            final InputStream flujo) {

        Objects.requireNonNull(
                flujo,
                "Archivo no encontrado: "
                        + ARCHIVO_PROPIEDADES
        );

        final Properties propiedades =
                new Properties();

        try (flujo) {
            propiedades.load(flujo);
        } catch (final IOException excepcion) {
            throw ExcepcionConfiguracion
                    .porqueNoSePudoCargar(excepcion);
        }

        return propiedades;
    }

    public String obtener(final String clave) {

        final String valor =
                propiedades.getProperty(clave);

        Objects.requireNonNull(
                valor,
                "Propiedad no encontrada en "
                        + ARCHIVO_PROPIEDADES
                        + ": "
                        + clave
        );

        return valor;
    }

    public int obtenerEntero(final String clave) {
        return Integer.parseInt(obtener(clave));
    }
}