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
     * Lista todos os registros de {@link Receita} com o ID de {@link Produto} informado ou ID da própria {@link  Receita}.
     *
     * @param filtro o número que será utilizado na busca.
     * @return uma lista de objetos {@link Receita} correspondentes ao ID informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
   List<Receita> buscarPorIdOuIdProduto(Long filtro, int page);

    /**
     * Lista todos os registros de {@link Receita} com a porção informada.
     *
     * @param porcao a porção que será utilizada na busca.
     * @return uma lista de objetos {@link Receita} correspondentes à porção informada.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Receita> buscarPorPorcao(String porcao, int page);

    /**
     * Conta a quantidade de registros de {@link Receita} com o ID informado.
     *
     * @param id o ID que será utilizado para contagem.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarPorId(Long id);

    /**
     * Conta a quantidade de registros de {@link Receita} com o ID de {@link Produto} informado ou o ID da própria {@link Receita}.
     *
     * @param filtro que será utilizado para contagem.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarPorIdOuIdProduto(Long filtro);

    /**
     * Conta a quantidade de registros de {@link Receita} com a porção informada.
     *
     * @param porcao a porção que será utilizada para contagem.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarPorPorcao(String porcao);
}