package io.github.nutria.nutria.model;

public class Admin {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    // Construtor sem argumentos
    public Admin() {
    }

    // Construtor com todos os argumentos
    public Admin(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Admin(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Métodos Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // ToString

    @Override
    public String toString() {
        return "Admin { " +
                "id: " + id +
                ", nome: '" + nome + '\'' +
                ", email: '" + email + '\'' +
                ", senha: '" + senha + '\'' + " " +
                '}';
    }
}
