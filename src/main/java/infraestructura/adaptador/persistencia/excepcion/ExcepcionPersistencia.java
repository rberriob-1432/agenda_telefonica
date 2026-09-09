package infraestructura.adaptador.persistencia.excepcion;

public final class ExcepcionPersistencia
        extends RuntimeException {

    private static final String MENSAJE_GUARDAR =
            "No se pudo guardar el contacto con ID: '%s'.";

    private static final String MENSAJE_ACTUALIZAR =
            "No se pudo actualizar el contacto con ID: '%s'.";

    private static final String MENSAJE_BUSCAR =
            "No se pudo buscar el contacto con ID: '%s'.";

    private static final String MENSAJE_CORREO =
            "No se pudo buscar el contacto con correo: '%s'.";

    private static final String MENSAJE_TODOS =
            "No se pudieron recuperar todos los contactos.";

    private static final String MENSAJE_ELIMINAR =
            "No se pudo eliminar el contacto con ID: '%s'.";

    private static final String MENSAJE_ARCHIVO =
            "No se pudo acceder al archivo de persistencia.";

    private ExcepcionPersistencia(
            final String mensaje,
            final Throwable causa) {

        super(mensaje, causa);
    }

    public static ExcepcionPersistencia
    porqueGuardarFallo(
            final String id,
            final Throwable causa) {

        return new ExcepcionPersistencia(
                String.format(MENSAJE_GUARDAR, id),
                causa
        );
    }

    public static ExcepcionPersistencia
    porqueActualizarFallo(
            final String id,
            final Throwable causa) {

        return new ExcepcionPersistencia(
                String.format(MENSAJE_ACTUALIZAR, id),
                causa
        );
    }

    public static ExcepcionPersistencia
    porqueBuscarPorIdFallo(
            final String id,
            final Throwable causa) {

        return new ExcepcionPersistencia(
                String.format(MENSAJE_BUSCAR, id),
                causa
        );
    }

    public static ExcepcionPersistencia
    porqueBuscarPorCorreoFallo(
            final String correo,
            final Throwable causa) {

        return new ExcepcionPersistencia(
                String.format(MENSAJE_CORREO, correo),
                causa
        );
    }

    public static ExcepcionPersistencia
    porqueBuscarTodosFallo(
            final Throwable causa) {

        return new ExcepcionPersistencia(
                MENSAJE_TODOS,
                causa
        );
    }

    public static ExcepcionPersistencia
    porqueEliminarFallo(
            final String id,
            final Throwable causa) {

        return new ExcepcionPersistencia(
                String.format(MENSAJE_ELIMINAR, id),
                causa
        );
    }

    public static ExcepcionPersistencia
    porqueArchivoFallo(
            final Throwable causa) {

        return new ExcepcionPersistencia(
                MENSAJE_ARCHIVO,
                causa
        );
    }
}

