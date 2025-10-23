package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IProdutoDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.InvalidNumberException;
import io.github.nutria.nutria.exceptions.RequiredFieldException;
import io.github.nutria.nutria.exceptions.ValidationException;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso a dados (DAO) para a entidade {@link Produto}.
 * <p>
 * Implementa as operações de CRUD e métodos personalizados.
 *
 * @see GenericDAO
 * @see IProdutoDAO
 * @see Produto
 * @author Mariana Marrão
 * @version 1.0
 */
public class ProdutoDAO implements GenericDAO<Produto, Long>, IProdutoDAO {

    @Override
    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produto (nome, id_usuario) VALUES (?, ?)";

        PreparedStatement ps = null;
        Connection connect = null;

        validarProduto(produto);

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
    public List<Produto> buscarTodos(int page) {
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
                produto.setIdUsuario(rs.getLong("id_usuario"));
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
    public Produto buscarPorId(Long id){
        String sql = "SELECT * FROM produto WHERE id = ?";
        PreparedStatement ps = null;
        Connection connect = null;
        ResultSet rs = null;
        Produto produto = null;

        if (id == null || id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        try {
            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            rs=  ps.executeQuery();
            if(rs.next()){
                produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setIdUsuario(rs.getLong("id_usuario"));
            }

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar o produto: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar a produto com ID: " + id, e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if(rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return produto;
    }

    @Override
    public boolean alterar(Produto produto) {
        if (produto.getId() == null || produto.getId() <= 0) {
            throw new ValidationException("ID é obrigatório para atualização");
        }

        validarProduto(produto);

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
    public boolean deletarPorId(Long id) {
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

    @Override
    public int contarTodos() {
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
    public List<Produto> buscarPorNome(String nome) {
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
     * Valida campos obrigatórios de um {@link Produto}.
     *
     * @param produto o objeto {@link Produto} que será validado.
     * @throws ValidationException se o objeto for {@code null}.
     * @throws RequiredFieldException se determinado campo obrigatório for {@code null} ou vazio.
     */
    private void validarProduto(Produto produto) {
        if (produto == null) throw new ValidationException("Produto não pode ser nulo");
        if (produto.getNome() == null || produto.getNome().isBlank()) throw new RequiredFieldException("nome");
        if (produto.getIdUsuario() == null || produto.getIdUsuario() == null) throw new RequiredFieldException("usuario");
    }
}
