package dominio.ov;

import dominio.excepciones.CorreoInvalidoException;

import java.util.Objects;
import java.util.regex.Pattern;

public record Correo(String value) {

    private static final Pattern PATRON_CORREO =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    public Correo {
        final String normalizedValue =
                Objects.requireNonNull(value, "Correo no puede ser null")
                        .trim()
                        .toLowerCase();

        validateNotEmpty(normalizedValue);
        validateFormat(normalizedValue);

        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw CorreoInvalidoException.becauseValueIsEmpty();
        }
    }

    private static void validateFormat(final String normalizedValue) {
        if (!PATRON_CORREO.matcher(normalizedValue).matches()) {
            throw CorreoInvalidoException.becauseFormatIsInvalid(normalizedValue);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}