package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IReceitaDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.InvalidNumberException;
import io.github.nutria.nutria.exceptions.RequiredFieldException;
import io.github.nutria.nutria.exceptions.ValidationException;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso a dados (DAO) para a entidade {@link Receita}.
 * <p>
 * Implementa as operações de CRUD e métodos personalizados.
 *
 * @see GenericDAO
 * @see IReceitaDAO
 * @see Receita
 * @author Mariana Marrão
 * @version 1.0
 */
public class ReceitaDAO implements GenericDAO<Receita,Long>, IReceitaDAO {

    public boolean inserir(Receita receita) {
        if (receita == null) throw new ValidationException("Receita não pode ser nulo");
        if (receita.getPorcao() == null || receita.getPorcao().isEmpty()) throw new RequiredFieldException("porcao");
        if (receita.getIdProduto() == null || receita.getIdProduto() <= 0)
            throw new RequiredFieldException("id_produto");



        String sql = "INSERT INTO receita (porcao, id_produto) VALUES (?, ?)";

        PreparedStatement ps = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.conectar();

            ps = connect.prepareStatement(sql);

            ps.setString(1, receita.getPorcao());
            ps.setLong(2, receita.getIdProduto());

            int result = ps.executeUpdate();

            return (result > 0);
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao salvar receita: " + receita);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao salvar receita", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();

            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    public List<Receita> buscarTodos(int page) {

        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM receita LIMIT ? OFFSET ?";

        List<Receita> receitasArrayList = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();

            ps = connect.prepareStatement(sql);
            ps.setInt(1, limite);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Receita receita = new Receita();

                receita.setId(rs.getLong("id"));
                receita.setPorcao(rs.getString("porcao"));
                receita.setIdProduto(rs.getLong("id_produto"));

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
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        // 12. Retornar a lista de receitas
        return receitasArrayList;
    }

    public Receita buscarPorId (Long id){

        String sql = "SELECT * FROM receita WHERE id = ?";
        PreparedStatement ps = null;
        Connection connect = null;
        ResultSet rs = null;
        Receita receita = null;

        if (id == null || id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        try {
            connect = ConnectionFactory.conectar();

            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);
            rs=  ps.executeQuery();
            if(rs.next()){
                receita = new Receita();
                receita.setId(rs.getLong("id"));
                receita.setPorcao(rs.getString("porcao"));
                receita.setIdProduto(rs.getLong("id_produto"));
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar a receita: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar a receita com ID: " + id, e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if(rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return receita;
    }

    public List<Receita> buscarPorIdOuIdProduto(Long numero, int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = """
            SELECT DISTINCT * FROM receita 
            WHERE id = ? OR id_produto = ?
            LIMIT ? OFFSET ?
            """;

        PreparedStatement ps = null;
        Connection connect = null;
        ResultSet rs = null;
        List<Receita> receitas = new ArrayList<>();

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, numero);
            ps.setLong(2, numero);
            ps.setInt(3, limite);
            ps.setInt(4, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Receita receita = new Receita();
                receita.setId(rs.getLong("id"));
                receita.setPorcao(rs.getString("porcao"));
                receita.setIdProduto(rs.getLong("id_produto"));
                receitas.add(receita);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar receita por ID ou ID_Produto: " + numero);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar produto por ID ou ID_Produto", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return receitas;
    }

    public List<Receita> buscarPorPorcao(String porcao, int page){
        int limite = 4;
        int offset = (page - 1) * limite;
        String sql =
                """
                SELECT * FROM receita
                WHERE LOWER(porcao) LIKE LOWER(?) 
                LIMIT ? OFFSET ?
                """;

        PreparedStatement ps = null;
        Connection connect = null;
        ResultSet rs = null;
        List<Receita> receitas = new ArrayList<>();

        try{
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1,"%" + porcao + "%");
            ps.setInt(2,limite);
            ps.setInt(3,offset);
            rs = ps.executeQuery();
            while (rs.next()){
                Receita receita = new Receita();
                receita.setId(rs.getLong("id"));
                receita.setPorcao(rs.getString("porcao"));
                receita.setIdProduto(rs.getLong("id_produto"));

                receitas.add(receita);
            }

        } catch (SQLException sqle){
            System.err.println("[DAO ERROR] Erro ao buscar a receita: " + porcao);
            sqle.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar a receita com porcao: " + porcao, sqle);
        } finally {
            try {
                if(connect != null) ConnectionFactory.desconectar(connect);
                if(ps != null) ps.close();
                if(rs != null) rs.close();
            } catch (SQLException sqle){
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", sqle);
            }
        }
        return receitas;
    }

    public boolean alterar(Receita receita){
        String sql = "UPDATE receita SET porcao = ?, id_produto = ? WHERE id = ?";
        PreparedStatement psmt = null;
        Connection connect = null;

        if (receita.getId() == null || receita.getId() <= 0) {
            throw new ValidationException("ID é obrigatório para atualização");
        }

        int result = 0;

        try {
            connect = ConnectionFactory.conectar();
            psmt = connect.prepareStatement(sql);
            psmt.setString(1,receita.getPorcao());
            psmt.setLong(2,receita.getIdProduto());
            psmt.setLong(3,receita.getId());

            result = psmt.executeUpdate();

        } catch (SQLException sqle){
            System.err.println("[DAO ERROR] Erro ao atualizar a receita: " + receita.getId());
            sqle.printStackTrace(System.err);
            throw new DataAccessException("Erro ao atualizar receita", sqle);
        } finally {
            try {
                if(connect != null) ConnectionFactory.desconectar(connect);
                if(psmt != null) psmt.close();
            } catch (SQLException e){
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return (result > 0);

    }

    public boolean deletarPorId(Long id) {
        String sql = "DELETE FROM receita WHERE id = ?";

        PreparedStatement ps = null;
        Connection connect = null;

        if (id == null || id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        try {
            connect = ConnectionFactory.conectar();

            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar a receita: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar a receita com ID: " + id, e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    public int contarTodos() {
        int totalReceitas = 0;

        String sql = "SELECT COUNT(*) FROM receita";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();

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
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalReceitas;
    }

    public int contarPorId(Long id) {
        String sql = "SELECT COUNT(*) FROM receita WHERE id = ?";
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao contar receitas filtradas", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco", e);
            }
        }
        return total;
    }

    public int contarPorIdOuIdProduto(Long numero) {
        String sql = """
            SELECT COUNT(DISTINCT id) FROM produto 
            WHERE id = ? OR id_usuario = ?
            """;

        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, numero);
            ps.setLong(2, numero);

            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao contar produtos por ID ou ID_Usuário: " + numero);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao contar produtos por ID ou ID_Usuário", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco", e);
            }
        }

        return total;
    }

    public int contarPorPorcao(String porcao) {
        String sql = "SELECT COUNT(*) FROM receita WHERE LOWER(porcao) LIKE LOWER(?)";
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, "%" + porcao + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao contar receitas filtradas", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco", e);
            }
        }
        return total;
    }
}