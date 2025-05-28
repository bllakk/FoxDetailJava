package com.FoxEstetica.model;

public class Servico {
    private int id;
    private String nome;
    private double preco;

    public Servico(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Servico(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringBuilder("Servico {")
                .append("\n  id = ").append(id)
                .append(",\n  nome = '").append(nome != null ? nome : "N/A").append('\'')
                .append(",\n  preco = ").append(preco != 0 ? preco : "N/A")
                .append("\n}")
                .toString();
    }
}
