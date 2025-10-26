package io.github.nutria.nutria.tests.adminTests;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;

import java.util.ArrayList;
import java.util.List;

public class updateAdminPw {
    public static void main(String[] args) {
        // Atualizar a senha do admin com id 4 (Enzo Mota) de "1234567810" para uma senha hasheada
        System.out.println("Testando atualização de senha do admin...");


        AdminDAO dao = new AdminDAO();

        List<Admin> admins = new ArrayList<Admin>();

        for (int i = 0; i < dao.contarTodos(); i++) {
            Admin admin = new Admin(
                    dao.buscarTodos((i < Math.ceil(dao.contarTodos()) / 4) ? (i + 1) : i).get(i).getId(),
                    dao.buscarTodos((i < Math.ceil(dao.contarTodos()) / 4) ? (i + 1) : i).get(i).getNome(),
                    dao.buscarTodos((i < Math.ceil(dao.contarTodos()) / 4) ? (i + 1) : i).get(i).getEmail(),
                    dao.buscarTodos((i < Math.ceil(dao.contarTodos()) / 4) ? (i + 1) : i).get(i).getSenha(),
                    dao.buscarTodos((i < Math.ceil(dao.contarTodos()) / 4) ? (i + 1) : i).get(i).getTelefone(),
                    dao.buscarTodos((i < Math.ceil(dao.contarTodos()) / 4) ? (i + 1) : i).get(i).getNascimento(),
                    dao.buscarTodos((i < Math.ceil(dao.contarTodos()) / 4) ? (i + 1) : i).get(i).getCargo(),
                    dao.buscarTodos((i < Math.ceil(dao.contarTodos()) / 4) ? (i + 1) : i).get(i).getFoto()
            );
            admins.add(admin);
        }

        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i);

//            System.out.println("Senha antes da atualização: " + admin1.getSenha());

            dao.alterar(admin);

//            System.out.println("Senha após a atualização: " + dao.findAll(4).get(2).getSenha());
        }

        System.out.println(dao.buscarTodos(1));
        System.out.println(dao.buscarTodos(2));
        System.out.println(dao.buscarTodos(3));
        System.out.println(dao.buscarTodos(4));
    }
}
