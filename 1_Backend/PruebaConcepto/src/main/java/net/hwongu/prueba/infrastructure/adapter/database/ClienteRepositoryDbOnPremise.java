package net.hwongu.prueba.infrastructure.adapter.database;

import net.hwongu.prueba.domain.model.Cliente;
import net.hwongu.prueba.domain.port.out.ClienteRepository;
import java.sql.*;

public class ClienteRepositoryDbOnPremise implements ClienteRepository {

    private final String URL = "jdbc:postgresql://localhost:5432/Db_OnPremise";
    private final String USER = "postgres";
    private final String PASS = "clave";

    @Override
    public void guardar(Cliente cliente) {
        String sql = "INSERT INTO customers (business_name, total_balance) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setDouble(2, cliente.getSaldo());
            stmt.executeUpdate();
            System.out.println("[ONPREMISE] INSERT exitoso en tabla 'customers'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscarPorId(Integer id) {
        System.out.println("--- [ONPREMISE] Conectando a Base de Datos On Premise ---");
        String sql = "SELECT id, business_name, total_balance FROM customers WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("business_name"),
                        rs.getDouble("total_balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
