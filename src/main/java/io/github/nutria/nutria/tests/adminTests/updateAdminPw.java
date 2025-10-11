package io.github.nutria.nutria.tests.adminTests;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.util.PasswordHasher;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class updateAdminPw {
    public static void main(String[] args) {
        // Atualizar a senha do admin com id 4 (Enzo Mota) de "1234567810" para uma senha hasheada
        System.out.println("Testando atualização de senha do admin...");


        AdminDAO dao = new AdminDAO();

        List<Admin> admins = new ArrayList<Admin>();

        for (int i = 0; i < dao.countAll(); i++) {
            Admin admin = new Admin(
                    dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i).get(i).getId(),
                    dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i).get(i).getNome(),
                    dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i).get(i).getEmail(),
                    dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i).get(i).getSenha(),
                    dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i).get(i).getTelefone(),
                    dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i).get(i).getNascimento(),
                    dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i).get(i).getCargo(),
                    dao.findAll((i < Math.ceil(dao.countAll()) / 4) ? (i + 1) : i).get(i).getFoto()
            );
            admins.add(admin);
        }

        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i);

//            System.out.println("Senha antes da atualização: " + admin1.getSenha());

            dao.update(admin);

//            System.out.println("Senha após a atualização: " + dao.findAll(4).get(2).getSenha());
        }

        System.out.println(dao.findAll(1));
        System.out.println(dao.findAll(2));
        System.out.println(dao.findAll(3));
        System.out.println(dao.findAll(4));
    }
}
