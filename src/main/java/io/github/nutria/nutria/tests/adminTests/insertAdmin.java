package io.github.nutria.nutria.tests.adminTests;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;

public class insertAdmin {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = new Admin("Luis", "luis@nutria.org.br", "senhaSegura123");

        adminDAO.insert(admin);
    }
}