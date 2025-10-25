package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface genérica para operações de CRUD.
 *
 * @author Enzo Mota
 * @author Giovanna Santos
 * @author Luis Henrique
 * @author Mariana Marrão
 * @version 1.0
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo da Chave Primária (key) da entidade.
 */
public interface GenericDAO<T, ID> {

    /**
     * Insere um novo objeto {@link T}.
     *
     * @param entity o objeto que será inserido.
     * @return {@code true} se for inserido com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean inserir(T entity);

    /**
     * Lista todos os registros de {@link T}.
     *
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link T} com todos os registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<T> buscarTodos(int page);

    /**
     * Busca um registro de {@link T} pelo ID informado.
     *
     * @param id o ID que será utilizado na busca.
     * @return um objeto {@link T} correspondente ao ID informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    T buscarPorId(Long id);

    /**
     * Atualiza os valores de um objeto {@link T}.
     *
     * @param entity o objeto que será atualizado.
     * @return {@code true} se for atualizado com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean alterar(T entity);

    /**
     * Deleta um objeto {@link T} pelo ID.
     *
     * @param id o ID do objeto que será deletado.
     * @return {@code true} se for deletado com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean deletarPorId(Long id);

    /**
     * Conta a quantidade total de registros de {@link T}.
     *
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarTodos();
}
