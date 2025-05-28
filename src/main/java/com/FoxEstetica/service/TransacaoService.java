package com.FoxEstetica.service;

import com.FoxEstetica.model.TransacaoFinanceira;
import com.FoxEstetica.model.TipoTransacao;
import com.FoxEstetica.util.ConexaoDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacaoService {

    public void cadastrarTransacao(TransacaoFinanceira transacao) {
        String sql = "INSERT INTO transacao (data, descricao, valor, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(transacao.getData()));
            stmt.setString(2, transacao.getDescricao());
            stmt.setDouble(3, transacao.getValor());
            stmt.setString(4, transacao.getTipo().name());

            stmt.executeUpdate();
            System.out.println("Transação cadastrada com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar transação: " + e.getMessage());
        }
    }

    public List<TransacaoFinanceira> listarTransacoes() {
        List<TransacaoFinanceira> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacao";

        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TransacaoFinanceira transacao = new TransacaoFinanceira(
                        rs.getInt("idtransacao"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        TipoTransacao.valueOf(rs.getString("tipo"))
                );
                transacoes.add(transacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar transações: " + e.getMessage());
        }
        return transacoes;
    }

    public void removerTransacao(int id) {
        String sql = "DELETE FROM transacao WHERE id = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Transação removida com sucesso.");
            } else {
                System.out.println("Nenhuma transação com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao remover transação: " + e.getMessage());
        }
    }
}