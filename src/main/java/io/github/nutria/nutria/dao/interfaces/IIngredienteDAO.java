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
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link Ingrediente} correspondentes ao nome informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Ingrediente> buscarPorNome(String nome, int page);

    /**
     * Lista todos os nomes dos registros de {@link Ingrediente}.

     * @return uma lista de {@link String} com todos os nomes encontrados.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<String> buscarNomes();

    /**
     * Deleta registros de {@link Ingrediente} pelo cargo.
     *
     * @param nome o nome do ingrediente que será utilizado na exclusão.
     * @return {@code true} se deletar com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean deletarPorNome(String nome);

    /**
     * Conta a quantidade total de registros de {@link Ingrediente} correspondentes ao nome informado.
     *
     * @param nome o nome que será utilizado na busca.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarPorNome(String nome);
}