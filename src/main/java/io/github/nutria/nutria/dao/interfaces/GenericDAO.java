package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;

import java.sql.SQLException;
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
     * @param entity o objeto a ser inserido.
     * @return {@code true} se for inserido com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Mariana Marrão
     */
    boolean insert(T entity);

    boolean update(T entity);

    /**
     * Lista todos os registros de {@link T}.
     *
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link T} com todos os registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Mariana Marrão
     */
    List<T> findAll(int page);

    /**
     * Deleta um objeto {@link T} pelo ID.
     *
     * @param id o ID do objeto que será deletado.
     * @return {@code true} se deletar o objeto; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Luis Henrique
     */
    boolean deleteById(Long id) throws SQLException;

    /**
     * Conta a quantidade total de registros de {@link T}.
     *
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Enzo Mota
     */
    int countAll();
}
