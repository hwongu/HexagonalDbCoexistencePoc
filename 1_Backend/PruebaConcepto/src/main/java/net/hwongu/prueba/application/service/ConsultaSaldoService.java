package net.hwongu.prueba.application.service;

import net.hwongu.prueba.domain.port.out.ClienteRepository;
import net.hwongu.prueba.domain.model.Cliente;

public class ConsultaSaldoService {

    private final ClienteRepository repositorio;

    public ConsultaSaldoService(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Cliente ejecutarConsulta(Integer idCliente) {
        Cliente cliente = repositorio.buscarPorId(idCliente);
        if (cliente != null) {
            System.out.println("Consulta Exitosa:");
            System.out.println(cliente);
        } else {
            System.out.println("Error: Cliente no encontrado en la base de datos.");
        }
        return cliente;
    }

}
