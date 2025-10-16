package io.github.nutria.nutria.model;

public class ReceitaIngrediente {
    // Atributos
    private Long id;
    private Long idReceita;
    private Long idIngrediente;
    private double quantidade;

    // Métodos construtores
    public ReceitaIngrediente(){

    }

    public ReceitaIngrediente(Long id, Long idReceita, Long idIngrediente, double quantidade){
        this.id = id;
        this.idReceita = idReceita;
        this.idIngrediente = idIngrediente;
        this.quantidade = quantidade;
    }

    // Métodos getters
    public Long getId(){
        return id;
    }
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
    public void setId(Long id){
        this.id = id;
    }

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
        return "ID: " + id +
                "\nReceita" + idReceita +
                "\nID-Ingredientes: " + idIngrediente +
                "\nQuantidade: " + quantidade;
    }
}