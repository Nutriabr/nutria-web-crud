package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.IAdminDAO;
import io.github.nutria.nutria.dao.interfaces.IUsuarioDAO;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.util.ConnectionFactory;
import io.github.nutria.nutria.util.PasswordHasher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**Classe para transferência de dados do Admin no banco de dados
 * @author Luis Henrique
 * @version 1.0
 * @see IAdminDAO
 * @see GenericDAO
 */
public class AdminDAO implements GenericDAO<Admin, Long>, IAdminDAO {
    public boolean findByEmailUsed(String email) {
        String sql = "SELECT COUNT(*) FROM admin WHERE email = ?";

        boolean result = false;

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
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

    public Admin findByEmail(String email) {
        String sql = "SELECT * FROM admin WHERE email = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getLong("id"));
                admin.setNome(rs.getString("nome"));
                admin.setEmail(rs.getString("email"));
                admin.setSenha(rs.getString("senha"));
                return admin;
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
        return null;
    }

    public boolean insert(Admin admin) {
        String sql = "INSERT INTO admin (nome, email, senha) VALUES (?, ?, ?)";

        Connection connect = null;
        PreparedStatement ps = null;

        try {
            if (findByEmailUsed(admin.getEmail())) {
                return false;
            }

            connect = ConnectionFactory.connect(); // Obtém dentro do método
            ps = connect.prepareStatement(sql);

            String hashedPassword = PasswordHasher.hashPassword(admin.getSenha());

            ps.setString(1, admin.getNome());
            ps.setString(2, admin.getEmail());
            ps.setString(3, hashedPassword);

            return (ps.executeUpdate() > 0);

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
    public boolean update(Admin admin) {
        String sql = "UPDATE admin SET nome = ?, email = ?, senha = ? WHERE id = ?";

        int result = 0;

        PreparedStatement pstmt = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            pstmt = connect.prepareStatement(sql);

            String hashedPassword = PasswordHasher.hashPassword(admin.getSenha());

            pstmt.setString(1, admin.getNome());
            pstmt.setString(2, admin.getEmail());
            pstmt.setString(3, hashedPassword);
            pstmt.setLong(4, admin.getId());

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

    @Override
    public List<Admin> findAll(int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM admin LIMIT ? OFFSET ?";

        List<Admin> adminArrayList = new ArrayList<Admin>();

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
                Admin admin = new Admin(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                );

                adminArrayList.add(admin);
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
        return adminArrayList;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM admin WHERE id = ?";

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
        int totalAdmins = 0;

        String sql = "SELECT COUNT(*) FROM admin";

        Statement stmt = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();

            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                totalAdmins = rs.getInt(1);
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

        return totalAdmins;
    }

}
