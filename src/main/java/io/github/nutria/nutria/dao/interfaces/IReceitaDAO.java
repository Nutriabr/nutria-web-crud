package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
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
     * Busca registros de {@link Receita} com a porção informada.
     *
     * @param porcao a porção que será utilizada na busca.
     * @return uma lista de objetos {@link Receita} correspondentes à porção informada.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Mariana Marrão
     */
    List<Receita> buscarPorPorcao(String porcao);
}