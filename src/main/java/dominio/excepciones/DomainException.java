package dominio.excepciones;

public abstract class DomainException extends RuntimeException {

    protected DomainException(final String message) {
        super(message);
    }
}