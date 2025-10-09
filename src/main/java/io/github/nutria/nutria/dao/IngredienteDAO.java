package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.model.Ingrediente;
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

public class IngredienteDAO implements GenericDAO<Ingrediente, Long> {
    @Override
    public boolean insert(Ingrediente ingrediente){
        String sql = "INSERT INTO ingrediente (id,nome) VALUES (?,?)";

        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setLong(1,ingrediente.getId());
            ps.setString(2,ingrediente.getNome());

            int result = ps.executeUpdate();
            return (result > 0);
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        } finally {
            try{
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Ingrediente> findAll(int page) {

        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM ingrediente LIMIT ? OFFSET ?";


        List<Ingrediente> ingredientesArrayList = new ArrayList<>();

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
                Ingrediente ingrediente = new Ingrediente();

                ingrediente.setId(rs.getLong("id"));
                ingrediente.setNome(rs.getString("nome"));


                ingredientesArrayList.add(ingrediente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ingredientesArrayList;
    }

    public int countAll() {
        int totalIngredientes = 0;

        String sql = "SELECT COUNT(*) FROM ingrediente";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();

            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalIngredientes = rs.getInt(1);
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

        return totalIngredientes;
    }
    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM ingrediente WHERE id = ?";

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

    public List<Ingrediente> findByNome(String nome){
        String sql =
                """
                SELECT * FROM ingrediente
                WHERE LOWER(nome) LIKE LOWER(?)
                """;

        PreparedStatement psmt = null;
        Connection connect = null;
        ResultSet rs = null;
        List<Ingrediente> ingredientes = new ArrayList<>();

        try{
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setString(1,"%" + nome + "%");
            rs = psmt.executeQuery();
            while (rs.next()){
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId(rs.getLong("id"));
                ingrediente.setNome(rs.getString("nome"));

                ingredientes.add(ingrediente);
            }

        } catch (SQLException sqle){
            sqle.printStackTrace();
        } finally {
            try {
                if(psmt != null) psmt.close();
                if(rs != null) rs.close();
                if(connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException sqle){
                sqle.printStackTrace();
            }
        }
        return ingredientes;
    }

    @Override
    public boolean update(Ingrediente ingrediente){
        String sql = "UPDATE ingrediente SET nome = ? WHERE id = ?";
        PreparedStatement psmt = null;
        Connection connect = null;
        int result = 0;

        try {
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setString(1,ingrediente.getNome());
            psmt.setLong(2,ingrediente.getId());

            result = psmt.executeUpdate();

        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        } finally {
            try {
                if(psmt != null) psmt.close();
                if(connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return (result > 0);
    }

}
