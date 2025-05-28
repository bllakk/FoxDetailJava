package com.FoxEstetica.model;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String placaCarro;


    public Cliente(String nome, String telefone, String placaCarro) {
        this.nome = nome;
        this.telefone = telefone;
        this.placaCarro = placaCarro;
    }
    public Cliente(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringBuilder("Cliente {")
                .append("\n  id = ").append(id)
                .append(",\n  nome = '").append(nome != null ? nome : "N/A").append('\'')
                .append(",\n  telefone = '").append(telefone != null ? telefone : "N/A").append('\'')
                .append(",\n  placaCarro = '").append(placaCarro != null ? placaCarro : "N/A").append('\'')
                .append("\n}")
                .toString();
    }
}
