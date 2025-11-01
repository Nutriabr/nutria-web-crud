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
     * Lista todos os registros de {@link Admin} com o domínio de email informado.
     *
     * @param dominio o domínio de email que será utilizado na busca.
     * @return um {@link Optional} contendo uma lista de objetos {@link Admin} encontrados, ou vazio se não houver correspondência.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<List<Admin>> buscarPorDominioDoEmail(String dominio);

    /**
     * Busca um registro de {@link Admin} pelo email informado.
     *
     * @param email o endereço de email que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Admin} encontrado, ou vazio se não houver correspondência.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<Admin> buscarPorEmail(String email);

    /**
     * Lista todos os registros de {@link Admin} com o nome informado.
     *
     * @param nome o nome que será utilizado na busca.
     * @return um {@link Optional} contendo uma lista de objetos {@link Admin} encontrados, ou vazio se não houver correspondência
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<List<Admin>> buscarPorNome(String nome);

    /**
     * Lista todos os registros de {@link Admin} com o nome ou domínio de email informado.
     *
     * @param valorBuscado o nome ou domínio de email que será utilizado na busca.
     * @return uma lista de objetos {@link Admin} correspondentes.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Admin> buscarPorNomeAdminOuDominioEmail(String valorBuscado, int page);

    /**
     * Busca um registro de {@link Admin} pelo telefone informado.
     *
     * @param fone o telefone que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Admin} encontrado, ou vazio se não houver correspondência.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<Admin> buscarPorTelefone(String fone);

    /**
     * Atualiza a senha de um resgistro de {@link Admin} pelo email informado.
     *
     * @param email o endereço de email de {@link Admin} que será utilizado para o envio de um código.
     * @param senha a nova senha que será utilizada.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    void alterarSenhaPeloEmail(String email, String senha);

    /**
     * Conta a quantidade total de registros de {@link Admin} correspondentes ao nome ou domínio de email buscado.
     *
     * @param valorBuscado o nome ou domínio de email que será utilizado na busca.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarTodosFiltrados(String valorBuscado);
}
