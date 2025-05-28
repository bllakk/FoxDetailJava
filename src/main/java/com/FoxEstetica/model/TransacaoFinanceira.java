package com.FoxEstetica.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransacaoFinanceira {
    private int id;
    private LocalDate data;
    private String descricao;
    private  double valor;
    private TipoTransacao tipo;

    public TransacaoFinanceira(int id, LocalDate data, String descricao, double valor, TipoTransacao tipo) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TransacaoFinanceira{" +
                "id=" + id +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tipo=" + tipo +
                '}';
    }
}
