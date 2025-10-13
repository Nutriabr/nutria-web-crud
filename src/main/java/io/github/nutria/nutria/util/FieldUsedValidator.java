package io.github.nutria.nutria.util;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FieldUsedValidator {
    /**
     * Verifica se a entidade com o campo informado está registrado.
     *
     * @param table A tabela que será utilizada na busca.
     * @param field o campo que será utilizado na busca.
     * @param value o valor da clausula que será utilizado na busca.
     * @return {@code true} se o valor já estiver registrado no banco; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Luis Henrique
     */
    public static boolean isFieldUsed(String table, String field, String value) {
        String sql = "SELECT COUNT(*) FROM " + table+ " WHERE " + field + " = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;
        try  {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setString(1, value);

            rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new DataAccessException(" Erro ao verificar valor duplicado: ", e);
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
}
