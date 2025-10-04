package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.model.Admin;

import java.sql.SQLException;
import java.util.List;

public class AdminDAO implements GenericDAO<Admin, Long> {

    @Override
    public boolean insert(Admin admin) {
        return false;
    }

    @Override
    public List<Admin> findAll(int page) {
        return List.of();
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        return false;
    }

}
