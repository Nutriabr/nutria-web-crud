package io.github.nutria.nutria.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface genérica para operações de CRUD.
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo da Chave Primária (key) da entidade.
 * @author Luis Henrique
 * @version 1.0
 */
public interface GenericDAO<T, ID> {
    /**
     * Método para inserir um novo dado no banco de dados
     * @param entity Objeto a ser inserido
     * @return boolean Retorna true foi inserido com sucesso, false caso contrário
     * @author marianamarrao
     * @throws RuntimeException Em caso de erro na operação SQL
     */
    boolean insert(T entity);

    boolean update(T entity);

    /** Método para listar todas os registros de uma entidade no banco de dados
     * @return List<Entity> Retorna uma lista de objetos da entidade
     * @author marianamarrao
     * @throws SQLException Lança uma exceção SQL em caso de erro na consulta
     * */
    List<T> findAll(int page);

    /**
     * Método para deletar um objeto no Banco de Dados pelo seu id
     * @param id Recebe como o parametro o id do objeto que será deletado
     * @author Luis Henrique
     * @return boolean - Retorna <i>true</i> se o número de linhas afetadas foi maior que 0 ou <i>false</i> se o
     * número de linhas afetadas for igual ou menor que 0.
     * */
    boolean deleteById(Long id) throws SQLException;

    /**
     * Método para contar todos os registros de uma determinada entidade do Banco de Dados
     * Esse método será utilizado para mostrar a quantidade de registros no banco antes de mostrar a tabela.
     * E como o método seguinte usará LIMIT E OFFSET não terá como guardar o total.
     * @return inteiro com a quantidade de registros na tabela
     * @author enzomota
     * @return int Retorna um inteiro com o total de registros no banco de dados
     * */
    int countAll();
}
