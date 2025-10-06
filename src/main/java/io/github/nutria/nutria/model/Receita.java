package io.github.nutria.nutria.model;

import java.util.List;
public class Receita {
    // Atributos
    private long id;
    private String porcao;
    private Produto produto;
    private List<ReceitaIngrediente> ingredientes;

    // Métodos construtores
    public Receita(){

    }

    public Receita(long id, String porcao, Produto produto, List<ReceitaIngrediente> ingredientes){
        this.id = id;
        this.porcao = porcao;
        this.produto = produto;
        this.ingredientes = ingredientes;
    }

    public Receita(String porcao, Produto produto, List<ReceitaIngrediente> ingredientes){
        this.porcao = porcao;
        this.produto = produto;
        this.ingredientes = ingredientes;
    }

    // Métodos getters

    public long getId() {
        return id;
    }

    public String getPorcao() {
        return porcao;
    }
    public List<ReceitaIngrediente> getIngredientes(){
        return this.ingredientes;
    }

    public Produto getProduto() {
        return produto;
    }

    // Método setters

    public void setId(long id) {
        this.id = id;
    }

    public void setPorcao(String porcao) {
        this.porcao = porcao;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public void setIngredientes(List<ReceitaIngrediente> ingredientes){
        this.ingredientes = ingredientes;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
               "\nPorção: " + porcao +
               "\nProduto: " + produto +
                "\nIngredientes: " + ingredientes;
    }
}
