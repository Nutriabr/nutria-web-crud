package io.github.nutria.nutria.model;

public class ReceitaIngrediente {
    // Atributos
    private long id;
    private Receita receita;
    private long idIngrediente;
    private double quantidade;

    // Métodos construtores
    public ReceitaIngrediente(){

    }

    public ReceitaIngrediente(long id, Receita receita, long idIngrediente, double quantidade){
        this.id = id;
        this.receita = receita;
        this.idIngrediente = idIngrediente;
        this.quantidade = quantidade;
    }

    // Métodos getters
    public long getId(){
        return id;
    }
    public Receita getReceita() {
        return receita;
    }

    public long getIdIngrediente() {
        return idIngrediente;
    }

    public double getQuantidade() {
        return quantidade;
    }

    // Métodos setters
    public void setId(long id){
        this.id = id;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
                "\nReceita" + receita +
                "\nID-Ingredientes: " + idIngrediente +
                "\nQuantidade: " + quantidade;
    }
}