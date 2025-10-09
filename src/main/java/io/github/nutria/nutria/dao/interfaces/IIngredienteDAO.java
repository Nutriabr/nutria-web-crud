package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.model.Ingrediente;

import java.util.List;

/**
 * Interface para operações de CRUD na entidade Produto.
 * @author Mariana Marrão
 * @version 1.1
 * @see Ingrediente
 */
public interface IIngredienteDAO {
    /**
     * Método para achar  pelo nome
     * @param nome Recebe como o parametro o nome que será usado na clausura where na query
     * @return List<Ingrediente> Retorna a lista de todas os ingredientes achadas no banco com determinada nome
     */
    List<Ingrediente> findByNome(String nome);
}