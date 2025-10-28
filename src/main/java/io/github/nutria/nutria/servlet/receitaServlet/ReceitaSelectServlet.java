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

        List<Receita> receitasList = new ArrayList<>();
        List<Long> idsAdicionados = new ArrayList<>();
        int totalReceitas;

        try {
            if (filtro == null || filtro.isEmpty()) {
                receitasList = receitaDAO.buscarTodos(currentPage);
                totalReceitas = receitaDAO.contarTodos();
            } else {
                try {
                    Long numero = Long.parseLong(filtro);
                    totalReceitas = receitaDAO.contarPorIdOuIdProduto(numero);
                    receitasList = receitaDAO.buscarPorIdOuIdProduto(numero,currentPage);

                } catch (NumberFormatException nfe) {
                    receitasList = receitaDAO.buscarPorPorcao(filtro, currentPage);
                    totalReceitas = receitaDAO.contarPorPorcao(filtro);
                }
            }

            int totalPages = (int) Math.ceil((double) totalReceitas / TOTAL_RECEITA_PAGE);
            if (totalPages == 0) totalPages = 1;

            if (currentPage < 1) {
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }

            req.setAttribute("totalReceitas", totalReceitas);
            req.setAttribute("receitasList", receitasList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("filtro", filtro);
            req.getRequestDispatcher("/WEB-INF/views/receita/receitas.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}