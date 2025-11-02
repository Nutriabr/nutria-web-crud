package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IAdminDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.util.ConnectionFactory;
import io.github.nutria.nutria.util.FieldUsedValidator;
import io.github.nutria.nutria.util.PasswordHasher;
import io.github.nutria.nutria.util.RegexValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe de acesso a dados (DAO) para a entidade {@link Admin}.
 * <p>
 * Implementa as operações de CRUD e métodos personalizados.
 *
 * @see GenericDAO
 * @see IAdminDAO
 * @see Admin
 * @author Luis Henrique
 * @version 1.0
 */
public class AdminDAO implements GenericDAO<Admin, Long>, IAdminDAO {

    @Override
    public boolean inserir(Admin admin) {
        String sql = "INSERT INTO admin (nome, email, senha, telefone, nascimento, cargo, foto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection connect = null;
        PreparedStatement ps = null;

        validarAdmin(admin);

        try {
            if (FieldUsedValidator.ehCampoEmUso("admin","email", admin.getEmail())) throw new DuplicateEmailException(admin.getEmail());
            if (FieldUsedValidator.ehCampoEmUso("admin","telefone", admin.getTelefone())) throw new DuplicatePhoneException(admin.getTelefone());

            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);

            String senhaHashed = PasswordHasher.hashSenha(admin.getSenha());

            ps.setString(1, admin.getNome());
            ps.setString(2, admin.getEmail());
            ps.setString(3, senhaHashed);
            ps.setString(4, admin.getTelefone());
            ps.setDate(5, admin.getNascimento());
            ps.setString(6, admin.getCargo());
            ps.setString(7, admin.getFoto());

            return (ps.executeUpdate() > 0);

        } catch (SQLException se) {
            System.err.println("[DAO ERROR] Erro ao salvar admin: " + admin.getEmail());
            se.printStackTrace(System.err);
            throw new DataAccessException("Erro ao salvar admin", se);
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
    public List<Admin> buscarTodos(int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM admin ORDER BY id LIMIT ? OFFSET ?";

        List<Admin> adminArrayList = new ArrayList<Admin>();

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
                Admin admin = new Admin(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getDate("nascimento"),
                        rs.getString("cargo"),
                        rs.getString("foto")
                );

                adminArrayList.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar por todos os administradores");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar pelos admininstradores", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return adminArrayList;
    }

    @Override
    public Admin buscarPorId(Long id) {
        if (id <= 0) throw new InvalidNumberException("id", "ID deve ser maior que zero");

        String sql = "SELECT * FROM admin WHERE id = ?";

        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getLong("id"));
                admin.setNome(rs.getString("nome"));
                admin.setEmail(rs.getString("email"));
                admin.setSenha(rs.getString("senha"));
                admin.setTelefone(rs.getString("telefone"));
                admin.setNascimento(rs.getDate("nascimento"));
                admin.setCargo(rs.getString("cargo"));
                admin.setFoto(rs.getString("foto"));

                return admin;
            } else {
                throw new EntityNotFoundException("Admin", id);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário por ID: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar usuário", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    @Override
    public Optional<Admin> buscarPorEmail(String email) {
        String sql = "SELECT * FROM admin WHERE email = ?";

        if (email == null || email.isBlank()) throw new RequiredFieldException("email");

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getDate("nascimento"),
                        rs.getString("cargo"),
                        rs.getString("foto")
                );
                return Optional.of(admin);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar admin por email: " + email);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar admin", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Admin> buscarPorNomeAdminOuDominioEmail(String valorBuscado, int page) {
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int limit = 4;
        int offset = (page - 1) * limit;

        List<Admin> admins = new ArrayList<>();

        try {
            connect = ConnectionFactory.conectar();

            String sql = "SELECT * FROM admin WHERE nome LIKE ? OR email LIKE ? LIMIT ? OFFSET ?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, "%" + valorBuscado + "%");
            ps.setString(2, "%" + valorBuscado + "%");
            ps.setInt(3, limit);
            ps.setInt(4, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getDate("nascimento"),
                        rs.getString("cargo"),
                        rs.getString("foto")
                );

                admins.add(admin);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar admin pelo valor buscado: " + valorBuscado);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar admin", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return admins;
    }

    @Override
    public Optional<Admin> buscarPorTelefone(String telefone) {
        String sql = "SELECT * FROM admin WHERE telefone = ?";

        if (telefone == null || telefone.isBlank()) throw new RequiredFieldException("telefone");

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, telefone);

            rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getDate("nascimento"),
                        rs.getString("cargo"),
                        rs.getString("foto")
                );

                return Optional.of(admin);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar admin pelo telefone: " + telefone);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar admin pelo telefone", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean alterar(Admin admin) {
        if (admin.getId() == null || admin.getId() <= 0) throw new ValidationException("ID é obrigatório para atualização");

        buscarPorId(admin.getId());

        Optional<Admin> adminExistentePeloEmail = buscarPorEmail(admin.getEmail());
        if (adminExistentePeloEmail.isPresent() && !adminExistentePeloEmail.get().getId().equals(admin.getId())) {
            throw new DuplicateEmailException(admin.getEmail());
        }

        Optional<Admin> adminExistentePeloTelefone = buscarPorTelefone(admin.getTelefone());
        if (adminExistentePeloTelefone.isPresent() && !adminExistentePeloTelefone.get().getId().equals(admin.getId())) {
            throw new DuplicatePhoneException(admin.getTelefone());
        }

        String sql = "UPDATE admin SET nome = ?, email = ?, senha = ?, telefone = ?, nascimento = ?, cargo = ?, foto = ? WHERE id = ?";

        int result = 0;

        PreparedStatement pstmt = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.conectar();
            pstmt = connect.prepareStatement(sql);

            String hashedSenha = PasswordHasher.hashSenha(admin.getSenha());

            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, hashedSenha);
            pstmt.setString(4, admin.getTelefone());
            pstmt.setDate(5, admin.getNascimento());
            pstmt.setString(6, admin.getCargo());
            pstmt.setString(7, admin.getFoto());
            pstmt.setLong(8, admin.getId());

            result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao atualizar o admin: " + admin.getId());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao atualizar admin", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return (result > 0);
    }

    @Override
    public void alterarSenhaPeloEmail(String email, String senha) {
        if (email.isBlank()) throw new ValidationException("Email é obrigatório para atualização");

        Optional<Admin> admin = buscarPorEmail(email);
        if (admin.isEmpty()) {
            throw new EntityNotFoundException("Admin", admin);
        }

        String sql = "UPDATE admin SET senha = ? WHERE email = ?";

        PreparedStatement pstmt = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.conectar();
            pstmt = connect.prepareStatement(sql);

            String hashedSenha = PasswordHasher.hashSenha(senha);

            pstmt.setString(1, hashedSenha);
            pstmt.setString(2, email);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao atualizar o admin: " + email);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao atualizar admin", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    @Override
    public boolean deletarPorId(Long id) {
        String sql = "DELETE FROM admin WHERE id = ?";

        boolean result = false;

        if (id <= 0) throw new InvalidNumberException("id", "ID deve ser maior que zero");

        PreparedStatement ps = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            result = (ps.executeUpdate() > 0);
            if (!result) {
                throw new EntityNotFoundException("Admin", id);
            }

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar o admin: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar admin", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return result;
    }

    @Override
    public int contarTodos() {
        int totalAdmins = 0;

        String sql = "SELECT COUNT(*) FROM admin";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();

            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalAdmins = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao realizar a contagem total de admins");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a contagem total de admins", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalAdmins;
    }

    @Override
    public int contarTodosFiltrados(String valorBuscado) {
        int totalAdmins = 0;

        String sql = "SELECT COUNT(*) FROM admin WHERE email LIKE ? OR nome LIKE ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();

            pstmt = connect.prepareStatement(sql);
            pstmt.setString(1, "%" + valorBuscado + "%");
            pstmt.setString(2, "%" + valorBuscado + "%");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalAdmins = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao realizar a contagem total de usuarios");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a contagem total de usuarios", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalAdmins;
    }

    /**
     * Valida campos obrigatórios de um {@link Admin}.
     *
     * @param admin o objeto {@link Admin} que será validado.
     * @throws ValidationException se o objeto for {@code null}.
     * @throws RequiredFieldException se determinado campo obrigatório for {@code null} ou vazio.
     * @throws InvalidEmailException se o email do {@link Admin} for inválido.
     * @throws InvalidPhoneException se o telefone do {@link Admin} tiver mais de 11 dígitos e não atender a regex.
     * @throws InvalidPasswordException se a senha do {@link Admin} tiver menos de 8 caracteres.
     */
    private void validarAdmin(Admin admin) {
        if (admin == null) throw new ValidationException("Preencha os campos obrigatórios");

        if (admin.getNome() == null || admin.getNome().isBlank()) throw new RequiredFieldException("nome");

        if (admin.getEmail() == null || admin.getEmail().isBlank()) throw new RequiredFieldException("email");

        if (!RegexValidator.ehEmailValido(admin.getEmail())) throw new InvalidEmailException(admin.getEmail());

        if (!RegexValidator.ehTelefoneValido(admin.getTelefone())) throw new InvalidPhoneException(admin.getTelefone());

        if (!RegexValidator.ehSenhaValida(admin.getSenha())) throw new InvalidPasswordException(admin.getSenha());

        if (admin.getTelefone() == null || admin.getTelefone().isBlank()) throw new RequiredFieldException("telefone");

        if (admin.getTelefone().length() > 11) throw new InvalidPhoneException(admin.getTelefone());

        if (admin.getNascimento() == null) throw new RequiredFieldException("Data de nascimento");

        if (admin.getCargo() == null || admin.getCargo().isBlank()) throw new RequiredFieldException("cargo");

        if (admin.getSenha().length() < 8) throw new InvalidPasswordException();
    }
}
