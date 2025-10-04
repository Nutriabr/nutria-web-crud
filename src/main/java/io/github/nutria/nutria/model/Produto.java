package io.github.nutria.nutria.model;

import java.util.List;

public class Produto {
    // Atributos
    private long id;
    private String nome;
    private List<Receita> receitas;

    // Métodos construtores
    public Produto (){

    }

    public Produto (long id, String nome,List<Receita> receitas){
        this.id = id;
        this.nome = nome;
        this.receitas = receitas;
    }

    // Métodos getters
    public long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public List<Receita> getReceitas() {
        return receitas;
    }

    // Métodos setters
    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setReceitas(List<Receita> receitas){
        this.receitas = receitas;
    }

    // Método toString

    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome +
                "\nReceitas" + receitas;
    }
}
