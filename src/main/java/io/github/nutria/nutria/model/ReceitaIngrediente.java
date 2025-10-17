package io.github.nutria.nutria.model;

public class ReceitaIngrediente {
    // Atributos
    private Long idReceita;
    private Long idIngrediente;
    private double quantidade;

    // Métodos construtores
    public ReceitaIngrediente(){

    }

    public ReceitaIngrediente(Long idReceita, Long idIngrediente, double quantidade){
        this.idReceita = idReceita;
        this.idIngrediente = idIngrediente;
        this.quantidade = quantidade;
    }

    // Métodos getters
    public Long getIdReceita() {
        return idReceita;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public double getQuantidade() {
        return quantidade;
    }

    // Métodos setters
    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    // Método toString
    public String toString() {
        return  "Receita" + idReceita +
                "\nID-Ingredientes: " + idIngrediente +
                "\nQuantidade: " + quantidade;
    }
}