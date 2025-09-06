package io.github.nutria.nutria.service;

import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.model.Usuario;

import java.util.List;


/** Classe service para métodos reutilizáveis que contém a lógica de negócio de validações, isolando-a do DAO e a
 * Controller
 * @see UsuarioDAO
 * @author Luis Henrique
 * @version 1.1
 * */
public class UsuarioService {
    // 1. Instanciar o objeto dao do usuario
    private final UsuarioDAO dao = new UsuarioDAO();

    /**
     * @param usuario Recebe o objeto usuário da Controller para validação dos campos
     * @return string Retorna uma mensagem de sucesso ou falha para a Controller
     */
    public String insert(Usuario usuario) {
        // 1. Inicializando os atributos do usuário
        String nome = usuario.getNome();
        String endereco_email = usuario.getEmail();
        String senha = usuario.getSenha();
        String telefone = usuario.getTelefone();

        // 1. Validando se os campos obrigatórios estão preenchidos e retornando mensagem de erro
        if (nome == null || endereco_email == null || senha == null || nome.isBlank() ||
                endereco_email.isBlank() || senha.isBlank()) {
            return "Nome Completo, Endereço de e-mail e Senha são campos obrigatórios.";
        }

        // 2. Método para validar se a senha tem mais de 8 caracteres e retorna mensagem de erro
        if (senha.length() <= 8) {
            return "A senha deve conter quantidade de caracteres maior ou igual a 8";
        }

        /*
        * 3. Método para retornar se o email já está cadastrado no Banco de Dados e retorna mensagem de erro
        */
        if (dao.findByEmailUsed(endereco_email)) {
            return "E-mail " + endereco_email + " já cadastrado no sistema.";
        }

        // 4. Inserindo o usuário no banco de dados e retornando mensagem de sucesso ou falha
        if (dao.insert(usuario)) {
            return "Usuário cadastrado no banco de dados.";
        }
        return "Não foi possível cadastrar o usuário no banco de dados";

    }

    // 2. Método para listar todos os usuários
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    // 3. Método para deletar usuário por ID e retornar boolean com sucesso ou falha
    public boolean deleteUserById(long id) {
        return dao.deleteById(id);
    }
}
