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
    private static final int TOTAL_ADMINS_PAGINAS = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO adminDAO = new AdminDAO();
        int paginaAtual = 1;

        String paginaParam = req.getParameter("page");
        if (paginaParam != null) {
            try {
                paginaAtual = Integer.parseInt(paginaParam);
            } catch (NumberFormatException e) {
                System.err.println("Parâmetro de página inválido: " + paginaParam);
            }
        }

        try {
            int totalAdmins = adminDAO.contarTodos();
            int totalPaginas = (int) Math.ceil((double) totalAdmins / TOTAL_ADMINS_PAGINAS);

            if (paginaAtual < 1) {
                paginaAtual = 1;
            } else if (paginaAtual > totalPaginas && totalPaginas > 0) {
                paginaAtual = totalPaginas;
            }

            List<Admin> adminList = adminDAO.buscarTodos(paginaAtual);

            req.setAttribute("adminList", adminList);
            req.setAttribute("totalAdmins", totalAdmins);
            req.setAttribute("totalPages", totalPaginas);
            req.setAttribute("currentPage", paginaAtual);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/admins.jsp");
            dispatcher.forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}

