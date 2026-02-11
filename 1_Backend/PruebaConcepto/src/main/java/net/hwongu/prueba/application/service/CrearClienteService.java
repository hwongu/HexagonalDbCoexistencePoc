package net.hwongu.prueba.application.service;

import net.hwongu.prueba.domain.model.Cliente;
import net.hwongu.prueba.domain.port.out.ClienteRepository;

public class CrearClienteService {

    private final ClienteRepository repositorio;

    public CrearClienteService(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void ejecutar(String nombre, Double saldoInicial) {
        Cliente nuevoCliente = new Cliente(null, nombre, saldoInicial);
        System.out.println("--- Iniciando proceso de alta de cliente ---");
        repositorio.guardar(nuevoCliente);
        System.out.println("--- Proceso finalizado ---");
    }
}
