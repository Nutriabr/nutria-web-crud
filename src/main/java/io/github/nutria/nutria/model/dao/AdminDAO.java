package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Admin;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminDAO implements IAdminDAO {
    ConnectionFactory factory = new ConnectionFactory();
    public void save(Admin admin) {

    }
}
