package io.github.nutria.nutria.util;

import io.github.nutria.nutria.exceptions.ConfigurationException;
import io.github.nutria.nutria.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Classe responsável por estabelecer e fechar a conexão com o banco de dados
 * @author luismedeiros-ieg
 * @version 1.1
 * */
public class ConnectionFactory {

    private static final String DRIVER = "org.postgresql.Driver";

    /**
     * Método para estabelecer conexão com o banco de dados com base no Driver e informações de
     * Host do banco
     *
     * @return Connection - Retorna a conexão estabelecida
     * @throws DatabaseConnectionException se falhar ao conectar
     * @throws ConfigurationException se variáveis de ambiente não estiverem configuradas
     * @author luismedeiros-ieg
     */
    public static Connection conectar() {
        try {
            // Utilizando as variáveis de ambiente configuradas no .env com as informações sensíveis do banco de dados
            final String URL =  System.getenv("DB_URL");
            final String USER = System.getenv("DB_USER");
            final String PASSWORD = System.getenv("DB_PASSWORD");

            if (URL == null || URL.isBlank()) {
                throw new ConfigurationException(
                        "Variável de ambiente DB_URL não está configurada. " +
                                "Configure o arquivo .env com as credenciais do banco de dados."
                );
            }

            if (USER == null || USER.isBlank()) {
                throw new ConfigurationException(
                        "Variável de ambiente DB_USER não está configurada. " +
                                "Configure o arquivo .env com as credenciais do banco de dados."
                );
            }

            if (PASSWORD == null) {
                throw new ConfigurationException(
                        "Variável de ambiente DB_PASSWORD não está configurada. " +
                                "Configure o arquivo .env com as credenciais do banco de dados."
                );
            }

            // Carrega o driver
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                throw new ConfigurationException(
                        "Driver PostgreSQL não encontrado. Adicione a dependência postgresql no pom.xml",
                        e
                );
            }

            // Tentativa de estabelecer conexão com a URL do banco de dados fornecida.
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection == null) {
                throw new DatabaseConnectionException(
                        "Conexão retornou null",
                        new SQLException("Connection is null")
                );
            }

            return connection;

        } catch (SQLException e) {
            System.err.println("[DB ERROR] Falha ao conectar com banco de dados");
            e.printStackTrace(System.err);

            // Mensagens específicas para erros comuns
            String mensagem = "Erro ao conectar com o banco de dados";

            if (e.getMessage().contains("password authentication failed")) {
                mensagem = "Usuário ou senha incorretos";
            } else if (e.getMessage().contains("Connection refused")) {
                mensagem = "Banco de dados não está acessível. Verifique se o PostgreSQL está rodando";
            } else if (e.getMessage().contains("database") && e.getMessage().contains("does not exist")) {
                mensagem = "Banco de dados não existe. Verifique a URL de conexão";
            }

            throw new DatabaseConnectionException(mensagem, e);
        }
    }

    /**
     * Método para fechar a conexão com o banco de dados
     *
     * @param connection Conexão a ser fechada
     * @author luismedeiros-ieg
     */
    public static void desconectar(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("[DB ERROR] Erro ao fechar conexão");
            e.printStackTrace(System.err);
        }
    }
}