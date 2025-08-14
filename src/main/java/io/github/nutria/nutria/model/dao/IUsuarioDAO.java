package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    boolean emailUsed(String email);
    void insertUser(Usuario usuario);
    List<Usuario> read();
}
