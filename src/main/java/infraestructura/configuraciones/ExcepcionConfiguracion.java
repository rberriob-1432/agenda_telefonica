package infraestructura.configuraciones;

public final class ExcepcionConfiguracion
        extends RuntimeException {

    private static final String MENSAJE_CARGA =
            "No se pudo cargar la configuración de la aplicación.";

    private ExcepcionConfiguracion(
            final String mensaje,
            final Throwable causa) {

        super(mensaje, causa);
    }

    public static ExcepcionConfiguracion
    porqueNoSePudoCargar(final Throwable causa) {

        return new ExcepcionConfiguracion(
                MENSAJE_CARGA,
                causa
        );
    }
}