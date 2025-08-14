package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Usuario;
import io.github.nutria.nutria.util.ConnectionFactory;
import io.github.nutria.nutria.util.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class UsuarioDAO implements IUsuarioDAO {
    ConnectionFactory factory = new ConnectionFactory();

    // Validar se e-mail já está cadastrado
    public boolean emailUsed(String email) {
        try(Connection connection = factory.connect()) {
            String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
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
        try (Connection connect = factory.connect()) {

            // Preparando a query de inserção no banco de dados
            String sql = "INSERT INTO usuarios (nome, email, senha, telefone, empresa, foto) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            String hashedPassword = PasswordHasher.hashPassword(usuario.getSenha());

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, hashedPassword);
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getEmpresa());
            ps.setString(6, usuario.getFoto());

            // Executando a query de inserção
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Usuário cadastrado com sucesso.");
            }

            // Fechando o objeto
//            ps.close();

//            // Encerrando a conexão com o banco
//            connect.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //Método read() para listar todos os usuários do banco
    public List<Usuario> read() {
//        Criamos a lista dinâmica para armazenar os usuário
        List<Usuario> usuarios = new ArrayList<>();

//        Tenta fazer a conexão com o banco
        try (Connection connect = factory.connect()) {
//            Comando de fazer a consulta no banco
            String sql = "SELECT * FROM usuarios";
//            Prepara a consulta para enviar ao banco
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

//            Enquanto o rs tiver usuários como resultado nós vamos criar um usuário e armazenar na lista
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setTelefone(rs.getString("telefone"));
                user.setEmpresa(rs.getString("empresa"));
                user.setFoto(rs.getString("foto"));
//                Adicionamos o usuário à lista
                usuarios.add(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return usuarios;
    }
}