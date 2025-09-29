package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.util.ConnectionFactory;

// Importações necessárias para operações com JDBC e manipulação de listas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso a dados (DAO) para a entidade Receita.
 * Implementa as operações CRUD (Create, Read, Update, Delete) para a tabela "receitas" no banco de dados.
 * @author marianamarrao
 * @version 1.1
 */
public class ReceitaDAO implements GenericDAO<Receita, Long>, AutoCloseable {
    private static final Connection connect = ConnectionFactory.connect();

    // Método para inserir (create) ou atualizar (update)
//    public void save(Receita receita) {
//        try (Connection connect = factory.connect()) {
//            if (receita.getId() == 0) {
//                // Query de inserção no banco de dados
//                String sql = "INSERT INTO receitas (nome, porcao, id_Produto)" +
//                        "VALUES (?,?,?)";
//
//                PreparedStatement ps = connect.prepareStatement(sql);
//                ps.setString(1, receita.getNome());
//                ps.setString(2, receita.getPorcao());
//                ps.setLong(3, receita.getIdProduto());
//
//                // Executando o query de inserção
//                int result = ps.executeUpdate();
//                if (result > 0) {
//                    System.out.println("Receita cadastrado com sucesso.");
//                }
//            } else {
//
//                String sql = "UPDATE receitas SET nome = ?, porcao = ?, idProduto = ?" +
//                        "WHERE id = ?";
//
//                PreparedStatement ps = connect.prepareStatement(sql);
//
//                ps.setString(1, receita.getNome());
//                ps.setString(2, receita.getPorcao());
//                ps.setLong(3, receita.getIdProduto());
//
//                // Condição do WHERE
//                ps.setLong(4, receita.getId());
//
//                int result = ps.executeUpdate();
//
//                if (result > 0) {
//                    System.out.println("Receita atualizado com sucesso.");
//                } else {
//                    System.out.println("Nenhuma receita encontrada para atualizar.");
//                }
//
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao cadastrar receita: " + e.getMessage());
//        }
//    }


    @Override
    /**
     * Método para inserir uma nova receita no banco de dados
     * @param receita Objeto Receita a ser inserido
     * @return boolean Retorna true se a receita foi inserida com sucesso, false caso contrário
     * @author marianamarrao
     * @throws RuntimeException Em caso de erro na operação SQL
     */
    public boolean insert(Receita receita) {
        // Query de inserção no banco de dados
        String sql = "INSERT INTO receitas (nome, porcao, id_produto) VALUES (?, ?, ?)";

        // 1. Usar try-with-resources para garantir que o PreparedStatement seja fechado
        try (PreparedStatement ps = connect.prepareStatement(sql)) {

            // 2. Setar os parâmetros da query com os valores do objeto Receita
            ps.setString(1, receita.getNome());
            ps.setString(2, receita.getPorcao());
            ps.setLong(3, receita.getIdProduto());

            // 3. Executando o query de inserção
            int result = ps.executeUpdate();

            // 4. Verificar se a inserção foi realizada com sucesso
            if (result > 0) {
                System.out.println("Receita cadastrada com sucesso.");
                return true;
            } else {
                System.out.println("Falha ao cadastrar a receita.");
                return false;
            }

        } catch (SQLException e) {
            // 5. Tratar exceções SQL lançando uma RuntimeException
            throw new RuntimeException("Erro ao cadastrar receita: " + e.getMessage());
        }
    }

    /** Método para listar todas as receitas no banco de dados
    * @return List<Receita> Retorna uma lista de objetos Receita
    * @author marianamarrao
    * @throws SQLException Lança uma exceção SQL em caso de erro na consulta
    * */
    @Override
    public List<Receita> findAll() {
        // Lista para armazenar os objetos Receita retornados do banco de dados
        List<Receita> receitas = new ArrayList<>();

        // Query SQL para selecionar todas as receitas
        String sql = "SELECT * FROM receitas";

        // 1. Usar try-with-resources para garantir que as conexões sejam fechadas
        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // 2. Iterar sobre o ResultSet para criar objetos Receita
            while (rs.next()) {
                Receita receita = new Receita();

                // 3. Preencher os atributos do objeto Receita com os dados do ResultSet
                receita.setId(rs.getLong("id"));
                receita.setNome(rs.getString("nome"));
                receita.setPorcao(rs.getString("porcao"));
                receita.setIdProduto(rs.getLong("id_produto"));

                // 4. Adicionar o objeto Receita à lista
                receitas.add(receita);
            }
        } catch (SQLException e) {
            // 4. Tratar exceções SQL
            e.printStackTrace();
        }
        // 5. Retornar a lista de receitas
        return receitas;
    }

    /** Método para deletar uma receita pelo ID
     * @param id ID da receita a ser deletada
     * @return boolean Retorna true se a receita foi deletada com sucesso, false caso contrário
     * @author marianamarrao
     * @throws SQLException Lança uma exceção SQL em caso de erro na operação
     * */
    @Override
    public boolean deleteById(Long id) {
        // Inicializar variável com a query de delete do SQL
        String sql = "DELETE FROM receitas WHERE id = ?";

        // 1. Usar try-with-resources para garantir que as conexões sejam fechadas
        try (Connection connect = ConnectionFactory.connect();
             PreparedStatement ps = connect.prepareStatement(sql)) {

            ps.setLong(1, id);

            // 2. Retornar true se o número de linhas afetadas for maior que 0 e false caso contrário
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            // 3. Tratar exceções SQL
            e.printStackTrace();
            return false;
        }
    }

    /** Método para fechar a conexão com o banco de dados
     * @throws SQLException Lança uma exceção SQL em caso de erro ao fechar a conexão
     * */
    @Override
    public void close() throws SQLException {
        if (connect != null && !connect.isClosed()) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
