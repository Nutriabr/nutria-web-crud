package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.model.Usuario;
import io.github.nutria.nutria.util.ConnectionFactory;

// Importações necessárias para operações com JDBC e manipulação de listas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
public class ReceitaDAO implements GenericDAO<Receita, Long> {
    private static final Connection connect = ConnectionFactory.connect();
    @Override
    /**
     * Método para inserir uma nova receita no banco de dados
     * @param receita Objeto Receita a ser inserido
     * @return boolean Retorna true se a receita foi inserida com sucesso, false caso contrário
     * @author marianamarrao
     * @throws RuntimeException Em caso de erro na operação SQL
     */
    public boolean insert(Receita receita) {
        // 1. Definir a query de inserção no banco de dados
        String sql = "INSERT INTO receitas (nome, porcao, id_produto) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 2. Criar o PreparedStatement para preparar a query
            ps = connect.prepareStatement(sql);

            // 3. Definir os parâmetros da query com os valores do objeto Receita
            ps.setString(1, receita.getNome());
            ps.setString(2, receita.getPorcao());
            ps.setLong(3, receita.getIdProduto());

            // 4. Executar a query de inserção
            int result = ps.executeUpdate();

            // 5. Verificar se a inserção foi realizada com sucesso
            if (result > 0) {
                System.out.println("Receita cadastrada com sucesso.");
                return true;
            } else {
                System.out.println("Falha ao cadastrar a receita.");
                return false;
            }

        } catch (SQLException e) {
            // 6. Tratar possíveis exceções SQL
            e.printStackTrace();
            return false;
        } finally {
            try {
                // 7. Fechar ResultSet, PreparedStatement e conexão, se existirem
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /** Método para listar todas as receitas no banco de dados
    * @return List<Receita> Retorna uma lista de objetos Receita
    * @author marianamarrao
    * @throws SQLException Lança uma exceção SQL em caso de erro na consulta
    * */
    @Override
    public List<Receita> findAll(int page) {
        // 1. Definir limite de registros por página e calcular o offset
        int limite = 4;
        int offset = (page - 1) * limite;

        // 2. Query SQL para buscar receitas com paginação
        String sql = "SELECT * FROM receitas LIMIT ? OFFSET ?";

        // 3. Lista para armazenar os resultados
        List<Receita> receitasArrayList = new ArrayList<>();

        // 4. Objetos auxiliares para manipular a query e os resultados
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 5. Preparar a query e setar parâmetros
            ps = connect.prepareStatement(sql);
            ps.setInt(1, limite);
            ps.setInt(2, offset);

            // 6. Executar a query
            rs = ps.executeQuery();

            // 7. Iterar sobre o ResultSet e criar objetos Receita
            while (rs.next()) {
                Receita receita = new Receita();

                // 8. Preencher os atributos do objeto Receita com os dados do ResultSet
                receita.setId(rs.getLong("id"));
                receita.setNome(rs.getString("nome"));
                receita.setPorcao(rs.getString("porcao"));
                receita.setIdProduto(rs.getLong("id_produto"));

                // 9. Adicionar a Receita à lista
                receitasArrayList.add(receita);
            }
        } catch (SQLException e) {
            // 10. Tratar exceções SQL
            e.printStackTrace();
        } finally {
            // 11. Fechar ResultSet, PreparedStatement e conexão
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 12. Retornar a lista de receitas
        return receitasArrayList;
    }


    /**
     * Método para contar todos os usuários do Banco de Dados
     * Esse método será utilizado para mostrar a quantidade de usuários no banco antes de mostrar a tabela.
     * E como o método seguinte usará LIMIT E OFFSET não terá como guardar o total.
     * @return inteiro com a quantidade de registros na tabela
     * @author enzomota
     * @return ArrayList Retorna um inteiro com o total de usuarios registrados no banco de dados
     * */
    public int countAll() {
        // 1. Variável para armazenar a quantidade total de receitas
        int totalReceitas = 0;

        // 2. Query SQL para contar todas as receitas
        String sql = "SELECT COUNT(*) FROM receitas";

        // 3. Objetos auxiliares para executar a query
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 4. Criar o Statement e executar a query
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            // 5. Obter o resultado (quantidade de receitas)
            if (rs.next()) {
                totalReceitas = rs.getInt(1);
            }
        } catch (SQLException e) {
            // 6. Tratar possíveis exceções SQL
            e.printStackTrace();
        } finally {
            // 7. Fechar ResultSet e Statement
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 8. Retornar o total de receitas
        return totalReceitas;
    }


    /** Método para deletar uma receita pelo ID
     * @param id ID da receita a ser deletada
     * @return boolean Retorna true se a receita foi deletada com sucesso, false caso contrário
     * @author marianamarrao
     * @throws SQLException Lança uma exceção SQL em caso de erro na operação
     * */
    @Override
    public boolean deleteById(Long id) {
        // 1. Query SQL para deletar uma receita pelo ID
        String sql = "DELETE FROM receitas WHERE id = ?";

        // 2. Objeto auxiliar para executar a query
        PreparedStatement ps = null;

        try {
            // 3. Preparar a query e definir o parâmetro (ID)
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            // 4. Executar a query e retornar true se alguma linha foi afetada
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            // 5. Tratar possíveis exceções SQL
            e.printStackTrace();
            return false;
        } finally {
            // 6. Fechar PreparedStatement e conexão
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
