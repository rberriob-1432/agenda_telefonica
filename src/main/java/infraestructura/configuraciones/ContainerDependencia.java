package infraestructura.adaptador.persistencia.configuraciones;
import aplicacion.puertos.entrada.ActualizarContactoCasoUso;
import aplicacion.puertos.entrada.ConseguirContactoPorIdCasoUso;
import aplicacion.puertos.entrada.ConseguirTodosContactosCasoUso;
import aplicacion.puertos.entrada.CrearContactoCasoUso;
import aplicacion.puertos.entrada.EliminarContactoCasoUso;

import aplicacion.servicios.ActualizarContactoServicio;
import aplicacion.servicios.ConseguirContactoPorIdServicio;
import aplicacion.servicios.ConseguirTodosContactosServicio;
import aplicacion.servicios.CrearContactoServicio;
import aplicacion.servicios.EliminarContactoServicio;

import dominio.modelo.Contacto;

import infraestructura.adaptador.persistencia.archivo.ContactoRepositorioTxt;
import infraestructura.configuraciones.PropiedadesApp;
import infraestructura.puntosentrada.cli.AgendaTelefonicaCli;
import infraestructura.puntosentrada.cli.io.ConsolaIo;
import infraestructura.puntosentrada.cli.io.ContactoRespuestaImpresor;
import infraestructura.puntosentrada.cli.manipulador.ActualizarContactoManipulador;
import infraestructura.puntosentrada.cli.manipulador.ConseguirContactoPorIdManipulador;
import infraestructura.puntosentrada.cli.manipulador.CrearContactoManipulador;
import infraestructura.puntosentrada.cli.manipulador.EliminarContactoManipulador;
import infraestructura.puntosentrada.cli.manipulador.ListarContactosManipulador;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Scanner;

public final class ContainerDependencia {

    private final Contacto contacto;
    private final Validator validator;
    private final ContactoRepositorioTxt repositorio;

    public ContainerDependencia() {

        final PropiedadesApp propiedadesApp =
                new PropiedadesApp();

        this.contacto = new Contacto();

        this.validator =
                Validation
                        .buildDefaultValidatorFactory()
                        .getValidator();

        this.repositorio =
                new ContactoRepositorioTxt(
                        propiedadesApp,
                        contacto
                );
    }

    public AgendaTelefonicaCli agendaTelefonicaCli(
            final ConsolaIo consola) {

        final CrearContactoCasoUso crearContactoCasoUso =
                new CrearContactoServicio(
                        repositorio,
                        repositorio,
                        validator,
                        contacto
                );

        final ActualizarContactoCasoUso
                actualizarContactoCasoUso =
                new ActualizarContactoServicio(
                        repositorio,
                        repositorio,
                        repositorio,
                        validator,
                        contacto
                );

        final ConseguirContactoPorIdCasoUso
                conseguirContactoPorIdCasoUso =
                new ConseguirContactoPorIdServicio(
                        repositorio,
                        validator,
                        contacto
                );

        final ConseguirTodosContactosCasoUso
                conseguirTodosContactosCasoUso =
                new ConseguirTodosContactosServicio(
                        repositorio
                );

        final EliminarContactoCasoUso eliminarContactoCasoUso =
                new EliminarContactoServicio(
                        repositorio,
                        repositorio,
                        validator
                );

        final ContactoRespuestaImpresor impresor =
                new ContactoRespuestaImpresor(consola);

        final CrearContactoManipulador crearManipulador =
                new CrearContactoManipulador(
                        crearContactoCasoUso,
                        consola
                );

        final ActualizarContactoManipulador
                actualizarManipulador =
                new ActualizarContactoManipulador(
                        actualizarContactoCasoUso,
                        consola
                );

        final ConseguirContactoPorIdManipulador
                buscarManipulador =
                new ConseguirContactoPorIdManipulador(
                        conseguirContactoPorIdCasoUso,
                        consola,
                        impresor
                );

        final ListarContactosManipulador listarManipulador =
                new ListarContactosManipulador(
                        conseguirTodosContactosCasoUso,
                        consola
                );

        final EliminarContactoManipulador
                eliminarManipulador =
                new EliminarContactoManipulador(
                        eliminarContactoCasoUso,
                        consola
                );

        return new AgendaTelefonicaCli(
                consola,
                listarManipulador,
                buscarManipulador,
                crearManipulador,
                actualizarManipulador,
                eliminarManipulador
        );
    }

    public AgendaTelefonicaCli agendaTelefonicaCli() {

        final Scanner scanner =
                new Scanner(System.in);

        final ConsolaIo consola =
                new ConsolaIo(
                        scanner,
                        System.out
                );

        return agendaTelefonicaCli(consola);
    }
}