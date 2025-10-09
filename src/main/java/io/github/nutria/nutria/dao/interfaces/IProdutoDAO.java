package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.model.Produto;

import java.util.List;

/**
 * Interface para operações de CRUD na entidade Produto.
 * @author Mariana Marrão
 * @version 1.1
 * @see Produto
 */
public interface IProdutoDAO {
    /**
     * Método para achar pelo nome
     * @param nome Recebe como o parametro o nome que será usado na clausura where na query
     * @return List<Produto> Retorna a lista de todas os produtos achadas no banco com determinada nome
     */
    List<Produto> findByNome(String nome);
}