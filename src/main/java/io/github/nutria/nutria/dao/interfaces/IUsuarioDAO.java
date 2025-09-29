package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.model.Usuario;

/**
 * Interface para operações de CRUD na entidade Usuario.
 * @author Luis Henrique
 * @version 1.1
 * @see Usuario
 */
public interface IUsuarioDAO {
    boolean findByEmailUsed(String email);
}
