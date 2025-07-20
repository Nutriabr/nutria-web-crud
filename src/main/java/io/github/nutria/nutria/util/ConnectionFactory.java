package io.github.nutria.nutria.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    // Carregando as variáveis de ambiente do arquivo .env
    Dotenv dotenv = Dotenv.configure().load();

    // Método para estabelecer a conexão com o banco de dados
    public Connection connect() {
        Connection con = null;
        try {
            final String DRIVER = "org.postgresql.Driver";
            final String URL = dotenv.get("DB_URL");
            final String USER = dotenv.get("DB_USER");
            final String PASSWORD = dotenv.get("DB_PASSWORD");

            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            // Estabelecendo conexão com o banco
            return con;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Método para testar a conexão com o banco de dados
    public void testConnection() {
        try {
            Connection con = connect();
            System.out.println(con);

            // Fechando a conexão com o banco de dados
            con.close();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro na conexão com o Banco de dados: " + e.getMessage());
        }
    }
}
