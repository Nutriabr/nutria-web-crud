package io.github.nutria.nutria.model;
public class Ingrediente {
    // Atributos
    private Long id;
    private String nome;

    // Métodos construtores
    public Ingrediente(){
    }

    public Ingrediente(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    // Métodos getters

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Métodos setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
                "Nome: " + nome;
    }

}
