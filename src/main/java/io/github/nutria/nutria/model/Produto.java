package io.github.nutria.nutria.model;


public class Produto {
    // Atributos
    private Long id;
    private String nome;
    private Long idUsuario;

    // Métodos construtores
    public Produto (){

    }

    public Produto (Long id, String nome,Long idUsuario){
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }

    public Produto (String nome, Long idUsuario){
        this.nome = nome;
        this.idUsuario = idUsuario;
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
    public Long getIdUsuario(){
        return idUsuario;
    }

    // Métodos setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome +
                "\nID-Usuário:" + idUsuario;
    }
}
