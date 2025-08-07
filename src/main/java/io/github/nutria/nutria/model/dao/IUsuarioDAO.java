package main.java.io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Usuario;

public interface IUsuarioDAO {
    boolean emailUsed(String email);
    void insertUser(Usuario usuario);
}
