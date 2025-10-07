package io.github.nutria.nutria.tests.adminTests;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;

public class insertAdmin {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = new Admin(58, "Mariana Marrão Ferreira Felis", "mariana.felis@germinare.org.br", "1234567810");

        System.out.println(adminDAO.update(admin));
    }
}
