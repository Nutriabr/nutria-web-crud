package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IUsuarioDAO;
import io.github.nutria.nutria.model.Usuario;
import io.github.nutria.nutria.util.ConnectionFactory;
import io.github.nutria.nutria.util.PasswordHasher;

// Importações necessárias para operações com JDBC e manipulação de listas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**Classe para transferência de dados do Usuario no banco de dados
 * @author Mariana Marrão, Luis Henrique e Enzo Mota
 * @version 1.1
 * @see IUsuarioDAO
 */
public class UsuarioDAO implements GenericDAO<Usuario, Long>, IUsuarioDAO {
    Connection connect = ConnectionFactory.connect();

    /**
     * Método para validar se o email já está cadastrado no Banco de Dados
     * @param email Recebe como o parametro o email que será usado na clausura where na query
     * @return boolean Retorna <i>true</i> caso a quantidade de linhas seja maior que 0 (já existe um email com
     * o do parametro passado ou <i>false</i> caso a quantidade linha seja menor que 0 (Não existe um email como o do
     * parametro passado).;
     * */
    @Override
    public boolean findByEmailUsed(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";

        boolean result = false;
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, email);
            // 2. Executar a consulta
            try (ResultSet rs = ps.executeQuery()) {

                // 3. Verificar se há linhas encontradas
                if (rs.next()) {
                    /* 4. Se o resultado da função COUNT for maior que 0, significa que
                     *  o e-mail já está cadastrado
                     * */
                    result = rs.getInt(1) > 0;
                }
            }

            ps.close();
        } catch (SQLException e) {
            // 5. Tratar exceções de SQL
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.desconnect(connect);
        }
        return result;

    }
    /**
     * Método para inserir um novo usuário no Banco de Dados
     * @param usuario Recebe como o parametro o objeto usuario que será inserido no banco de dados
     * @return boolean Retorna <i>true</i> se o número de linhas afetadas foi maior que 0 ou <i>false</i> se o
     * número de linhas afetadas for igual ou menor que 0.
     * */
    public boolean insert(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, empresa, foto) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        boolean result = false;

        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            /* 2. Inicializa uma variável hashedPassword que recebe o retorno método hashPassword
            * o método recebe como parametro o atributo de senha do objeto usuario
             */
            String hashedPassword = PasswordHasher.hashPassword(usuario.getSenha());

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            // Verificação se o email já está em uso
            if (findByEmailUsed(usuario.getEmail())) {
                return false;
            }
            ps.setString(3, hashedPassword);
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getEmpresa());
            ps.setString(6, usuario.getFoto());

           // 3. Validação sobre o número de linhas afetadas, com atribuição de result para true.
            if (ps.executeUpdate() > 0) {
                result = true;
            }
            ps.close();

        } catch (SQLException e) {
            // 4. Tratar exceções SQL
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.desconnect(connect);
        }
        // 5. Retornar o resultado da validação
        return result;
    }

    public Usuario findUsuarioById(long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        boolean result = false;
        Usuario user = null;

        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new Usuario();
                    user.setId(rs.getLong("id"));
                    user.setNome(rs.getString("nome"));
                    user.setEmail(rs.getString("email"));
                    user.setSenha(rs.getString("senha"));
                    user.setTelefone(rs.getString("telefone"));
                    user.setEmpresa(rs.getString("empresa"));
                    user.setFoto(rs.getString("foto"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.desconnect(connect);
        }

        return user;
    }

    /**
     * Método para listar todos os usuários do Banco de Dados
     * @return ArrayList Retorna um <i>ArrayList</i> com os usuarios registrados no banco de dados
     * */
    public List<Usuario> findAll() {
        // 1. Inicializar variável com a consulta SQL
        String sql = "SELECT * FROM usuario";

        // 2. Instanciar uma lista para armazenar os usuarios retornados da consulta
        List<Usuario> usuarioArrayList = new ArrayList<Usuario>();
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

                /* 4. Enquanto o ResultSet tiver usuarios como resultado
                 * é instanciado um novo usuario e armazenado no usuarioArrayList
                 * */
                while (rs.next()) {
                    Usuario user = new Usuario();
                    user.setId(rs.getLong("id"));
                    user.setNome(rs.getString("nome"));
                    user.setEmail(rs.getString("email"));
                    user.setSenha(rs.getString("senha"));
                    user.setTelefone(rs.getString("telefone"));
                    user.setEmpresa(rs.getString("empresa"));
                    user.setFoto(rs.getString("foto"));

                // 5. O usuario é armazenado na lista
                    usuarioArrayList.add(user);
                }
                ps.close();
                rs.close();
        } catch (SQLException e) {
            // 5. Tratar exceções SQL
            e.printStackTrace();
        } finally {
            ConnectionFactory.desconnect(connect);
        }
        return usuarioArrayList;
    }

    public boolean update(Usuario usuario) {
        StringBuilder sql = new StringBuilder("UPDATE usuario SET ");
        List<Object> parameters = new ArrayList<>();

        if (usuario.getNome() != null) {
            sql.append("nome = ?, ");
            parameters.add(usuario.getNome());
        }

        if (usuario.getEmail() != null) {
            sql.append("email = ?");
            if (findByEmailUsed(usuario.getEmail())) {
                return false;
            }
            parameters.add(usuario.getEmail());
        }

        if (usuario.getEmpresa() != null) {
            sql.append("empresa = ?, ");
            parameters.add(usuario.getEmpresa());
        }

        if (usuario.getSenha() != null) {
            String hashedPassword = PasswordHasher.hashPassword(usuario.getSenha());
            sql.append("senha = ?, ");
            parameters.add(hashedPassword);
        }

        if (usuario.getFoto() != null) {
            sql.append("foto = ?, ");
            parameters.add(usuario.getFoto());
        }

        if (parameters.isEmpty()) {
            return false;
        }

        // Remove a última vírgula e espaço
        sql.setLength(sql.length() - 1);

        sql.append("WHERE id = ?");
        parameters.add(usuario.getId());

        boolean result = false;
        try {
            PreparedStatement ps = connect.prepareStatement(sql.toString());

            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i+1, parameters.get(i));
            }
            result = ps.executeUpdate() > 0;
            ps.close();

            return result;

        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.desconnect(connect);
        }
    }

    /**
     * Método para deletar usuário no Banco de Dados pelo seu id
     * @param id Recebe como o parametro o id do usuario que será deletado
     * @return boolean - Retorna <i>true</i> se o número de linhas afetadas foi maior que 0 ou <i>false</i> se o
     * número de linhas afetadas for igual ou menor que 0.
     * */
    public boolean deleteById(Long id) {
        // 1. Inicializar variável com a query de delete do SQL
        String sql = "DELETE FROM usuario WHERE id = ?";

        boolean result = false;

        try (
                PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setLong(1, id);

            // 3. Validação sobre o número de linhas afetadas, com atribuição de result para true.
            result = ps.executeUpdate() > 0;
            ps.close();

            return result;
        } catch (SQLException e) {
            // 4. Tratar exceções SQL
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.desconnect(connect);
        }
    }
}