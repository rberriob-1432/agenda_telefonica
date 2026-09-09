package agendatelefonica.src.main;

import infraestructura.adaptador.persistencia.configuraciones.ContainerDependencia;

public final class Main {

    private Main() {
    }

    public static void main(final String[] args) {

        final ContainerDependencia container =
                new ContainerDependencia();

        container.agendaTelefonicaCli().start();
    }
}