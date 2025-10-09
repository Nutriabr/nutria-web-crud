package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.model.Ingrediente;
import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.util.ConnectionFactory;

// Importações necessárias para operações com JDBC e manipulação de listas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO implements GenericDAO<Ingrediente, Long> {
    @Override
    public boolean insert(Ingrediente ingrediente){
        String sql = "INSERT INTO ingrediente (id,nome) VALUES (?,?)";

        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setLong(1,ingrediente.getId());
            ps.setString(2,ingrediente.getNome());

            int result = ps.executeUpdate();
            return (result > 0);
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        } finally {
            try{
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}
