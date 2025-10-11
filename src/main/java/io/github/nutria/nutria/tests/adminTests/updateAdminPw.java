package io.github.nutria.nutria.tests.adminTests;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.util.PasswordHasher;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class updateAdminPw {
    public static void main(String[] args) {
        // Atualizar a senha do admin com id 4 (Enzo Mota) de "1234567810" para uma senha hasheada
        System.out.println("Testando atualização de senha do admin...");

        Admin admin1 = new Admin();
        AdminDAO dao = new AdminDAO();

        List<Admin> admins = null;

        for (int i = 0; i < dao.countAll(); i++) {
            admins = dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i);
        }

        for (int i = 0; i < admins.size(); i++) {
            admin1 = admins.get(i);

//            System.out.println("Senha antes da atualização: " + admin1.getSenha());

            dao.update(admin1);

//            System.out.println("Senha após a atualização: " + dao.findAll(4).get(2).getSenha());
        }

        System.out.println(dao.findAll(1));
        System.out.println(dao.findAll(2));
        System.out.println(dao.findAll(3));
        System.out.println(dao.findAll(4));
    }
}
