package io.github.nutria.nutria.model.entity;

public class Usuario {
    // Atributos
    private long id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String empresa;
    private String foto; 

    // Métodos construtores;
    public Usuario() {
    }

    public Usuario (String nome, String email, String senha, String telefone, String empresa, String foto){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.empresa = empresa;
        this.foto = foto;
    }

    public Usuario (long id, String nome, String email, String senha, String telefone, String empresa, String foto){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.empresa = empresa;
        this.foto = foto;
    }

    public Usuario(long id, String nome, String email, String senha, String telefone){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    // Métodos getters
    public long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public String getSenha(){
        return senha;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getEmpresa(){
        return empresa;
    }

    public String getFoto(){
        return foto;
    }

    // Métodos setters


    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }

    public void setFoto(String foto){
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Usuario { " +
                "id: " + id +
                ", nome: '" + nome + '\'' +
                ", email: '" + email + '\'' +
                ", senha: '" + senha + '\'' +
                ", telefone: '" + telefone + '\'' +
                ", empresa: '" + empresa + '\'' +
                ", foto: '" + foto + '\'' + " " +
                '}';
    }
}