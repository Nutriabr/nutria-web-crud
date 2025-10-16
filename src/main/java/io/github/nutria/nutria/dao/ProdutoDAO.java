package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IProdutoDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso a dados (DAO) para a entidade Produto.
 * Implementa as operações CRUD (Create, Read, Update, Delete) para a tabela "produtos" no banco de dados.
 * @author marianamarrao
 * @version 1.1
 */
public class ProdutoDAO implements GenericDAO<Produto, Long>, IProdutoDAO {

    @Override
    public boolean insert(Produto produto) {
        String sql = "INSERT INTO produto (nome, id_usuario) VALUES (?, ?)";

        PreparedStatement ps = null;
        Connection connect = null;

        validateProduto(produto);

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setLong(2, produto.getIdUsuario());

            return (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao salvar produto: " + produto.getNome());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao salvar produto", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    @Override
    public List<Produto> findAll(int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM produto LIMIT ? OFFSET ?";

        List<Produto> produtosArrayList = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setInt(1, limite);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produtosArrayList.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar todos os produtos");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar todos os produtos", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return produtosArrayList;
    }

    @Override
    public int countAll() {
        int totalProdutos = 0;
        String sql = "SELECT COUNT(*) FROM produto";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalProdutos = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao realizar a contagem total de produtos");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a contagem total de produtos", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalProdutos;
    }

    @Override
    public boolean update(Produto produto) {
        if (produto.getId() == null || produto.getId() <= 0) {
            throw new ValidationException("ID é obrigatório para atualização");
        }

        validateProduto(produto);

        String sql = "UPDATE produto SET nome = ? WHERE id = ?";
        PreparedStatement psmt = null;
        Connection connect = null;
        int result = 0;

        try {
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setString(1, produto.getNome());
            psmt.setLong(2, produto.getId());

            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao atualizar produto: " + produto.getId());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao atualizar produto", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (psmt != null) psmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return (result > 0);
    }

    @Override
    public boolean deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        String sql = "DELETE FROM produto WHERE id = ?";

        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar produto: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar produto", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    public List<Produto> findByNome(String nome) {
        String sql = """
                SELECT * FROM produto
                WHERE LOWER(nome) LIKE LOWER(?)
                """;

        PreparedStatement psmt = null;
        Connection connect = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();

        try {
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setString(1, "%" + nome + "%");
            rs = psmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar produto pelo nome: " + nome);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar produto pelo nome", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (psmt != null) psmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return produtos;
    }

    /**
     * Validação de campos obrigatórios do produto
     */
    private void validateProduto(Produto produto) {
        if (produto == null) {
            throw new ValidationException("Produto não pode ser nulo");
        }
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new RequiredFieldException("nome");
        }
        if (produto.getIdUsuario() == null || produto.getIdUsuario() == null) {
            throw new RequiredFieldException("usuario");
        }
    }
}
