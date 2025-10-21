package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface genérica para operações de CRUD.
 *
 * @author Luis Henrique
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
     * @author Mariana Marrão
     */
    boolean inserir(T entity);

    /**
     * Lista todos os registros de {@link T}.
     *
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link T} com todos os registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Mariana Marrão
     */
    List<T> buscarTodos(int page);

    /**
     * Atualiza os valores de um objeto {@link T}.
     *
     * @param entity o objeto que será atualizado.
     * @return {@code true} se for atualizado com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Luis Henrique
     */
    boolean alterar(T entity);

    /**
     * Deleta um objeto {@link T} pelo ID.
     *
     * @param id o ID do objeto que será deletado.
     * @return {@code true} se for deletado com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Luis Henrique
     */
    boolean deletarPorId(Long id);

    /**
     * Conta a quantidade total de registros de {@link T}.
     *
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Enzo Mota
     */
    int contarTodos();
}
