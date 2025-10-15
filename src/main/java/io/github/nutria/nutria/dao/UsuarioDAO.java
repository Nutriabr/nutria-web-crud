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
import java.util.*;

/**Classe para transferência de dados do Usuario no banco de dados
 * @author Mariana Marrão, Luis Henrique e Enzo Mota
 * @version 1.1
 * @see IUsuarioDAO
 */
@SuppressWarnings("ALL")
public class UsuarioDAO implements GenericDAO<Usuario, Long>, IUsuarioDAO {
    private final static Map<String, FiltroUsuario> filtros = FiltroUsuario.filtrosUsuarios();

    public Optional<Usuario> findByPhone(String phone) {
        String sql = "SELECT * FROM usuario WHERE telefone = ?";

        if (phone == null || phone.isBlank()) {
            throw new RequiredFieldException("telefone");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setString(1, phone);

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
            System.err.println("[DAO ERROR] Erro ao buscar usuário pelo telefone: " + phone);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar usuário pelo telefone", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return Optional.empty();
    }

    public boolean findByPhoneUsed(String phone) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE telefone = ?";

        boolean result = false;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setString(1, phone);

            rs = ps.executeQuery();

            if (rs.next()) {
                /* 4. Se o resultado da função COUNT for maior que 0, significa que
                 *  o telefone já está cadastrado
                 * */
                result = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar os telefones usados por usuario: " + phone);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar telefones usados", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return result;
    }

    public List<Usuario> findAllFilterBy(String nomeFiltro, String valorBuscado, int page) {
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int limit = 4;
        int offset = (page - 1) * limit;

        FiltroUsuario filtroUsuario = filtros.get(nomeFiltro);

        FiltroUsuario.setValor(valorBuscado);

        List<Usuario> usuarios = new ArrayList<>();

        try {
            connect = ConnectionFactory.connect();

            String sql = "SELECT * FROM usuario WHERE ? LIKE ? LIMIT ? OFFSET ?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, filtroUsuario.getColuna());
            ps.setString(2, filtroUsuario.getValor());
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
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connect != null) ConnectionFactory.disconnect(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        return usuarios;
}

    public List<Usuario> findByUsername(String nomeFiltro, String valorBuscado, int page) {
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int limit = 4;
        int offset = (page - 1) * limit;

        FiltroUsuario filtroUsuario = filtros.get(nomeFiltro);

        FiltroUsuario.setValor(valorBuscado);

        List<Usuario> usuarios = new ArrayList<>();

        try {
            connect = ConnectionFactory.connect();

            switch (nomeFiltro) {
                case "nome_usuario" -> {
                    String sql = "SELECT * FROM usuario WHERE ? LIKE ? LIMIT ? OFFSET ?";
                    ps = connect.prepareStatement(sql);
                    ps.setString(1, filtroUsuario.getColuna());
                    ps.setString(2, filtroUsuario.getValor());
                    ps.setInt(3, limit);
                    ps.setInt(4, offset);
                }
                case "email_usuario" -> {
                    String sql = "SELECT * FROM usuario WHERE ? = ? LIMIT ? OFFSET ?";
                    ps = connect.prepareStatement(sql);
                    ps.setString(1, filtroUsuario.getColuna());
                    ps.setString(2, filtroUsuario.getValor());
                    ps.setInt(3, limit);
                    ps.setInt(4, offset);
                }
                case "dominio_email_usuario" -> {
                    String sql = "SELECT * FROM usuario WHERE ? LIKE LIMIT ? OFFSET ?";
                    ps = connect.prepareStatement(sql);
                    ps.setString(1, filtroUsuario.getColuna());
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                }
                case "empresa" -> {
                    String sql = "SELECT * FROM usuario WHERE ? LIKE ? OFFSET ?";
                    ps = connect.prepareStatement(sql);
                    ps.setString(1, filtroUsuario.getColuna());
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                }
                case "com_foto" -> {
                    String sql = "SELECT * FROM usuario WHERE ? IS NOT NULL LIMIT ? OFFSET ?";
                    ps = connect.prepareStatement(sql);
                    ps.setString(1, filtroUsuario.getColuna());
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                }
                case "sem_foto" -> {
                    String sql = "SELECT * FROM usuario WHERE ? IS NULL LIMIT ? OFFSET ?";
                    ps = connect.prepareStatement(sql);
                    ps.setString(1, filtroUsuario.getColuna());
                    ps.setInt(2, limit);
                    ps.setInt(3, offset);
                }
            }

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
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuarios;
    }

    public boolean insert(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, telefone, empresa, foto) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        boolean result = false;

        validateUser(usuario);


        PreparedStatement ps = null;
        Connection connect = null;
        try  {
            if (FieldUsedValidator.isFieldUsed("usuario", "email", usuario.getEmail())) throw new DuplicateEmailException(usuario.getEmail());
            if (FieldUsedValidator.isFieldUsed("usuario","telefone", usuario.getTelefone())) throw new DuplicatePhoneException(usuario.getTelefone());

            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);

            /* 2. Inicializa uma variável hashedPassword que recebe o retorno método hashPassword
            * o método recebe como parametro o atributo de senha do objeto usuario
             */
            String hashedPassword = PasswordHasher.hashPassword(usuario.getSenha());

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
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return result;
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?, senha = ?, empresa = ?, foto = ? WHERE id = ?";

        int result = 0;

        if (usuario.getId() == null || usuario.getId() <= 0) {
            throw new ValidationException("ID é obrigatório para atualização");
        }

        findById(usuario.getId());

        Optional<Usuario> existingUser = findByEmail(usuario.getEmail());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(usuario.getId())) {
            throw new DuplicateEmailException(usuario.getEmail());
        }

        PreparedStatement pstmt = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            pstmt = connect.prepareStatement(sql);

            String hashedPassword = PasswordHasher.hashPassword(usuario.getSenha());

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
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return (result > 0);
    }

    public Optional<Usuario> findByEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email = ?";

        if (email == null || email.isBlank()) {
            throw new RequiredFieldException("email");
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
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
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar usuario por email: " + email);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar usuario", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return Optional.empty();
    }


    public List<Usuario> findAll(int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM usuario LIMIT ? OFFSET ?";

        List<Usuario> usuarioArrayList = new ArrayList<>();

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
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return usuarioArrayList;
    }


    public boolean deleteById(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        boolean result = false;

        PreparedStatement ps = null;
        Connection connect = null;

        if (id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            result = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar o usuario: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar usuario", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        return result;
    }

    @Override
    public int countAll() {
        int totalUsuarios = 0;

        String sql = "SELECT COUNT(*) FROM usuario";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();

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
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalUsuarios;
    }

    public Usuario findById(long id) {
        if (id <= 0) {
            throw new InvalidNumberException("id", "ID deve ser maior que zero");
        }

        String sql = "SELECT * FROM usuario WHERE id = ?";

        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connect = ConnectionFactory.connect();
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
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private void validateUser(Usuario usuario) {
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

        if (!isValidEmail(usuario.getEmail())) {
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