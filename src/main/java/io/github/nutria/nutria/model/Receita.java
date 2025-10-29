package io.github.nutria.nutria.model;

import java.util.List;
public class Receita {
    // Atributos
    private Long id;
    private String porcao;
    private Long idProduto;
    private String nomeProduto;

    // Métodos construtores
    public Receita(){

    }

    public Receita(Long id, String porcao, Long idProduto){
        this.id = id;
        this.porcao = porcao;
        this.idProduto = idProduto;
    }
    public Receita(Long id, String porcao, Long idProduto, String nomeProduto){
        this.id = id;
        this.porcao = porcao;
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
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

    public String getNomeProduto() {
        return nomeProduto;
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

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    // Método toString
    @Override
    public String toString() {
        return "ID: " + id +
                "\nPorção: " + porcao +
                "\nID-Produto: " +  idProduto;
    }
}