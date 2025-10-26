package io.github.nutria.nutria.model;

import java.util.List;
public class Receita {
    // Atributos
    private Long id;
    private String porcao;
    private Long idProduto;

    // Métodos construtores
    public Receita(){

    }

    public Receita(Long id, String porcao, Long idProduto){
        this.id = id;
        this.porcao = porcao;
        this.idProduto = idProduto;
    }

    public Receita(String porcao, Long idProduto){
        this.porcao = porcao;
        this.idProduto = idProduto;
    }

    // Métodos getters

    public Long getId() {
        return id;
    }
    public String getPorcao() {
        return porcao;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    // Método setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setPorcao(String porcao) {
        this.porcao = porcao;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    // Método toString
    @Override
    public String toString() {
        return "ID: " + id +
                "\nPorção: " + porcao +
                "\nID-Produto: " +  idProduto;
    }
}