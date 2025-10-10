package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.model.TabelaNutricional;
import io.github.nutria.nutria.model.FiltroNutricional;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de CRUD na entidade {@link TabelaNutricional}.
 *
 * @see TabelaNutricional
 * @see FiltroNutricional
 * @author Giovanna Santos
 * @version 1.1
 */
public interface ITabelaNutricionalDAO {

    /** Método filterBy: filtra os registros da tabela nutricional com base em um critério e paginação.
     * Os filtros válidos estão definidos na classe {@link FiltroNutricional}.
     *
     * @param nomeFiltro Filtro aplicado a um critério nutricional (ex.: "alto em sódio", "rico em fibras").
     * @param page Número da página de resultados (para paginação).
     * @return List<TabelaNutricional> Retorna uma lista de objetos {@link TabelaNutricional} que correspondem ao filtro.
     * @throws SQLException Lança uma exceção SQL em caso de erro na consulta ao banco de dados.
     * @author Giovanna Santos
     * */
    List<TabelaNutricional> filterBy(String nomeFiltro, int page);

    /** Método findByNutrientRange: filtra os registros da tabela nutricional com base em um valor mínimo, máximo ou intervalo
     * de quantidade de determinado nutriente.
     *
     * @param type Tipo de comparação ou intervalo (ex.: "min", "max", "range").
     * @param column Nome da coluna/nutriente a ser filtrado.
     * @param minValue Quantidade mínima do nutriente.
     * @param maxValue Quantidade máxima do nutriente.
     * @param page Número da página de resultados (para paginação).
     * @return List<TabelaNutricional> Retorna uma lista de objetos {@link TabelaNutricional} que correspondem ao filtro.
     * @throws SQLException Lança uma exceção SQL em caso de erro na consulta ao banco de dados.
     * @author Giovanna Santos
     * */
    List<TabelaNutricional> findByNutrientRange(String type, String column, double minValue, double maxValue, int page);
}
