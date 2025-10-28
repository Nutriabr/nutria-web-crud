package io.github.nutria.nutria.model;


public class Produto {
    // Atributos
    private Long id;
    private String nome;
    private Long idUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String empresaUsuario;

    // Métodos construtores
    public Produto (){

    }

    public Produto (Long id, String nome,Long idUsuario){
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
    }
    public Produto (Long id, String nome,Long idUsuario, String nomeUsuario, String emailUsuario){
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.empresaUsuario = emailUsuario;
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
    public String getNomeUsuario(){
        return nomeUsuario;
    }
    public String getEmailUsuario(){
        return emailUsuario;
    }

    public String getEmpresaUsuario(){
        return empresaUsuario;
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

    public void setNomeUsuario(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
    }

    public void setEmailUsuario(String emailUsuario){
        this.emailUsuario = emailUsuario;
    }

    public void setEmpresaUsuario(String empresaUsuario){
        this.empresaUsuario = empresaUsuario;
    }

    // Método toString
    public String toString() {
        return "ID: " + id +
                "\nNome: " + nome +
                "\nID-Usuário: " + idUsuario +
                "\nNome-Usuário: " + nomeUsuario +
                "\nEmail-Usuário: " + emailUsuario +
                "\nEmpresa-Usuário: " + empresaUsuario;
    }
}
