package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.ReceitaIngrediente;

import java.util.List;

public interface IReceitaIngredienteDAO {

    /**
     * Filtra os registros de {@link ReceitaIngrediente} por quantidade máxima.
     *
     * @param page o número da página de resultados (para paginação).
     * @param quant a quantidade máxima que será utilizada na busca.
     * @return uma lista de objetos {@link ReceitaIngrediente} que atendem à quantidade máxima informada.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<ReceitaIngrediente> buscarPorMaiorQuant(int page, double quant);

    /**
     * Filtra os registros de {@link ReceitaIngrediente} por quantidade mínima.
     *
     * @param page o número da página de resultados (para paginação).
     * @param quant a quantidade mínima que será utilizada na busca.
     * @return uma lista de objetos {@link ReceitaIngrediente} que atendem à quantidade mínima informada.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<ReceitaIngrediente> buscarPorMenorQuant(int page, double quant);

    /**
     * Filtra os registros de {@link ReceitaIngrediente} por intervalo de quantidade.
     *
     * @param page o número da página de resultados (para paginação).
     * @param quantMin a quantidade mínima do intervalo.
     * @param quantMax a quantidade máxima do intervalo.
     * @return uma lista de objetos {@link ReceitaIngrediente} que atendem ao intervalo de quantidade informado.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<ReceitaIngrediente> buscarPorIntervalo(int page, double quantMin, double quantMax);
}
