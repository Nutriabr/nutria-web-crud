package io.github.nutria.nutria.tests;

import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/** Classe principal para testes diversos.
 * @author Mariana Marrão, Luis Henrique e Enzo Mota
 * @version 1.1
 * */
public class Main {
    public static void main(String[] args) {

        // 1. Teste de conexão com o banco de dados

        // 2. Teste de CRUD para a entidade Usuario
        try (UsuarioDAO usuarioDAOTest = new UsuarioDAO()) {
            // TESTE DO MÉTODO findByEmailUsed
            System.out.println("Verificando se o email está em uso:");

            boolean isEmailUsed = usuarioDAOTest.findByEmailUsed("Mariana.aparecida@gmail.com");
            if (isEmailUsed) {
                System.out.println("O email já está em uso.");
            } else {
                System.out.println("O email não está em uso.");
            }

            // TESTE DO MÉTODO insert
            System.out.println("\nInserindo um novo usuário:");
            // Aqui você pode criar um objeto Usuario e chamar o método insert
            Usuario usuario = new Usuario();
            usuario.setNome("Mariana Aparecida");
            usuario.setEmail("Mariana.aparecida@gmail.com");
            usuario.setSenha("senhaSegura123");
            usuario.setTelefone("11987654321");
            boolean insertResult = usuarioDAOTest.insert(usuario);
            if (insertResult) {
                System.out.println("Usuário inserido com sucesso.");
            } else {
                System.out.println("Falha ao inserir o usuário.");
            }

            // TESTE DO MÉTODO deleteById
            System.out.println("\nDeletando um usuário por ID:");
            long userIdToDelete = 2;
            boolean deleteResult = usuarioDAOTest.deleteById(userIdToDelete);
            if (deleteResult) {
                System.out.println("Usuário deletado com sucesso.");
            } else {
                System.out.println("Falha ao deletar o usuário.");
            }

            // Teste do método findAll
            System.out.println("\nListando todos os usuários:");
            List<Usuario> usuarios = usuarioDAOTest.findAll();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário encontrado.");
            } else {
                System.out.println("Usuários encontrados:");
                System.out.println(usuarios);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Teste de CRUD para a entidade Receita

        // 4. Teste de CRUD para a entidade Ingrediente

        // 5. Teste de CRUD para a entidade TabelaNutricional

        // 6. Teste de funcionalidades adicionais (ex: autenticação, hashing de senhas, etc.)

        // 7. Teste de manipulação de listas e buscas no banco de dados

        // 8. Teste de tratamento de exceções e validações
    }
}
