package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Ingrediente;

import java.util.List;

/**
 * Interface para operações específicas de CRUD na entidade {@link Ingrediente}.
 *
 * @see Ingrediente
 * @author Mariana Marrão
 * @version 1.0
 */
public interface IIngredienteDAO {

    /**
     * Lista todos os registros de {@link Ingrediente} com o nome informado.
     *
     * @param nome o nome que será utilizado na busca.
     * @return uma lista de objetos {@link Ingrediente} correspondentes ao nome informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Ingrediente> buscarPorNome(String nome);
}