package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Produto;

import java.util.List;

/**
 * Interface para operações específicas de CRUD na entidade {@link Produto}.
 *
 * @see Produto
 * @author Mariana Marrão
 * @version 1.0
 */
public interface IProdutoDAO {

    /**
     * Busca registros de {@link Produto} com o nome informado.
     *
     * @param nome o nome que será utilizado na busca.
     * @return uma lista de objetos {@link Produto} correspondentes ao nome informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Mariana Marrão
     */
    List<Produto> findByNome(String nome);
}