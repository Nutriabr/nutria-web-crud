package io.github.nutria.nutria.servlet.tabelaNutricionalServlet;

import io.github.nutria.nutria.dao.TabelaNutricionalDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.TabelaNutricional;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tabela_nutricional/listar")
public class TabelaNutricionalSelectServlet extends HttpServlet {
    private static final int TOTAL_TABELAS_NUTRICIONAIS_PAGE = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TabelaNutricionalDAO tabelaNutricionalDAO = new TabelaNutricionalDAO();
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
            int totalTabelasNutricionais = tabelaNutricionalDAO.countAll();
            int totalPages = (int) Math.ceil((double) totalTabelasNutricionais / TOTAL_TABELAS_NUTRICIONAIS_PAGE);

            if (currentPage < 1) {
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }

            List<TabelaNutricional> tabelaNutricionalList = tabelaNutricionalDAO.findAll(currentPage);

            req.setAttribute("tabelaNutricionalList", tabelaNutricionalList);
            req.setAttribute("totalTabelasNutricionais", totalTabelasNutricionais);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", currentPage);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/tabela_nutricional/tabela_nutricional.jsp");
            dispatcher.forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}