package io.github.nutria.nutria.tests.receitaTests;

import io.github.nutria.nutria.dao.ReceitaDAO;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.model.ReceitaIngrediente;

import java.util.ArrayList;
import java.util.List;

public class insertReceita {
    public static void main(String[] args) {
        ReceitaDAO receitaDAO = new ReceitaDAO();

        Produto produto = new Produto(20L,"Panetone com Açúcar e Canela");

        Receita receita1 = new Receita("100g", produto);
        testInsert(receitaDAO,receita1);

        Receita receita2 = new Receita(21L,"100g",produto);
        testInsert(receitaDAO,receita2);

        List<ReceitaIngrediente> receitaIngredienteList1 = new ArrayList<>();
        Receita receita3 = new Receita(22L,"100g", produto, receitaIngredienteList1);
        testInsert(receitaDAO,receita3);

        List<ReceitaIngrediente> receitaIngredienteList2 = new ArrayList<>();
        Receita receita4 = new Receita("100g", produto, receitaIngredienteList2);
        testInsert(receitaDAO,receita4);
    }

    public static void testInsert(ReceitaDAO receitaDAO, Receita receita){
        boolean itWorked = receitaDAO.insert(receita);
        System.out.println(itWorked ? "Inserido com sucesso." : "Falha ao inserir.");
    }
}