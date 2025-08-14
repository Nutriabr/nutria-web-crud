package io.github.nutria.nutria.model.entity;

public class Produto {
    // Atributos
    private long id;
    private String nome;

    // Métodos construtores
    public Produto (){

    }

    public Produto (long id, String nome){
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
    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome;
    }
}
