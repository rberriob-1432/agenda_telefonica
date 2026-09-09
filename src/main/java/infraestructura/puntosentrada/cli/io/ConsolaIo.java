package infraestructura.puntosentrada.cli.io;

import java.io.PrintStream;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ConsolaIo {

    private final Scanner scanner;
    private final PrintStream out;

    public String readRequired(final String prompt) {
        String value;
        do {
            out.print(prompt);
            value = scanner.nextLine().trim();

            if (value.isBlank()) {
                out.println("El valor no puede estar vacío. Intente nuevamente.");
            }
        } while (value.isBlank());

        return value;
    }

    public String readOptional(final String prompt) {
        out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(final String prompt) {
        while (true) {
            out.print(prompt);
            final String raw = scanner.nextLine().trim();

            try {
                return Integer.parseInt(raw);
            } catch (final NumberFormatException ignored) {
                out.println("Entrada inválida. Ingrese un número.");
            }
        }
    }

    public void println(final String message) {
        out.println(message);
    }

    public void println() {
        out.println();
    }

    public void printf(
            final String format,
            final Object... args) {

        out.printf(format, args);
    }
}