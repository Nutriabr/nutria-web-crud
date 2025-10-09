package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.InvalidNumberException;
import io.github.nutria.nutria.exceptions.RequiredFieldException;
import io.github.nutria.nutria.exceptions.ValidationException;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.model.Receita;
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
    @Override
    public boolean insert(Receita receita) {
        if (receita == null) throw new ValidationException("Receita não pode ser nulo");
        if (receita.getPorcao() == null || receita.getPorcao().isEmpty()) throw new RequiredFieldException("porcao");
        if (receita.getProduto() == null || receita.getProduto().getId() == null || receita.getProduto().getId() <= 0)
            throw new RequiredFieldException("produto");



        String sql = "INSERT INTO receita (porcao, id_produto) VALUES (?, ?)";

        PreparedStatement ps = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);

            ps.setString(1, receita.getPorcao());
            ps.setLong(2, receita.getProduto().getId());

            int result = ps.executeUpdate();

            return (result > 0);

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao salvar receita: " + receita);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao salvar receita", e);
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
    public boolean update(Receita receita){
        String sql = "UPDATE receita SET porcao = ?, id_produto = ? WHERE id = ?";
        PreparedStatement psmt = null;
        Connection connect = null;

        if (receita.getId() == null || receita.getId() <= 0) {
            throw new ValidationException("ID é obrigatório para atualização");
        }

        int result = 0;

        try {
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setString(1,receita.getPorcao());
            psmt.setLong(2,receita.getProduto().getId());
            psmt.setLong(3,receita.getId());

            result = psmt.executeUpdate();

        } catch (SQLException sqle){
            System.err.println("[DAO ERROR] Erro ao atualizar a receita: " + receita.getId());
            sqle.printStackTrace(System.err);
            throw new DataAccessException("Erro ao atualizar receita", sqle);
        } finally {
            try {
                if(connect != null) ConnectionFactory.disconnect(connect);
                if(psmt != null) psmt.close();
            } catch (SQLException e){
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return (result > 0);

    }

    @Override
    public List<Receita> findAll(int page) {

        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT r.*, p.nome AS nome_produto " +
                       "FROM receita r " +
                       "JOIN produto p ON r.id_produto = p.id " +
                       "LIMIT ? OFFSET ?";

        List<Receita> receitasArrayList = new ArrayList<>();

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
                Receita receita = new Receita();

                receita.setId(rs.getLong("id"));
                receita.setPorcao(rs.getString("porcao"));

                Produto produto = new Produto();
                produto.setId(rs.getLong("id_produto"));
                produto.setNome(rs.getString("nome_produto"));

                receita.setProduto(produto);

                receitasArrayList.add(receita);
            }
        } catch (SQLException e) {
            // 10. Tratar exceções SQL
            System.err.println("[DAO ERROR] Erro ao buscar por todas as receitas");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar pelas receitas", e);
        } finally {
            // 11. Fechar ResultSet, PreparedStatement e conexão
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        // 12. Retornar a lista de receitas
        return receitasArrayList;
    }


    public int countAll() {
        int totalReceitas = 0;

        String sql = "SELECT COUNT(*) FROM receita";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();

            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalReceitas = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao contar as receitas");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao contar as receitas", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalReceitas;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM receita WHERE id = ?";

        PreparedStatement ps = null;
        Connection connect = null;

        if (id == null || id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        try {
            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar a receita: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar a receita com ID: " + id, e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    public List<Receita> findByPorcao(String porcao){
        String sql =
                """
                SELECT r.*, p.nome AS nome_produto
                FROM receita r
                JOIN produto p ON r.id_produto = p.id
                WHERE LOWER(r.porcao) LIKE LOWER(?)
                """;

        PreparedStatement psmt = null;
        Connection connect = null;
        ResultSet rs = null;
        List<Receita> receitas = new ArrayList<>();

        try{
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setString(1,"%" + porcao + "%");
            rs = psmt.executeQuery();
            while (rs.next()){
                Receita receita = new Receita();
                receita.setId(rs.getLong("id"));
                receita.setPorcao(rs.getString("porcao"));
                Produto produto = new Produto();
                produto.setId(rs.getLong("id_produto"));
                produto.setNome(rs.getString("nome_produto"));
                receita.setProduto(produto);

                receitas.add(receita);
            }

        } catch (SQLException sqle){
            System.err.println("[DAO ERROR] Erro ao buscar a receita: " + porcao);
            sqle.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar a receita com porcao: " + porcao, sqle);
        } finally {
            try {
                if(psmt != null) psmt.close();
                if(rs != null) rs.close();
                if(connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException sqle){
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", sqle);
            }
        }
        return receitas;
    }
}