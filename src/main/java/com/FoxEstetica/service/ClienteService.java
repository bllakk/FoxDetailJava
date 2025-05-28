package com.FoxEstetica.service;

import com.FoxEstetica.model.Cliente;
import com.FoxEstetica.util.ConexaoDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    public void cadatrarClientes(Cliente cliente){
        String sql = "INSERT INTO cliente (nome, telefone, placaCarro) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getPlacaCarro());
            stmt.executeUpdate();

            System.out.println("Cliente cadastrado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("placaCarro")
                );
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }

        return clientes;
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE idcliente = ?";
        Cliente cliente = null;

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("placaCarro")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por ID: " + e.getMessage());
        }

        return cliente;
    }

    public void removerCliente(int id) {
        String sql = "DELETE FROM cliente WHERE idcliente = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Cliente removido com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao remover cliente: " + e.getMessage());
        }
    }
}
