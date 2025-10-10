package io.github.nutria.nutria.tests.adminTests;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.util.PasswordHasher;

public class updateAdminPw {
    public static void main(String[] args) {
        // Atualizar a senha do admin com id 4 (Enzo Mota) de "1234567810" para uma senha hasheada
        System.out.println("Testando atualização de senha do admin...");

        Admin admin = new Admin(13L, "Mariana Marrão Ferreira Felis", "mariana.felis@germinare.org.br", "1234567810");
        Admin admin1 = new Admin();
        AdminDAO dao = new AdminDAO();


        System.out.println(dao.findAll(3));

        System.out.println("Senha antes da atualização: " + admin.getSenha());

        dao.update(admin);

        System.out.println("Senha após a atualização: " + dao.findAll(4).get(2).getSenha());

        System.out.println(dao.findAll(4));
    }
}
