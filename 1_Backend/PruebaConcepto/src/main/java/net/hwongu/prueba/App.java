package net.hwongu.prueba;

import net.hwongu.prueba.application.service.ConsultaSaldoService;
import net.hwongu.prueba.application.service.CrearClienteService;
import net.hwongu.prueba.domain.port.out.ClienteRepository;
import net.hwongu.prueba.infrastructure.adapter.database.ClienteRepositoryDbCloud;
import net.hwongu.prueba.infrastructure.adapter.database.ClienteRepositoryDbOnPremise;
import net.hwongu.prueba.infrastructure.adapter.mock.ClienteRepositoryMock;

import java.util.Scanner;

public class App {


    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            ClienteRepository repositorio = seleccionarRepositorio(opcion);
            if (repositorio != null) {
                // Si la opción es 1, 2 o 3, es una INSERT
                if (opcion >= 1 && opcion <= 3) {
                    procesarInsercion(repositorio);
                }
                // Si la opción es 4, 5 o 6, es una CONSULTA
                else if (opcion >= 4 && opcion <= 6) {
                    procesarConsulta(repositorio);
                }
            } else if (opcion != 7) {
                System.out.println("Opción no válida.");
            }

        } while (opcion != 7);

        System.out.println("Aplicación finalizada.");
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU OPCIONES ---");
        System.out.println("1. Insertar Cliente (OnPremise)");
        System.out.println("2. Insertar Cliente (Cloud)");
        System.out.println("3. Insertar Cliente (Mock)");
        System.out.println("4. Consultar Saldo (OnPremise)");
        System.out.println("5. Consultar Saldo (Cloud)");
        System.out.println("6. Consultar Saldo (Mock)");
        System.out.println("7. Salir");
    }


    private static ClienteRepository seleccionarRepositorio(int opcion) {
        switch (opcion) {
            case 1:
            case 4:
                return new ClienteRepositoryDbOnPremise();
            case 2:
            case 5:
                return new ClienteRepositoryDbCloud();
            case 3:
            case 6:
                return new ClienteRepositoryMock();
            default:
                return null;
        }
    }


    private static void procesarInsercion(ClienteRepository repositorio) {
        System.out.println("\n--- NUEVO CLIENTE ---");
        String nombre = leerTexto("Ingrese nombre (Razón Social): ");
        Double saldo = leerDouble("Ingrese saldo inicial: ");
        CrearClienteService servicio = new CrearClienteService(repositorio);
        servicio.ejecutar(nombre, saldo);
    }

    private static void procesarConsulta(ClienteRepository repositorio) {
        System.out.println("\n--- CONSULTA DE SALDO ---");
        int id = leerEntero("Ingrese ID de Cliente: ");
        ConsultaSaldoService servicio = new ConsultaSaldoService(repositorio);
        servicio.ejecutarConsulta(id);
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            sc.next();
            System.out.print(mensaje);
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    private static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextDouble()) {
            System.out.println("Por favor, ingrese un monto válido (ej. 1500.50).");
            sc.next();
            System.out.print(mensaje);
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }
}