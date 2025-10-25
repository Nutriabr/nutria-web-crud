package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Admin;

import java.util.List;
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
     */
    Optional<Admin> buscarPorEmail(String email);

    /**
     * Lista todos os registros de {@link Admin} com o nome informado.
     *
     * @param nome o nome que será utilizado na busca.
     * @return um {@link Optional} contendo uma lista de objetos {@link Admin} correspondentes ao nome informado, ou vazio se não encontrado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<List<Admin>> buscarPorNome(String nome);

    /**
     * Busca um registro de {@link Admin} pelo telefone informado.
     *
     * @param fone o telefone que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Admin} correspondente ao telefone informado, ou vazio se não encontrado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<Admin> buscarPorTelefone(String fone);
}
