package io.github.nutria.nutria.tests.adminTests;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;

public class insertAdmin {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = new Admin(11, "Luis Henrique Medeiros da Silva", "luis.medeiros@germinare.org.br", "senhaSegura123");


        System.out.println(adminDAO.update(admin));
    }
}
