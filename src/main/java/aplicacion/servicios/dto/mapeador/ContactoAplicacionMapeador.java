package aplicacion.servicios.dto.mapeador;
import aplicacion.servicios.dto.comando.AgregarContactoComando;
import aplicacion.servicios.dto.comando.ActualizarContactoComando;
import aplicacion.servicios.dto.comando.EliminarContactoComando;
import aplicacion.servicios.dto.query.ConseguirContactoPorId;
import dominio.ov.Correo;
import dominio.ov.Id;
import dominio.ov.Nombre;
import dominio.ov.Telefono;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContactoAplicacionMapeador {

    public Id fromGetByIdQueryToId(
            final ConseguirContactoPorId query) {

        return new Id(query.id());
    }

    public Id fromDeleteCommandToId(
            final EliminarContactoComando comando) {

        return new Id(comando.id());
    }

    public Nombre fromCreateCommandToNombre(
            final AgregarContactoComando comando) {

        return new Nombre(comando.nombre());
    }

    public Telefono fromCreateCommandToTelefono(
            final AgregarContactoComando comando) {

        return new Telefono(comando.telefono());
    }

    public Correo fromCreateCommandToCorreo(
            final AgregarContactoComando comando) {

        return new Correo(comando.correo());
    }

    public Id fromUpdateCommandToId(
            final ActualizarContactoComando comando) {

        return new Id(comando.id());
    }

    public Nombre fromUpdateCommandToNombre(
            final ActualizarContactoComando comando) {

        return new Nombre(comando.nombre());
    }

    public Correo fromUpdateCommandToCorreo(
            final ActualizarContactoComando comando) {

        return new Correo(comando.correo());
    }
}

