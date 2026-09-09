package aplicacion.servicios;

import aplicacion.puertos.entrada.ExportarContactosCasoUso;
import aplicacion.puertos.salida.ConseguirTodosContactosPuerto;
import aplicacion.puertos.salida.ExportarContactosPuerto;
import dominio.modelo.Contacto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ExportarContactosServicio
        implements ExportarContactosCasoUso {

    private final ConseguirTodosContactosPuerto
            conseguirTodosContactosPuerto;

    private final ExportarContactosPuerto
            exportarContactosPuerto;

    @Override
    public void execute() {

        final Contacto contacto =
                conseguirTodosContactosPuerto.getAll();

        exportarContactosPuerto.exportar(contacto);
    }
}