package io.github.nutria.nutria.servlet.receitaIngredienteServlet;

import io.github.nutria.nutria.dao.ReceitaIngredienteDAO;
import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.ReceitaIngrediente;
import io.github.nutria.nutria.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/receitasIngredientes/listar")
public class ReceitaIngredienteSelectServlet extends HttpServlet {
    private static final int TOTAL_RECEITA_INGREDIENTE_PAGE = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();
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
            int totalReceitasIngrediente = receitaIngredienteDAO.countAll();
            int totalPages = (int) Math.ceil((double) totalReceitasIngrediente / TOTAL_RECEITA_INGREDIENTE_PAGE);

            if (currentPage < 1) {
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }

            List<ReceitaIngrediente> receitaIngredienteList = receitaIngredienteDAO.findAll(currentPage);

            req.setAttribute("receitaIngredienteList", receitaIngredienteList);
            req.setAttribute("totalReceitasIngrediente", totalReceitasIngrediente);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", currentPage);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/receitaIngrediente/receitasIngrediente.jsp");
            dispatcher.forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}