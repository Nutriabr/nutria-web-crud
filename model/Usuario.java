package java.model;
public class Usuario {
    // Atributos
    private int id;
    private String nomeCompleto;
    private String enderecoEmail;
    private String senha;
    private String telefone;
    private String empresa;
    private String foto; 

    // Métodos construtores;
    public Usuario (int id, String nomeCompleto, String enderecoEmail, String senha, String telefone, String empresa, String foto){
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.enderecoEmail = enderecoEmail;
        this.senha = senha;
        this.telefone = telefone;
        this.empresa = empresa;
        this.foto = foto;
    }

    public Usuario(int id, String nomeCompleto, String enderecoEmail, String senha, String telefone){
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.enderecoEmail = enderecoEmail;
        this.senha = senha;
        this.telefone = telefone;
    }

    // Métodos getters
    public int getId(){
        return id;
    }

    public String getNomeCompleto(){
        return nomeCompleto;
    }

    public String getEnderecoEmail(){
        return enderecoEmail;
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
    public void setNomeCompleto(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
    }

    public void setEnderecoEmail(String enderecoEmail){
        this.enderecoEmail = enderecoEmail;
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
}