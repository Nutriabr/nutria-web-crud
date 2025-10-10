package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Usuario;

/**
 * Interface para operações específicas de CRUD na entidade {@link Usuario}.
 *
 * @see Usuario
 * @author Luis Henrique
 * @version 1.0
 */
public interface IUsuarioDAO {

    /**
     * Verifica se um {@link Usuario} com o endereço de email informado está registrado.
     *
     * @param email o endereço de email que será utilizado na busca.
     * @return {@code true} se o email já estiver registrado no banco; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Luis Henrique
     */
    boolean findByEmailUsed(String email);
}
