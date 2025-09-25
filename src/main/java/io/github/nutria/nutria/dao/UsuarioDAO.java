package io.github.nutria.nutria.dao;

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
public class UsuarioDAO implements GenericDAO<Usuario, Long>, IUsuarioDAO, AutoCloseable {

     // Instanciação do objeto connect
    private static final Connection connect = ConnectionFactory.connect();

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

        System.out.println(email);
        boolean result = false;
        // 1. Usar try-with-resources para garantir que as conexões sejam fechadas
        try(PreparedStatement ps = connect.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            // 5. Tratar exceções de SQL
            e.printStackTrace();
            return false;
        } finally {
            return result;
        }
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

        // 1. Usar try-with-resources para garantir que as conexões sejam fechadas
        try (PreparedStatement ps = connect.prepareStatement(sql)) {

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

        } catch (SQLException e) {
            // 4. Tratar exceções SQL
            e.printStackTrace();
            return false;
        }
        // 5. Retornar o resultado da validação
        return result;
    }


    /**
     * Método para listar todos os usuários do Banco de Dados
     * @return ArrayList Retorna um <i>ArrayList</i> com os usuarios registrados no banco de dados
     * */
    public List<Usuario> findAll(int page) {
        // 1. Setar em qual página inicia o crud de usuários que será = 1 // O limite de usuário por páginas do .jsp // A partir de qual registro começarão os registros da próxima página
        int limite = 4;
        int offset = (page - 1) * limite;

        // 2. Inicializar variável com a consulta SQL
        String sql = "SELECT * FROM usuario LIMIT ? OFFSET ?";

        // 3. Instanciar uma lista para armazenar os usuarios retornados da consulta
        List<Usuario> usuarioArrayList = new ArrayList<Usuario>();

        // 4. Usar try-with-resources para garantir que as conexões sejam fechadas
        try (Connection connection = ConnectionFactory.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            /* 5. Setando os parâmetros passados no método para a instrução SQL
             * */
            ps.setInt(1, limite);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {

                /* 6. Enquanto o ResultSet tiver usuarios como resultado
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

                // 7. O usuario é armazenado na lista
                    usuarioArrayList.add(user);

                }
            }
        } catch (SQLException e) {
            // 8. Tratar exceções SQL
            e.printStackTrace();
        }
        // 9. Retornar a lista de receitas
        return usuarioArrayList;
    }

    /**
     * Método para contar todos os usuários do Banco de Dados
     * Esse método será utilizado para mostrar a quantidade de usuários no banco antes de mostrar a tabela.
     * E como o método seguinte usará LIMIT E OFFSET não terá como guardar o total.
     * @return ArrayList Retorna um inteiro com o total de usuarios registrados no banco de dados
     * */
    public int countAll() {
        // 1. Inicializar a variavel responsável por armazenar a instrução sql
        String sql = "SELECT COUNT(*) FROM usuario";

        // 2. Usar try-with-resources para garantir que as conexões sejam fechadas
        try (Connection connection = ConnectionFactory.connect()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            /* 3. Enquanto o ResultSet tiver usuarios como resultado
             * é incrementado +1 no countUsuarios
             * */
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            // 6. Tratar exceções SQL
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Esse método será uma atualização geral para o usuário, ou seja, ele receberá todos os parâmetros e o que preicisar ser trocado será alterado aqui.
     * @param nome, email, senha, telefone, foto
     * @return boolean
     * */
    public boolean update(Long id, String nome, String email, String senha, String telefone, String empresa, String foto) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, telefone = ?, foto = ?, empresa = ? WHERE id = ?";
        int rs = 0;

        try (Connection con = ConnectionFactory.connect()) {
            //Preparamos a instrção para o banco e logo em seguida setamos os valores
            PreparedStatement pstmt = connect.prepareStatement(sql);

            //Setando os valores
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, senha);
            pstmt.setString(4, telefone);
            pstmt.setString(5, empresa);
            pstmt.setString(6, foto);

            //Executando a instrução no banco
            rs = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
            //Verificamos se alguma linha do banco foi afetada, caso tenha sido a quantidade será representada no retorno do resultSet
        if (rs > 0) {
            return true;
        } else {
            return false;
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

        // 2. Usar try-with-resources para garantir que as conexões sejam fechadas
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setLong(1, id);

            // 3. Validação sobre o número de linhas afetadas, com atribuição de result para true.
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            // 4. Tratar exceções SQL
            e.printStackTrace();
            return false;
        } finally {
            return result;
        }
    }

    /**
     * Método para fechar a conexão com o banco de dados
     * */
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