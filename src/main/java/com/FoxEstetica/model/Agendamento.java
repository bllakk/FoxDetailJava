package com.FoxEstetica.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
    private int id;
    Cliente cliente;
    Servico servico;
    LocalDate data;
    LocalTime hora;

    public Agendamento(int id, Cliente cliente, Servico servico, LocalDate data, LocalTime hora) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.data = data;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return new StringBuilder("Agendamento {")
                .append("\n  id = ").append(id)
                .append(",\n  cliente = ").append(cliente != null ? cliente : "N/A")
                .append(",\n  servico = ").append(servico != null ? servico : "N/A")
                .append(",\n  data = ").append(data != null ? data : "N/A")
                .append(",\n  hora = ").append(hora != null ? hora : "N/A")
                .append("\n}")
                .toString();
    }
}
