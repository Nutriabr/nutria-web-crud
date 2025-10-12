package io.github.nutria.nutria.model;

import java.util.List;

public class Produto {
    // Atributos
    private Long id;
    private String nome;
    private Usuario usuario;
    private List<Receita> receitas;

    // Métodos construtores
    public Produto (){

    }

    public Produto (Long id, String nome,Usuario usuario, List<Receita> receitas){
        this.id = id;
        this.nome = nome;
        this.receitas = receitas;
        this.usuario = usuario;
    }

    public Produto (Long id, String nome, Usuario usuario){
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }

    public Produto (String nome, Usuario usuario){
        this.nome = nome;
        this.usuario = usuario;
    }

    public Produto (Long id, String nome){
        this.id = id;
        this.nome = nome;
    }
    public Produto (String nome){
        this.nome = nome;
    }

    // Métodos getters
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Usuario getUsuario(){
        return usuario;
    }
    public List<Receita> getReceitas() {
        return receitas;
    }

    // Métodos setters
    public void setId(Long id) {
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
        String receitasInfo = "Nenhuma receita cadastrada";
        if (receitas != null && !receitas.isEmpty()) {
            receitasInfo = "";
            for (Receita r : receitas) {
                receitasInfo += "\n  - " + r.getPorcao();
            }
        }

        return "ID: " + id +
                "\nNome: " + nome +
                "\nReceitas:" + receitasInfo;
    }
}
