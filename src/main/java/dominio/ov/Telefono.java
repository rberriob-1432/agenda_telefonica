package dominio.ov;
import dominio.excepciones.TelefonoInvalidoException;
import java.util.Objects;
import java.util.regex.Pattern;
public record Telefono(String value) {

    private static final Pattern TELEFONO_PATTERN =
            Pattern.compile("^\\d+$");
    public Telefono {
        final String normalizedValue =
                Objects.requireNonNull(value, "Telefono no puede ser nulo").trim();
        validateNotEmpty(normalizedValue);
        validateFormat(normalizedValue);
        value = normalizedValue;
    }
    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw TelefonoInvalidoException.becauseValueIsEmpty();
        }
    }
    private static void validateFormat(final String normalizedValue) {
        if (!TELEFONO_PATTERN.matcher(normalizedValue).matches()) {
            throw TelefonoInvalidoException.becauseFormatIsInvalid(normalizedValue);
        }
    }
    @Override
    public String toString() {
        return value;
    }
}