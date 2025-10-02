package io.github.nutria.nutria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Classe responsável por estabelecer e fechar a conexão com o banco de dados
 * @author luismedeiros-ieg
 * @version 1.1
 * */
public class ConnectionFactory {

    /** Método para estabelecer conexão com o banco de dados com base no Driver e informações de
     * Host do banco
     * @author luismedeiros-ieg
     * @return conn - Retorna a conexão do DriverManager
     * */
    public static Connection connect() {
        Connection con = null;
        try {

            // Utilizando as variáveis de ambiente configuradas no .env com as informações sensíveis do banco de dados
            final String DRIVER = "org.postgresql.Driver";
            final String URL = System.getenv("DB_URL");
            final String USER = System.getenv("DB_USER");
            final String PASSWORD = System.getenv("DB_PASSWORD");

            Class.forName(DRIVER);

            // Tentativa de estabelecer conexão com a URL do banco de dados fornecida.
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void disconnect(Connection connection){
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}