package dominio.excepciones;

public final class ContactoNoEncontradoException extends DomainException {

    private ContactoNoEncontradoException(final String message) {
        super(message);
    }

    public static ContactoNoEncontradoException becauseIdWasNotFound(
            final String id) {

        return new ContactoNoEncontradoException(
                "No existe un contacto con el ID: " + id
        );
    }
}