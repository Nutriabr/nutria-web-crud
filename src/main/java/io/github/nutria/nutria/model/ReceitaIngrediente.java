package io.github.nutria.nutria.model;

public class ReceitaIngrediente {
    // Atributos
    private Long id;
    private Receita receita;
    private Long idIngrediente;
    private double quantidade;

    // Métodos construtores
    public ReceitaIngrediente(){

    }

    public ReceitaIngrediente(Long id, Receita receita, Long idIngrediente, double quantidade){
        this.id = id;
        this.receita = receita;
        this.idIngrediente = idIngrediente;
        this.quantidade = quantidade;
    }

    // Métodos getters
    public Long getId(){
        return id;
    }
    public Receita getReceita() {
        return receita;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public double getQuantidade() {
        return quantidade;
    }

    // Métodos setters
    public void setId(Long id){
        this.id = id;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public void setIdIngrediente(Long idIngrediente) {
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