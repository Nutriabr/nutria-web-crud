package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.model.Receita;

import java.util.List;

/**
 * Interface para operações de CRUD na entidade Receita.
 * @author Mariana Marrão
 * @version 1.1
 * @see Receita
 */
public interface IReceitaDAO {
    /**
     * Método para achar receitas pela porção
     * @param porcao Recebe como o parametro a porção que será usado na clausura where na query
     * @return List<Receita> Retorna a lista de todas as receitas achadas no banco com determinada porção
     */
    List<Receita> findByPorcao(String porcao);
}