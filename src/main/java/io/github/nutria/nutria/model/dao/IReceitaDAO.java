package io.github.nutria.nutria.model.dao;

import io.github.nutria.nutria.model.entity.Receita;
import java.util.List;
public interface IReceitaDAO {
    void save (Receita receita);
    List<Receita> findAll();
    int deleteById(long id);

}
