package io.github.nutria.nutria.dao.interfaces;

import io.github.nutria.nutria.model.ReceitaIngrediente;

import java.util.List;

public interface IReceitaIngredienteDAO {

    List<ReceitaIngrediente> buscarPorMaiorQuant(int page, Double quant);

    List<ReceitaIngrediente> buscarPorMenorQuant(int page, Double quant);

    List<ReceitaIngrediente> buscarPorIntervalo(int page, Double quantMax, Double quantMin);
}
