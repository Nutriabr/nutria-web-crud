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
     * Busca um registro de {@link TabelaNutricional} pelo ID informado.
     *
     * @param id o ID que será utilizado na busca.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    TabelaNutricional buscarPorId(Long id);

    /**
     * Filtra os registros de {@link TabelaNutricional} de acordo com um filtro pré-definido.
     * <p>
     * Os filtros disponíveis são definidos na implementação da DAO, em um mapa constante
     * que associa o nome do filtro às suas condições.
     *
     * @param nomeFiltro o nome do filtro que será aplicado (ex.: "baixo_sodio", "rico_fibras").
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link TabelaNutricional} que atendem aos critérios do filtro.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<TabelaNutricional> filtrarPor(String nomeFiltro, int page);

    /**
     * Filtra os registros de {@link TabelaNutricional} por valor mínimo, máximo ou intervalo de quantidade de determinado nutriente.
     *
     * @param tipo o tipo de comparação ou intervalo (ex.: "min", "max", "intervalo").
     * @param coluna o nome da coluna/nutriente a ser filtrado.
     * @param quantMin a quantidade mínima do nutriente.
     * @param quantMax a quantidade máxima do nutriente.
     * @param page o número da página de resultados (para paginação).
     * @return uma lista de objetos {@link TabelaNutricional} que atendem aos critérios do filtro.
     * @throws DataAccessException se ocorrer algum erro ao acessar o banco de dados.
     */
    List<TabelaNutricional> filtrarPorIntervaloNutriente(String tipo, String coluna, double quantMin, double quantMax, int page);
}
