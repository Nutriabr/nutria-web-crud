package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IUsuarioDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.FiltroUsuario;
import io.github.nutria.nutria.model.Usuario;
import io.github.nutria.nutria.util.ConnectionFactory;
import io.github.nutria.nutria.util.FieldUsedValidator;
import io.github.nutria.nutria.util.PasswordHasher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe de acesso a dados (DAO) para a entidade {@link Usuario}.
 * <p>
 * Implementa as operações de CRUD e métodos personalizados.
 *
 * @see GenericDAO
 * @see IUsuarioDAO
 * @see Usuario
 * @author Enzo Mota
 * @author Luis Henrique
 * @author Mariana Marrão
 * @version 1.0
 */
public class UsuarioDAO implements GenericDAO<Usuario, Long>, IUsuarioDAO {
    private final static Map<String, FiltroUsuario> FILTROS = FiltroUsuario.filtrosUsuarios();

    @Override
    public boolean inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, empresa, foto) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        boolean result = false;

        validarUsuario(usuario);


        PreparedStatement ps = null;
        Connection connect = null;
        try  {
            if (FieldUsedValidator.ehCampoEmUso("usuario", "email", usuario.getEmail())) throw new DuplicateEmailException(usuario.getEmail());
            if (FieldUsedValidator.ehCampoEmUso("usuario","telefone", usuario.getTelefone())) throw new DuplicatePhoneException(usuario.getTelefone());

            connect = ConnectionFactory.conectar();

            ps = connect.prepareStatement(sql);

            /* 2. Inicializa uma variável hashedPassword que recebe o retorno método hashPassword
             * o método recebe como parametro o atributo de senha do objeto usuario
             */
            String hashedPassword = PasswordHasher.hashSenha(usuario.getSenha());

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, hashedPassword);
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getEmpresa());
            ps.setString(6, usuario.getFoto());

            result = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao salvar usuário: " + usuario.getEmail());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao salvar usuario", e);
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
    public List<Usuario> buscarTodos(int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM usuario LIMIT ? OFFSET ?";

        List<Usuario> usuarioArrayList = new ArrayList<>();

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
                Usuario usuario = new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getString("empresa"),
                        rs.getString("foto")
                );

                usuarioArrayList.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar por todos os usuarios");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar pelos usuarios", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return usuarioArrayList;
    }

    @Override
    public Usuario buscarPorId(Long id) {
        if (id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        String sql = "SELECT * FROM usuario WHERE id = ?";

        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmpresa(rs.getString("empresa"));
                usuario.setFoto(rs.getString("foto"));

                return usuario;
            } else {
                throw new EntityNotFoundException("Usuario", id);
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
    public Optional<Usuario> buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmpresa(rs.getString("empresa"));
                usuario.setFoto(rs.getString("foto"));

                return Optional.of(usuario);
            } else {
                throw new EntityNotFoundException("Usuario", email);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário pelo Email: " + email);
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
    public List<Usuario> buscarPorNomeDeUsuario(String nomeFiltro, String valorBuscado, int page) {
        return List.of();
    }

    @Override
    public List<Usuario> buscarPorNomeDeUsuarioOuDominioEmail(String valorBuscado, int page) {
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int limit = 4;
        int offset = (page - 1) * limit;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            connect = ConnectionFactory.conectar();

            String sql = "SELECT * FROM usuario WHERE nome LIKE ? OR email LIKE ? LIMIT ? OFFSET ?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, "%" + valorBuscado + "%");
            ps.setString(2, "%" + valorBuscado + "%");
            ps.setInt(3, limit);
            ps.setInt(4, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getString("empresa"),
                        rs.getString("foto")
                );

                usuarios.add(usuario);
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

        return usuarios;
    }

    @Override
    public Optional<Usuario> buscarPorTelefone(String fone) {
        String sql = "SELECT * FROM usuario WHERE telefone = ?";

        if (fone == null || fone.isBlank()) {
            throw new RequiredFieldException("telefone");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, fone);

            rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone"),
                        rs.getString("empresa"),
                        rs.getString("foto")
                );

                return Optional.of(usuario);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuário pelo telefone: " + fone);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar usuário pelo telefone", e);
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
    public boolean buscarPorTelefoneUsado(String fone) {
        return false;
    }

    @Override
    public boolean alterar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, senha = ?, empresa = ?, foto = ? WHERE id = ?";

        int result = 0;

        if (usuario.getId() == null || usuario.getId() <= 0) {
            throw new ValidationException("ID é obrigatório para atualização");
        }

        buscarPorId(usuario.getId());

        Optional<Usuario> existingUser = buscarPorEmail(usuario.getEmail());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(usuario.getId())) {
            throw new DuplicateEmailException(usuario.getEmail());
        }

        PreparedStatement pstmt = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.conectar();
            pstmt = connect.prepareStatement(sql);

            String hashedPassword = PasswordHasher.hashSenha(usuario.getSenha());

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setString(4, hashedPassword);
            pstmt.setString(5, usuario.getEmpresa());
            pstmt.setString(6, usuario.getFoto());
            pstmt.setLong(7, usuario.getId());

            result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException sqle) {
            System.err.println("[DAO ERROR] Erro ao atualizar o usuário: " + usuario.getId());
            sqle.printStackTrace(System.err);
            throw new DataAccessException("Erro ao atualizar usuário", sqle);
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
    public boolean deletarPorId(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        boolean result = false;

        PreparedStatement ps = null;
        Connection connect = null;

        if (id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            result = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar o usuario: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar usuario", e);
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

    public boolean deletarPorEmpresa(String empresa) {
        String sql = "DELETE FROM usuario WHERE empresa = ?";

        boolean result = false;

        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();
            ps = connect.prepareStatement(sql);
            ps.setString(1, empresa);

            result = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar os usuarios: " + empresa);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar usuario", e);
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

//    public boolean deletarPorDominioDeEmail(String dominioEmail) {
//        String sql = "DELETE FROM usuario WHERE email LIKE ?";
//
//        boolean result = false;
//
//        PreparedStatement ps = null;
//        Connection connect = null;
//
//        try {
//            connect = ConnectionFactory.conectar();
//            ps = connect.prepareStatement(sql);
//            ps.setString(1, dominioEmail);
//
//            result = (ps.executeUpdate() > 0);
//
//        } catch (SQLException e) {
//            System.err.println("[DAO ERROR] Erro ao deletar os usuarios: " + dominioEmail);
//            e.printStackTrace(System.err);
//            throw new DataAccessException("Erro ao deletar usuarios", e);
//        } finally {
//            try {
//                if (connect != null) ConnectionFactory.desconectar(connect);
//                if (ps != null) ps.close();
//            } catch (SQLException e) {
//                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
//            }
//        }
//        return result;
//    }

    @Override
    public int contarTodos() {
        int totalUsuarios = 0;

        String sql = "SELECT COUNT(*) FROM usuario";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.conectar();

            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalUsuarios = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao realizar a contagem total de usuarios");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a contagem total de usuarios", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.desconectar(connect);
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalUsuarios;
    }

    @Override
    public int contarTodosFiltrados(String valorBuscado) {
        int totalUsuarios = 0;

        String sql = "SELECT COUNT(*) FROM usuario WHERE email LIKE ? OR nome LIKE ?";

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
                totalUsuarios = rs.getInt(1);
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

        return totalUsuarios;
    }

    /**
     * Valida campos obrigatórios de um {@link Usuario}.
     *
     * @param email o email que será validado.
     * @return {@code true} se for válido; {@code false} caso contrário.
     */
    private boolean emailEhValido(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    /**
     * Valida campos obrigatórios de um {@link Usuario}.
     *
     * @param usuario o objeto {@link Usuario} que será validado.
     * @throws ValidationException se o objeto for {@code null}.
     * @throws RequiredFieldException se determinado campo obrigatório for {@code null} ou vazio.
     * @throws InvalidEmailException se o email do {@link Usuario} for inválido.
     * @throws InvalidPasswordException se a senha do {@link Usuario} tiver menos de 8 caracteres.
     */
    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new ValidationException("Usuario não pode ser nulo");
        }

        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new RequiredFieldException("nome");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new RequiredFieldException("email");
        }

        if (usuario.getTelefone() == null || usuario.getTelefone().isBlank()) {
            throw new RequiredFieldException("telefone");
        }

        if (!emailEhValido(usuario.getEmail())) {
            throw new InvalidEmailException(usuario.getEmail());
        }

        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new RequiredFieldException("senha");
        }

        if (usuario.getSenha().length() < 8) {
            throw new InvalidPasswordException();
        }
    }
}