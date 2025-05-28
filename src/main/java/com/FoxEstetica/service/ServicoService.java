package com.FoxEstetica.service;

import com.FoxEstetica.model.Servico;
import com.FoxEstetica.util.ConexaoDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoService {
    public void cadatrarServico(Servico servico){
        String sql = "INSERT INTO servico (nome, preco) VALUES (?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getNome());
            stmt.setDouble(2, servico.getPreco());
            stmt.executeUpdate();

            System.out.println("Serviço cadastrado com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar serviço: " + e.getMessage());
        }
    }

    public List<Servico> listarServico() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servico";

        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Servico servico = new Servico(
                        rs.getString("nome"),
                        rs.getDouble("preco")
                );
                servicos.add(servico);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }

        return servicos;
    }

    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM servico WHERE idservico = ?";
        Servico servico = null;

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                servico = new Servico(
                        rs.getString("nome"),
                        rs.getDouble("preco")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por ID: " + e.getMessage());
        }

        return servico;
    }

    public void removerServico(int id) {
        String sql = "DELETE FROM servico WHERE idservico = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Serviço removido com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao remover serviço: " + e.getMessage());
        }
    }
}
