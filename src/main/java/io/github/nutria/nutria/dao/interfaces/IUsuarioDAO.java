package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Interface para operações específicas de CRUD na entidade {@link Usuario}.
 *
 * @see Usuario
 * @author Enzo Mota
 * @version 1.0
 */
public interface IUsuarioDAO {

    /**
     * Busca um registro de {@link Usuario} pelo email informado.
     *
     * @param email o endereço de email que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Usuario} encontrado, ou vazio se não houver correspondência.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<Usuario> buscarPorEmail(String email);

    /**
     * Lista todos os registros de {@link Usuario} com o nome ou domínio de email informado.
     *
     * @param valorBuscado o nome ou domínio de email que será utilizado na busca.
     * @return uma lista de objetos {@link Usuario} correspondentes ao valor buscado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Usuario> buscarPorNomeEmailOuEmpresa(String valorBuscado, int page);

    /**
     * Busca um registro de {@link Usuario} pelo telefone informado.
     *
     * @param fone o telefone que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Usuario} encontrado, ou vazio se não encontrado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<Usuario> buscarPorTelefone(String fone);

    /**
     * Deleta registros {@link Usuario} pela empresa.
     *
     * @param empresa o nome da empresa que será utilizado na exclusão.
     * @return {@code true} se deletar com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean deletarPorEmpresa(String empresa);

    /**
     * Conta a quantidade total de registros de {@link Usuario} correspondentes ao nome ou domínio de email buscado.
     *
     * @param valorBuscado o nome ou domínio de email que será utilizado na busca.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarTodosFiltrados(String valorBuscado);
}
