package io.github.nutria.nutria.model.entity;

public class ReceitasIngrediente {
    // Atributos
    private long idReceita;
    private long idIngrediente;
    private double quantidade;

    // Métodos getters
    public long getIdReceita() {
        return idReceita;
    }

    public long getIdIngrediente() {
        return idIngrediente;
    }

    public double getQuantidade() {
        return quantidade;
    }

    // Métodos setters

    public void setIdReceita(long idReceita) {
        this.idReceita = idReceita;
    }

    public void setIdIngrediente(long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    // Método toString
    public String toString() {
        return "ID-Receita: " + idReceita +
                "ID-Ingredientes: " + idIngrediente +
                "Quantidade: " + quantidade;
    }
}