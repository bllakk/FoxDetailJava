package com.FoxEstetica.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = Dotenv.load().get("DB_URL");
    private static final String USUARIO = Dotenv.load().get("DB_USER");
    private static final String SENHA = Dotenv.load().get("DB_PASS");

    public static Connection conectar(){
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e){
            System.out.println("Erro ao conectar ao MySQL: " + e.getMessage());
            return null;
        }
    }
}
