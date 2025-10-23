package io.github.nutria.nutria.dao.interfaces;

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

    Optional<Usuario> buscarPorEmail(String email);

    List<Usuario> buscarPorNomeDeUsuario(String nomeFiltro, String valorBuscado, int page);

    Optional<Usuario> buscarPorTelefone(String fone);

    boolean buscarPorTelefoneUsado(String fone);

    List<Usuario> buscarTodosFiltrado(String nomeFiltro, String valorBuscado, int page);
}
