package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.TabelaNutricional;

import java.sql.SQLException;
import java.util.List;

public interface ITabelaNutricionalDAO {
    void save(TabelaNutricional tabelaNutricional) throws SQLException;
    List<TabelaNutricional> findAll();
    int deleteTabelaNutricionalById(long id) throws SQLException;
}
