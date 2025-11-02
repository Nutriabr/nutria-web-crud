package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IIngredienteDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.InvalidNumberException;
import io.github.nutria.nutria.exceptions.RequiredFieldException;
import io.github.nutria.nutria.exceptions.ValidationException;
import io.github.nutria.nutria.model.Ingrediente;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso a dados (DAO) para a entidade {@link Ingrediente}.
 * <p>
 * Implementa as operações de CRUD e métodos personalizados.
 *
 * @see GenericDAO
 * @see IIngredienteDAO
 * @see Ingrediente
 * @author Mariana Marrão
 * @version 1.0
 */
public class IngredienteDAO implements GenericDAO<Ingrediente, Long>, IIngredienteDAO {

    @Override
    public boolean inserir(Ingrediente ingrediente) {
        String sql = "INSERT INTO ingrediente (nome) VALUES (?)";

        PreparedStatement ps = null;
        Connection connect = null;

        validarIngrediente(ingrediente);

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, ingrediente.getNome());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao salvar ingrediente: " + ingrediente.getNome());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao salvar ingrediente", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    @Override
    public List<Ingrediente> buscarTodos(int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM ingrediente LIMIT ? OFFSET ?";

        List<Ingrediente> ingredientesArrayList = new ArrayList<>();
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
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId(rs.getLong("id"));
                ingrediente.setNome(rs.getString("nome"));
                ingredientesArrayList.add(ingrediente);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar todos os ingredientes");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar todos os ingredientes", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return ingredientesArrayList;
    }

    @Override
    public Ingrediente buscarPorId(Long id){
        String sql = "SELECT * FROM ingrediente WHERE id = ?";
        PreparedStatement ps = null;
        Connection connect = null;
        ResultSet rs = null;
        Ingrediente ingrediente = null;

        if (id == null || id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        try {
            connect = ConnectionFactory.conectar();

            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            rs=  ps.executeQuery();
            if(rs.next()){
                ingrediente = new Ingrediente();
                ingrediente.setId(rs.getLong("id"));
                ingrediente.setNome(rs.getString("nome"));
            }

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar o ingrediente: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar o ingrediente com ID: " + id, e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if(rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return ingrediente;
    }

    @Override
    public List<Ingrediente> buscarPorNome(String nome, int page) {
        int limite = 4;
        int offset = (page - 1) * limite;
        String sql = """
                SELECT * FROM ingrediente
                WHERE LOWER(nome) LIKE LOWER(?)
                LIMIT ? OFFSET ?
                """;

        List<Ingrediente> ingredientes = new ArrayList<>();
        PreparedStatement ps = null;
        Connection connect = null;
        ResultSet rs = null;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ps.setInt(2,limite);
            ps.setInt(3,offset);
            rs = ps.executeQuery();

            while (rs.next()) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId(rs.getLong("id"));
                ingrediente.setNome(rs.getString("nome"));
                ingredientes.add(ingrediente);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar ingrediente pelo nome: " + nome);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar ingrediente pelo nome", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return ingredientes;
    }

    public List<String> buscarNomes() {
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<String> nomes = new ArrayList<>();

        try {
            connect = ConnectionFactory.conectar();

            String sql = "SELECT nome FROM ingrediente";
            ps = connect.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                nomes.add(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nomes;
    }
    @Override
    public boolean alterar(Ingrediente ingrediente) {
        if (ingrediente.getId() == null || ingrediente.getId() <= 0) {
            throw new ValidationException("ID é obrigatório para atualização");
        }

        validarIngrediente(ingrediente);

        String sql = "UPDATE ingrediente SET nome = ? WHERE id = ?";
        PreparedStatement psmt = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();
            psmt = connect.prepareStatement(sql);
            psmt.setString(1, ingrediente.getNome());
            psmt.setLong(2, ingrediente.getId());

            return psmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao atualizar ingrediente: " + ingrediente.getId());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao atualizar ingrediente", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (psmt != null) psmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    @Override
    public boolean deletarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        String sql = "DELETE FROM ingrediente WHERE id = ?";
        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar ingrediente: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar ingrediente", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    public boolean deletarPorNome(String nome) {
        String sql = "DELETE FROM ingrediente WHERE nome = ?";
        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, nome);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar ingrediente: " + nome);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar ingrediente", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    @Override
    public int contarTodos() {
        int totalIngredientes = 0;
        String sql = "SELECT COUNT(*) FROM ingrediente";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalIngredientes = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao realizar a contagem total de ingredientes");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a contagem total de ingredientes", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalIngredientes;
    }

    @Override
    public int contarPorNome(String nome) {
        String sql = "SELECT COUNT(*) FROM ingrediente WHERE LOWER(nome) LIKE LOWER(?)";
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao contar ingredientes filtrados", e);
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

    /**
     * Valida campos obrigatórios de um {@link Ingrediente}.
     *
     * @param ingrediente o objeto {@link Ingrediente} que será validado.
     * @throws ValidationException se o objeto for {@code null}.
     * @throws RequiredFieldException se o nome do {@link Ingrediente} for {@code null} ou vazio.
     */
    private void validarIngrediente(Ingrediente ingrediente) {
        if (ingrediente == null) throw new ValidationException("Ingrediente não pode ser nulo");
        if (ingrediente.getNome() == null || ingrediente.getNome().isBlank()) throw new RequiredFieldException("nome");
    }
}