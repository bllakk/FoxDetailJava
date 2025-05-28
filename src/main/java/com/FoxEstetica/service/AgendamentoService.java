package com.FoxEstetica.service;

import com.FoxEstetica.model.Agendamento;
import com.FoxEstetica.model.Cliente;
import com.FoxEstetica.model.Servico;
import com.FoxEstetica.util.ConexaoDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoService {
    public void agendarServico(Agendamento agendamento) {
        String sql = "INSERT INTO agendamento (idcliente, idservico, data, hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, agendamento.getCliente().getId());
            stmt.setInt(2, agendamento.getServico().getId());
            stmt.setDate(3, Date.valueOf(agendamento.getData()));
            stmt.setTime(4, Time.valueOf(agendamento.getHora()));

            stmt.executeUpdate();
            System.out.println("Serviço agendado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao agendar serviço: " + e.getMessage());
        }
    }

    public List<Agendamento> listarAgendamentosPorData(LocalDate data) {
        List<Agendamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM agendamento WHERE data = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(data));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int clienteId = rs.getInt("idcliente");
                int servicoId = rs.getInt("idservico");

                Cliente cliente = new Cliente(clienteId);
                Servico servico = new Servico(servicoId);

                LocalDate dataAgendamento = rs.getDate("data").toLocalDate();
                LocalTime hora = rs.getTime("hora").toLocalTime();

                Agendamento agendamento = new Agendamento(0, cliente, servico, dataAgendamento, hora);
                lista.add(agendamento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar agendamentos: " + e.getMessage());
        }

        return lista;
    }

    public void cancelarAgendamento(int id) {
        String sql = "DELETE FROM agendamento WHERE id = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Agendamento cancelado.");
            } else {
                System.out.println("Nenhum agendamento com esse ID.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cancelar agendamento: " + e.getMessage());
        }
    }
}
