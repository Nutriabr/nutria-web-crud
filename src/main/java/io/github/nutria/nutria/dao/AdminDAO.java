package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.model.Admin;

import java.sql.SQLException;
import java.util.List;

public class AdminDAO implements GenericDAO<Admin, Long>, AutoCloseable {

    @Override
    public boolean insert(Admin entity) {
        return false;
    }

    @Override
    public List<Admin> findAll() {
        return List.of();
    }

    @Override
    public boolean deleteById(Long aLong) throws SQLException {
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}
