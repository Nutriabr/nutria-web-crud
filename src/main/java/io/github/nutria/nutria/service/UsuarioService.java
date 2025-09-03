package io.github.nutria.nutria.service;

import io.github.nutria.nutria.model.dao.UsuarioDAO;
import io.github.nutria.nutria.model.entity.Usuario;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAO dao = new UsuarioDAO();

    public String save(Usuario usuario) {
        // Inicializando os atributos do usuário
        String nome = usuario.getNome();
        String endereco_email = usuario.getEmail();
        String senha = usuario.getSenha();
        String telefone = usuario.getTelefone();
        String empresa = usuario.getEmpresa();
        String foto = usuario.getFoto();

        // Validando se os campos obrigatórios estão preenchidos
        if (nome == null || endereco_email == null || senha == null || nome.isBlank() ||
                endereco_email.isBlank() || senha.isBlank()) {
            return "Nome Completo, Endereço de e-mail e Senha são campos obrigatórios.";
        }

        // Validando se a senha está correta
        if (senha.length() <= 8) {
            return "A senha deve conter quantidade de caracteres maior ou igual a 8";
        }

        // Validando se o telefone possui 11 caracteres
        // TODO: Corrigir a quantidade de caracteres no banco
        if (telefone.length() != 11) {
            return "A quantidade de caracteres do Telefone deve conter 11 caracteres";
        }

        // Validando se o e-mail é válido
        if (dao.findByEmailUsed(endereco_email)) {
            return "E-mail " + endereco_email + " já cadastrado no sistema.";
        }

        // Inserindo o usuário no banco de dados
        dao.save(usuario);
        return null;

    }

    public List<Usuario> findAll() {
        return dao.findAll();
    }

    public boolean deleteUserById(int id) {
        return dao.deleteUserById(id) == 1;
    }
}
