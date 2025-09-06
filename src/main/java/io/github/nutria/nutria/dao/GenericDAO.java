package io.github.nutria.nutria.dao;

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
    boolean insert(T entity);
    List<T> findAll();
    boolean deleteById(ID id) throws SQLException;
}
