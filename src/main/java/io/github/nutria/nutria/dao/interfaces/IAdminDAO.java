package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Admin;

import java.util.Optional;

/**
 * Interface para operações específicas de CRUD na entidade {@link Admin}.
 *
 * @see Admin
 * @author Luis Henrique
 * @version 1.0
 */
public interface IAdminDAO {

    /**
     * Busca um registro de {@link Admin} pelo email informado.
     *
     * @param email o endereço de email que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Admin} correspondente, ou vazio se não encontrado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Luis Henrique
     */
    Optional<Admin> buscarPorEmail(String email);

    /**
     * Busca um registro de {@link Admin} pelo email informado.
     *
     * @param telefone o telefone que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Admin} correspondente, ou vazio se não encontrado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Luis Henrique
     */
     Optional<Admin> buscarPorTelefone(String telefone);

}
