package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Admin;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminDAO implements IAdminDAO {
    ConnectionFactory factory = new ConnectionFactory();
    public void insertAdmin(Admin admin) {
        try (Connection connect = factory.connect()) {
            String sql = "INSERT INTO admin(nome, ";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
