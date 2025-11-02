package io.github.nutria.nutria.servlet.receitaServlet;

import io.github.nutria.nutria.dao.ReceitaDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.model.ReceitaIngrediente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/receita/listar")
public class ReceitaSelectServlet extends HttpServlet {
    private final int TOTAL_RECEITA_PAGE = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        int currentPage = 1;
        String pageParam = req.getParameter("page");
        String filtro = req.getParameter("busca");

        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException nfe) {
                System.err.println("Parâmetro de página inválida: " + pageParam);
            }
        }

        if (currentPage < 1) {
            currentPage = 1;
        }

        List<Receita> receitasList;
        int totalReceitas;

        try {
            if (filtro == null || filtro.isEmpty()) {
                totalReceitas = receitaDAO.contarTodos();
            } else {
                try {
                    Long numero = Long.parseLong(filtro);
                    totalReceitas = receitaDAO.contarPorIdOuIdProduto(numero);
                } catch (NumberFormatException nfe) {
                    totalReceitas = receitaDAO.contarPorPorcao(filtro);
                }
            }

            int totalPages = (int) Math.ceil((double) totalReceitas / TOTAL_RECEITA_PAGE);
            if (totalPages == 0) {
                totalPages = 1;
            }

            if (currentPage > totalPages) {
                currentPage = totalPages;
            }

            if (filtro == null || filtro.isEmpty()) {
                receitasList = receitaDAO.buscarTodos(currentPage);
            } else {
                try {
                    Long numero = Long.parseLong(filtro);
                    receitasList = receitaDAO.buscarPorIdOuIdProduto(numero, currentPage);
                } catch (NumberFormatException nfe) {
                    receitasList = receitaDAO.buscarPorPorcao(filtro, currentPage);
                }
            }

            req.setAttribute("totalReceitas", totalReceitas);
            req.setAttribute("receitasList", receitasList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("filtro", filtro);
            req.getRequestDispatcher("/WEB-INF/views/receita/receitas.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }
}