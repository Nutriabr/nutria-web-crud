package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.model.Usuario;
import io.github.nutria.nutria.util.ConnectionFactory;
import io.github.nutria.nutria.util.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAO {
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
            // 2. Executar a consulta
            rs = ps.executeQuery();

            // 3. Verificar se há linhas encontradas
            if (rs.next()) {
                /* 4. Se o resultado da função COUNT for maior que 0, significa que
                 *  o e-mail já está cadastrado
                 * */
                result = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            // 5. Tratar exceções de SQL
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }

                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
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

}
