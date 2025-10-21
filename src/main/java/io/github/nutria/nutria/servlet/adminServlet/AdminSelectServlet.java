package io.github.nutria.nutria.servlet.adminServlet;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Admin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/listar")
public class AdminSelectServlet extends HttpServlet {
    private static final int TOTAL_ADMINS_PAGE = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO adminDAO = new AdminDAO();
        int currentPage = 1;

        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                System.err.println("Parâmetro de página inválido: " + pageParam);
            }
        }

        try {
            int totalAdmins = adminDAO.contarTodos();
            int totalPages = (int) Math.ceil((double) totalAdmins / TOTAL_ADMINS_PAGE);

            if (currentPage < 1) {
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }

            List<Admin> adminList = adminDAO.buscarTodos(currentPage);

            req.setAttribute("adminList", adminList);
            req.setAttribute("totalAdmins", totalAdmins);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", currentPage);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/admins.jsp");
            dispatcher.forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}