package io.github.nutria.nutria.model;
public class Receita {
    // Atributos
    private long id;
    private String nome;
    private String porcao;
    private Produto produto;

    // Métodos construtores
    public Receita(){

    }

    public Receita(long id, String nome, String porcao, Produto produto){
        this.id = id;
        this.nome = nome;
        this.porcao = porcao;
        this.produto = produto;
    }

    public Receita(String nome, String porcao, Produto produto){
        this.nome = nome;
        this.porcao = porcao;
        this.produto = produto;
    }

    public Receita(String porcao, Produto produto){
        this.porcao = porcao;
        this.produto = produto;
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

    public Produto getProduto() {
        return produto;
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

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome + '\'' +
                "\nPorcão: " + porcao + '\'' +
                "\nProduto: " + produto;
    }
}
