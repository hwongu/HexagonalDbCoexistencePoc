package net.hwongu.prueba.domain.port.out;

import net.hwongu.prueba.domain.model.Cliente;

public interface ClienteRepository {

    void guardar(Cliente cliente);

    Cliente buscarPorId(Integer id);

}