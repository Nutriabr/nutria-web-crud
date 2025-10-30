package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Interface para operações específicas de CRUD na entidade {@link Usuario}.
 *
 * @see Usuario
 * @author Enzo Mota
 * @version 1.0
 */
public interface IUsuarioDAO {

    /**
     * Busca um registro de {@link Usuario} pelo email informado.
     *
     * @param email o endereço de email que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Usuario} correspondente ao email informado, ou vazio se não encontrado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<Usuario> buscarPorEmail(String email);

    /**
     * Adicionar documentação
     */
    List<Usuario> buscarPorNomeDeUsuario(String nomeFiltro, String valorBuscado, int page);

    /**
     * Adicionar documentação
     */
    List<Usuario> buscarPorNomeEmailOuEmpresa(String valorBuscado, int page);

    /**
     * Busca um registro de {@link Usuario} pelo telefone informado.
     *
     * @param fone o telefone que será utilizado na busca.
     * @return um {@link Optional} contendo o {@link Usuario} correspondente ao telefone informado, ou vazio se não encontrado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    Optional<Usuario> buscarPorTelefone(String fone);

    /**
     * Verifica se há um registro de {@link Usuario} com o telefone informado.
     *
     * @param fone o telefone que será utilizado na busca.
     * @return {@code true} se houver um registro; {@code false} caso contrário.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean buscarPorTelefoneUsado(String fone);

    /**
     * Adicionar documentação
     */
    int contarTodosFiltrados(String valorBuscado);
}
