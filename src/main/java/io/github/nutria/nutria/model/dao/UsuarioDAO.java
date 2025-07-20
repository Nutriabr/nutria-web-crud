package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Usuario;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    ConnectionFactory factory = new ConnectionFactory();

    // Validar se e-mail já está cadastrado
    public boolean emailUsed(String email) {
        try {
            Connection connection = factory.connect();

            String sql = "SELECT COUNT(*) FROM usuario WHERE endereco_email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                // Se o resultado da função COUNT for maior que 0, significa que o e-mail já está cadastrado
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao validar o e-mail: " + e.getMessage());
        }
        return false;
    }

    // Cadastrar usuario
    public void insertUser(Usuario usuario) {
        try {
            Connection connect = factory.connect();

            // Preparando a query de inserção no banco de dados
            String sql = "INSERT INTO usuario (nome_completo, endereco_email, senha, telefone, empresa, foto) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, usuario.getNomeCompleto());
            ps.setString(2, usuario.getEnderecoEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getEmpresa());
            ps.setString(6, usuario.getFoto());

            // Executando a query de inserção
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Usuário cadastrado com sucesso.");
            }

            // Fechando o objeto
            ps.close();

            // Encerrando a conexão com o banco
            connect.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
