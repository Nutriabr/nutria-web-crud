package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    boolean findByEmailUsed(String email);
    void save(Usuario usuario);
    List<Usuario> findAll();
    int deleteUserById(long id) throws SQLException;
}
