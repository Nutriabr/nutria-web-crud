package io.github.nutria.nutria.model;
public class Ingrediente {
    // Atributos
    private long id;
    private String nome;

    // Métodos construtores
    public Ingrediente(){
    }

    public Ingrediente(long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    // Métodos getters

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Métodos setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
                "Nome: " + nome;
    }
}
