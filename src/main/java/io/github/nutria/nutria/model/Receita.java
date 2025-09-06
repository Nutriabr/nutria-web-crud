package io.github.nutria.nutria.model;
public class Receita {
    // Atributos
    private long id;
    private String nome;
    private String porcao;
    private long idProduto;

    // Métodos construtores
    public Receita(){

    }

    public Receita(long id, String nome, String porcao, long idProduto){
        this.id = id;
        this.nome = nome;
        this.porcao = porcao;
        this.idProduto = idProduto;
    }

    public Receita(String nome, String porcao, long idProduto){
        this.nome = nome;
        this.porcao = porcao;
        this.idProduto = idProduto;
    }

    public Receita(String porcao, long idProduto){
        this.porcao = porcao;
        this.idProduto = idProduto;
    }

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
