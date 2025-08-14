package io.github.nutria.nutria.model.entity;
public class Receita {
    // Atributos
    private long id;
    private String nome;
    private String porcao;
    private long idProduto;

    // Métodos getters

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPorcao() {
        return porcao;
    }

    public long getIdProduto() {
        return idProduto;
    }

    // Método setters

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPorcao(String porcao) {
        this.porcao = porcao;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome + '\'' +
                "\nPorcão: " + porcao + '\'' +
                "\nID-Produto: " + idProduto;
    }
}