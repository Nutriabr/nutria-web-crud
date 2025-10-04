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
    @Override
    public boolean insert(Receita receita) {
        String sql = "INSERT INTO receitas (nome, porcao, id_produto) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);

            ps.setString(1, receita.getNome());
            ps.setString(2, receita.getPorcao());
            ps.setLong(3, receita.getIdProduto());

            int result = ps.executeUpdate();

            return (result > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Receita> findAll(int page) {

        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM receitas LIMIT ? OFFSET ?";

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
                receita.setNome(rs.getString("nome"));
                receita.setPorcao(rs.getString("porcao"));
                receita.setIdProduto(rs.getLong("id_produto"));

                receitasArrayList.add(receita);
            }
        } catch (SQLException e) {
            // 10. Tratar exceções SQL
            e.printStackTrace();
        } finally {
            // 11. Fechar ResultSet, PreparedStatement e conexão
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 12. Retornar a lista de receitas
        return receitasArrayList;
    }


    public int countAll() {
        int totalReceitas = 0;

        String sql = "SELECT COUNT(*) FROM receitas";

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
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return totalReceitas;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM receitas WHERE id = ?";

        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
