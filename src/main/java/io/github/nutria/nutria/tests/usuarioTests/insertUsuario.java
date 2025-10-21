package io.github.nutria.nutria.tests.usuarioTests;

import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.model.Usuario;

public class insertUsuario {
    public static void main(String[] args) {
        // Método de inserir Usuario
        Usuario usuario = new Usuario("Enzo Mota", "enzo.mota@germinare.org.br", "1234567810", "119928661280", "Instituto J&F", "enzo.png");
        UsuarioDAO UsuarioDaoTest = new UsuarioDAO();

        try {
            if (UsuarioDaoTest.inserir(usuario)) {
                System.out.println("Usuario inserido com sucesso!");
            } else {
                System.out.println("Erro ao inserir usuario!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
