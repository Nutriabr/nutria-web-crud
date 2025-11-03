package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Ingrediente;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.model.Usuario;

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
     * Lista todos os emails dos registros de {@link Usuario}.

     * @return uma lista de {@link String} com todos os emails encontrados.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<String> buscarEmails();

    /**
     * Lista todos os registros de {@link Produto} com o ID de {@link Produto} ou de {@link Usuario} informado.
     *
     * @param numero o ID de {@link Produto} ou {@link Usuario} que será utilizado na busca.
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link Produto} correspondentes ao ID informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Produto> buscarPorIdOuIdUsuario(Long numero, int page);

    /**
     * Lista todos os registros de {@link Produto} com o nome informado.
     *
     * @param nome o nome que será utilizado na busca.
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link Produto} correspondentes ao nome informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<Produto> buscarPorNomeProdutoOuNomeUsuario(String nome, int page);

    /**
     * Deleta todos os registros de {@link Ingrediente} pelo email de {@link Usuario} informado.
     *
     * @param email o endereço de email que será utilizado na exclusão.
     * @return {@code true} se deletar com sucesso; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean deletarPorEmailUsuario(String email);

    /**
     * Conta a quantidade total de registros de {@link Produto} com o ID de {@link Produto} ou de {@link Usuario} informado.
     *
     * @param numero o ID de {@link Produto} ou de {@link Usuario} que será utilizado na busca.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarPorIdOuIdUsuario(Long numero);

    /**
     * Conta a quantidade total de registros de {@link Produto} correspondentes ao nome informado.
     *
     * @param nome o nome que será utilizado na busca.
     * @return um inteiro com o número total de registros.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    int contarPorNomeProdutoOuNomeUsuario(String nome);
}