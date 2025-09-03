package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Usuario;
import io.github.nutria.nutria.util.ConnectionFactory;
import io.github.nutria.nutria.util.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class UsuarioDAO implements IUsuarioDAO, AutoCloseable {
     /**Classe para transferência de dados do Usuario no banco de dados
     * @author Mariana Marrão, Luis Henrique e Enzo Mota
     * @version 1.05
     */

     // Instanciação do
    private static Connection connect = ConnectionFactory.connect();

    // Validar se e-mail já está cadastrado
    /**
     * Método para validar se o email já está cadastrado no Banco de Dados
     * @param email
     * @return boolean
     * */
    public boolean findByEmailUsed(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";

        try(PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            ps.setString(1, email);

            if(rs.next()) {
                // Se o resultado da função COUNT for maior que 0, significa que o e-mail já está cadastrado
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cadastrar usuario
    public void save(Usuario usuario) {
        // Preparando a query de inserção no banco de dados
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, empresa, foto) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connect.prepareStatement(sql)) {

            String hashedPassword = PasswordHasher.hashPassword(usuario.getSenha());

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, hashedPassword);
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getEmpresa());
            ps.setString(6, usuario.getFoto());

            // Executando a query de inserção
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Método read() para listar todos os usuários do banco
    public List<Usuario> findAll() {
        // Comando de fazer a consulta no banco
        String sql = "SELECT * FROM usuario";

//        Criamos a lista dinâmica para armazenar os usuário
        List<Usuario> usuarioArrayList = new ArrayList<Usuario>();

//        Tenta fazer a conexão com o banco
        //            Prepara a consulta para enviar ao banco
        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {

//            Enquanto o rs tiver usuários como resultado nós vamos criar um usuário e armazenar na lista
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(rs.getLong("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setTelefone(rs.getString("telefone"));
                user.setEmpresa(rs.getString("empresa"));
                user.setFoto(rs.getString("foto"));

//                Adicionamos o usuário à lista
                usuarioArrayList.add(user);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioArrayList;
    }

    public int deleteUserById(long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setLong(1, id);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void close() throws Exception {
        try {
            if (connect == null && !connect.isClosed()) {
                connect.close();
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}