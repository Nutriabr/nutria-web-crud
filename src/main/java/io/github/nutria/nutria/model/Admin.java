package io.github.nutria.nutria.model;

import java.sql.Date;

public class Admin {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private Date nascimento;
    private String cargo;
    private String foto;

    // Construtor sem argumentos
    public Admin() {
    }

    // Construtor com todos os argumentos
    public Admin(Long id, String nome, String email, String senha, String telefone, Date nascimento, String cargo, String foto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.foto = foto;
    }


    public Admin(String nome, String email, String senha, String telefone, Date nascimento, String cargo, String foto) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.foto = foto;
    }

    public Admin(String nome, String email, String senha, String telefone, Date nascimento, String cargo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.cargo = cargo;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    // ToString

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", nascimento=" + nascimento +
                ", cargo='" + cargo + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
