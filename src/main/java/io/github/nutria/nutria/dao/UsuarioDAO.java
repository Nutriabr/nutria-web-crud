package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IUsuarioDAO;
import io.github.nutria.nutria.model.FiltroInfo;
import io.github.nutria.nutria.model.FiltroUsuario;
import io.github.nutria.nutria.model.Usuario;
import io.github.nutria.nutria.util.ConnectionFactory;
import io.github.nutria.nutria.util.PasswordHasher;

import java.sql.*;
import java.util.*;

/**Classe para transferência de dados do Usuario no banco de dados
 * @author Mariana Marrão, Luis Henrique e Enzo Mota
 * @version 1.1
 * @see IUsuarioDAO
 */
public class UsuarioDAO implements GenericDAO<Usuario, Long>, IUsuarioDAO {
    private static Map<String, FiltroUsuario> filtros = FiltroUsuario.filtrosUsuarios();

    @Override
    public boolean findByEmailUsed(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";

        System.out.println(email);
        boolean result = false;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try  {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();
             if (rs.next()) {
                    /* 4. Se o resultado da função COUNT for maior que 0, significa que
                     *  o e-mail já está cadastrado
                     * */
                    result = rs.getInt(1) > 0;
                }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Filtragem
    public List<Usuario> filterBy(String nomeFiltro, int page) {
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int limit = 4;
        int offset = (page - 1) * limit;

        FiltroUsuario filtroUsuario = filtros.get(nomeFiltro);

        List<Usuario> usuarios = new ArrayList<>();

        try {
            connect = ConnectionFactory.connect();
            if (filtroUsuario.getColuna().equals("LIKE")) {
                String sql = "SELECT * FROM usuario WHERE ? LIKE ? LIMIT ? OFFSET ?";
                ps = connect.prepareStatement(sql);
                ps.setString(1, filtroUsuario.getColuna());
                ps.setString(2, filtroUsuario.getValor());
                ps.setInt(3, limit);
                ps.setInt(4, offset);
            } else if (filtroUsuario.getColuna().equals("=")) {
                String sql = "SELECT * FROM usuario WHERE ? = ? LIMIT ? OFFSET ?";
                ps = connect.prepareStatement(sql);
                ps.setString(1, filtroUsuario.getColuna());
                ps.setString(2, filtroUsuario.getValor());
                ps.setInt(3, limit);
                ps.setInt(4, offset);
            } else if (filtroUsuario.getColuna().equals("IS NOT NULL")) {
                String sql = "SELECT * FROM usuario WHERE ? IS NOT NULL LIMIT ? OFFSET ?";
                ps = connect.prepareStatement(sql);
                ps.setString(1, filtroUsuario.getColuna());
                ps.setInt(2, limit);
                ps.setInt(3, offset);
            } else {
                String sql = "SELECT * FROM usuario WHERE ? IS NULL LIMIT ? OFFSET ?";
                ps = connect.prepareStatement(sql);
                ps.setString(1, filtroUsuario.getColuna());
                ps.setInt(2, limit);
                ps.setInt(3, offset);
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
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
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

        PreparedStatement ps = null;
        Connection connect = null;
        try  {
            connect = ConnectionFactory.connect();

            ps = connect.prepareStatement(sql);

            /* 2. Inicializa uma variável hashedPassword que recebe o retorno método hashPassword
            * o método recebe como parametro o atributo de senha do objeto usuario
             */
            String hashedPassword = PasswordHasher.hashPassword(usuario.getSenha());

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());

            if (findByEmailUsed(usuario.getEmail())) {
                return false;
            }

            ps.setString(3, hashedPassword);
            ps.setString(4, usuario.getTelefone());
            ps.setString(5, usuario.getEmpresa());
            ps.setString(6, usuario.getFoto());

            result = (ps.executeUpdate() > 0);

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

        return result;
    }

    @Override
    public boolean update(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, empresa = ?, foto = ? WHERE id = ?";

        int result = 0;

        PreparedStatement pstmt = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            pstmt = connect.prepareStatement(sql);

            String hashedPassword = PasswordHasher.hashPassword(usuario.getSenha());

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            if (findByEmailUsed(usuario.getEmail())) {
                return false;
            }
            pstmt.setString(3, hashedPassword);
            pstmt.setString(4, usuario.getEmpresa());
            pstmt.setString(5, usuario.getFoto());
            pstmt.setLong(6, usuario.getId());

            result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (result > 0);
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
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usuarioArrayList;
    }


    public boolean deleteById(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        boolean result = false;

        PreparedStatement ps = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            result = (ps.executeUpdate() > 0);

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

        return totalUsuarios;
    }
}