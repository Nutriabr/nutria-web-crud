package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.model.Receita;

import java.util.List;

/**
 * Interface para operações específicas de CRUD na entidade {@link Receita}.
 *
 * @see Receita
 * @author Mariana Marrão
 * @version 1.0
 */
public interface IReceitaDAO {

    /**
     * Lista todos os IDs dos registros de {@link Produto}.

     * @return uma lista de {@link Long} com todos os IDs encontrados.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Long> buscarIdProduto();

    /**
     * Lista todos os registros de {@link Receita} com o ID de {@link Receita} ou de {@link Produto} informado.
     *
     * @param filtro o ID de {@link Receita} ou de {@link Produto} que será utilizado na busca.
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link Receita} correspondentes ao ID informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
   List<Receita> buscarPorIdOuIdProduto(Long filtro, int page);

    /**
     * Lista todos os registros de {@link Receita} com a porção informada.
     *
     * @param porcao a porção que será utilizada na busca.
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link Receita} correspondentes à porção informada.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Receita> buscarPorPorcao(String porcao, int page);

    /**
     * Deleta todos os registros de {@link Receita} pelo ID de {@link Produto} informado.
     *
     * @param idProduto o ID de {@link Produto} que será utilizado na exclusão.
     * @return {@code true} se deletar com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean deletarPorIdProduto(Long idProduto);

    /**
     * Conta a quantidade total de registros de {@link Receita} com o ID de {@link Receita} ou de {@link Produto} informado.
     *
     * @param filtro o ID de {@link Receita} ou de {@link Produto} que será utilizado na busca.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarPorIdOuIdProduto(Long filtro);

    /**
     * Conta a quantidade total de registros de {@link Receita} com a porção informada.
     *
     * @param porcao a porção que será utilizada na busca.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarPorPorcao(String porcao);
}