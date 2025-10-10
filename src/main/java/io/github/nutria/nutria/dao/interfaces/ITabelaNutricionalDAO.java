package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.FiltroNutricional;
import io.github.nutria.nutria.model.TabelaNutricional;

import java.util.List;

/**
 * Interface para operações específicas de CRUD na entidade {@link TabelaNutricional}.
 *
 * @see TabelaNutricional
 * @see FiltroNutricional
 * @author Giovanna Santos
 * @version 1.0
 */
public interface ITabelaNutricionalDAO {

    /**
     * Filtra os registros de {@link TabelaNutricional} de acordo com um filtro pré-definido.
     * <p>
     * Os filtros disponíveis são definidos na implementação da DAO, em um mapa constante
     * que associa o nome do filtro às suas condições.
     *
     * @param nomeFiltro o nome do filtro a ser aplicado (ex.: "baixo_sodio", "rico_fibras").
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link TabelaNutricional} que atendem aos critérios do filtro.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Giovanna Santos
     */
    List<TabelaNutricional> filterBy(String nomeFiltro, int page);

    /**
     * Filtra os registros de {@link TabelaNutricional} por valor mínimo, máximo ou intervalo de quantidade de determinado nutriente.
     *
     * @param type o tipo de comparação ou intervalo (ex.: "min", "max", "range").
     * @param column o nome da coluna/nutriente a ser filtrado.
     * @param minValue a quantidade mínima do nutriente.
     * @param maxValue a quantidade máxima do nutriente.
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link TabelaNutricional} que atendem aos critérios do filtro.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     * @author Giovanna Santos
     */
    List<TabelaNutricional> findByNutrientRange(String type, String column, double minValue, double maxValue, int page);
}
