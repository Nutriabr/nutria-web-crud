package io.github.nutria.nutria.service;

import io.github.nutria.nutria.model.dao;
import io.github.nutria.nutria.model.entity.Usuario;

public class UsuarioService {

    private final UsuarioDAO dao = new UsuarioDAO();

    public String insertUser(Usuario usuario) {
        // Inicializando os atributos do usuário
        String nome_completo = usuario.getNomeCompleto();
        String endereco_email = usuario.getEnderecoEmail();
        String senha = usuario.getSenha();
        String telefone = usuario.getTelefone();
        String empresa = usuario.getEmpresa();
        String foto = usuario.getFoto();

        // Validando se os campos obrigatórios estão preenchidos
        if (nome_completo == null || endereco_email == null || senha == null || nome_completo.isBlank() ||
                endereco_email.isBlank() || senha.isBlank()) {
            return "Nome Completo, Endereço de e-mail e Senha são campos obrigatórios.";
        }

        // Validando se o e-mail é válido
        if (dao.emailUsed(endereco_email)) {
            return "E-mail " + endereco_email + " já cadastrado no sistema.";
        }

        // Inserindo o usuário no banco de dados
        dao.insertUser(usuario);
        return null;
    }
}
