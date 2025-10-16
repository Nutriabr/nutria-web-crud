package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.model.ReceitaIngrediente;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaIngredienteDAO /*implements GenericDAO<ReceitaIngrediente, Long>*/ {
    public boolean insert(ReceitaIngrediente receitaIngrediente) {
        if (receitaIngrediente.getId() == 0) return false;
        if (receitaIngrediente.getIdIngrediente() == 0) return false;
        if (receitaIngrediente.getQuantidade() == 0) return false;


        String sql = "INSERT INTO receita (id_receita, id_ingrediente, quantidade) VALUES (?, ?, ?)";

        PreparedStatement ps = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);

            ps.setLong(1, receitaIngrediente.getIdReceita());
            ps.setLong(2, receitaIngrediente.getIdIngrediente());
            ps.setDouble(3, receitaIngrediente.getQuantidade());

            int result = ps.executeUpdate();

            return (result > 0);

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao salvar relação Receita e Ingrediente: " + receitaIngrediente);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao salvar relação Receita e Ingrediente", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }


    public List<ReceitaIngrediente> findAll(int page) {

        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM receita_ingrediente LIMIT ? OFFSET ?";

        List<ReceitaIngrediente> receitasIngredienteArrayList = new ArrayList<>();

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
                ReceitaIngrediente receita = new ReceitaIngrediente(
                        rs.getLong("id"),
                        rs.getLong("idReceita"),
                        rs.getLong("idIngrediente"),
                        rs.getDouble("quantidade")
                );

                receitasIngredienteArrayList.add(receita);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar por todas as relações Receita e Ingrediente");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar por todas as relações Receita e Ingrediente", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return receitasIngredienteArrayList;
    }

    public int countAll() {
        int totalReceitasIngredientes = 0;

        String sql = "SELECT COUNT(*) FROM receita_ingrediente";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();

            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalReceitasIngredientes = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao contar as relações entre receita e ingrediente");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao contar as relações entre receita e ingrediente\"", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalReceitasIngredientes;
    }

    public List<ReceitaIngrediente> findByQuantidadeMaior(int page, Double quantidade) {
        int limit = 4;
        int offset = (page - 1) * limit;

        String sql = "SELECT * FROM receita_ingrediente WHERE quantidade >= ? LIMIT ? OFFSET ?";

        PreparedStatement psmt = null;
        Connection connect = null;
        ResultSet rs = null;
        List<ReceitaIngrediente> receitasIngredienteArrayList = new ArrayList<>();

        try{
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setDouble(1, quantidade);
            rs = psmt.executeQuery();
            while (rs.next()){
                ReceitaIngrediente receita = new ReceitaIngrediente(
                        rs.getLong("id"),
                        rs.getLong("receita"),
                        rs.getLong("idIngrediente"),
                        rs.getDouble("quantidade")
                );

                receitasIngredienteArrayList.add(receita);
            }

        } catch (SQLException sqle){
            System.err.println("[DAO ERROR] Erro ao buscar relação receita ingrediente por quantidade maior que: " + quantidade);
            sqle.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar relação receita ingrediente por quantidade maior que: " + quantidade, sqle);
        } finally {
            try {
                if(connect != null) ConnectionFactory.disconnect(connect);
                if(psmt != null) psmt.close();
                if(rs != null) rs.close();
            } catch (SQLException sqle){
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", sqle);
            }
        }
        return receitasIngredienteArrayList;
    }

    public List<ReceitaIngrediente> findByQuantidadeMenor(int page, Double quantidade) {
        int limit = 4;
        int offset = (page - 1) * limit;

        String sql = "SELECT * FROM receita_ingrediente WHERE quantidade <= ? LIMIT ? OFFSET ?";

        PreparedStatement psmt = null;
        Connection connect = null;
        ResultSet rs = null;
        List<ReceitaIngrediente> receitasIngredienteArrayList = new ArrayList<>();

        try{
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setDouble(1, quantidade);
            rs = psmt.executeQuery();
            while (rs.next()){
                ReceitaIngrediente receita = new ReceitaIngrediente(
                        rs.getLong("id"),
                        rs.getLong("idReceita"),
                        rs.getLong("idIngrediente"),
                        rs.getDouble("quantidade")
                );

                receitasIngredienteArrayList.add(receita);
            }

        } catch (SQLException sqle){
            System.err.println("[DAO ERROR] Erro ao buscar relação receita ingrediente por quantidade menor que: " + quantidade);
            sqle.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar relação receita ingrediente por quantidade menor que: " + quantidade, sqle);
        } finally {
            try {
                if(connect != null) ConnectionFactory.disconnect(connect);
                if(psmt != null) psmt.close();
                if(rs != null) rs.close();
            } catch (SQLException sqle){
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", sqle);
            }
        }
        return receitasIngredienteArrayList;
    }

    public List<ReceitaIngrediente> findByQuantidadeEntre(int page, Double quantidadeMax, Double quantidadeMin) {
        int limit = 4;
        int offset = (page - 1) * limit;

        String sql = "SELECT * FROM receita_ingrediente WHERE quantidade BETWEEN ? AND ? LIMIT ? OFFSET ?";

        PreparedStatement psmt = null;
        Connection connect = null;
        ResultSet rs = null;
        List<ReceitaIngrediente> receitasIngredienteArrayList = new ArrayList<>();

        try{
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setDouble(1, quantidadeMin);
            psmt.setDouble(2, quantidadeMax);
            rs = psmt.executeQuery();
            while (rs.next()){
                ReceitaIngrediente receita = new ReceitaIngrediente(
                        rs.getLong("id"),
                        rs.getLong("idReceita"),
                        rs.getLong("idIngrediente"),
                        rs.getDouble("quantidade")
                );

                receitasIngredienteArrayList.add(receita);
            }

        } catch (SQLException sqle){
            System.err.println("[DAO ERROR] Erro ao buscar relação receita ingrediente por quantidade entre: " + quantidadeMin + " e " + quantidadeMax);
            sqle.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar relação receita ingrediente por quantidade entre: " + quantidadeMin + " e " + quantidadeMax, sqle);
        } finally {
            try {
                if(connect != null) ConnectionFactory.disconnect(connect);
                if(psmt != null) psmt.close();
                if(rs != null) rs.close();
            } catch (SQLException sqle){
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", sqle);
            }
        }
        return receitasIngredienteArrayList;
    }

    public boolean update(ReceitaIngrediente receitaIngrediente){
        String sql = "UPDATE receita_ingrediente SET id = ?, receita = ?, idIngrediente = ?, quantidade = ? WHERE id = ?";
        PreparedStatement psmt = null;
        Connection connect = null;
        int result = 0;

        try {
            connect = ConnectionFactory.connect();
            psmt = connect.prepareStatement(sql);
            psmt.setLong(1, receitaIngrediente.getId());
            psmt.setLong(2, receitaIngrediente.getIdReceita());
            psmt.setLong(3, receitaIngrediente.getIdIngrediente());
            psmt.setDouble(4, receitaIngrediente.getQuantidade());
            psmt.setLong(5, receitaIngrediente.getId());

            result = psmt.executeUpdate();

        } catch (SQLException sqle){
            System.err.println("[DAO ERROR] Erro ao atualizar a relação receita e ingrediente: " + receitaIngrediente.getId());
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

    public boolean deleteById(Long id) {
        String sql = "DELETE FROM receita_ingrediente WHERE id = ?";

        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar a relação receita e ingrediente: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar  a relação receita e ingrediente: " + id, e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }
}