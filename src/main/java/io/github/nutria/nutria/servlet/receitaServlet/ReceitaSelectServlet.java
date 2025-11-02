package io.github.nutria.nutria.servlet.receitaServlet;

import io.github.nutria.nutria.dao.ReceitaDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Receita;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/receita/listar")
public class ReceitaSelectServlet extends HttpServlet {
    private final int TOTAL_RECEITAS_PAGINAS = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        int paginaAtual = 1;
        String pageParam = req.getParameter("page");
        String filtro = req.getParameter("busca");

        if (pageParam != null) {
            try {
                paginaAtual = Integer.parseInt(pageParam);
            } catch (NumberFormatException nfe) {
                System.err.println("Parâmetro de página inválida: " + pageParam);
            }
        }

        if (paginaAtual < 1) {
            paginaAtual = 1;
        }

        List<Receita> receitasList;
        List<Long> idsProdutosList = null;
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

            int totalPaginas = (int) Math.ceil((double) totalReceitas / TOTAL_RECEITAS_PAGINAS);
            if (totalPaginas == 0) {
                totalPaginas = 1;
            }

            if (paginaAtual > totalPaginas) {
                paginaAtual = totalPaginas;
            }

            if (filtro == null || filtro.isEmpty()) {
                receitasList = receitaDAO.buscarTodos(paginaAtual);
                idsProdutosList = receitaDAO.buscarIdProduto();
            } else {
                try {
                    Long numero = Long.parseLong(filtro);
                    receitasList = receitaDAO.buscarPorIdOuIdProduto(numero, paginaAtual);
                    idsProdutosList = receitaDAO.buscarIdProduto();
                } catch (NumberFormatException nfe) {
                    receitasList = receitaDAO.buscarPorPorcao(filtro, paginaAtual);
                }
            }

            req.setAttribute("totalReceitas", totalReceitas);
            req.setAttribute("receitasList", receitasList);
            req.setAttribute("idsProdutosList", idsProdutosList);
            req.setAttribute("paginaAtual", paginaAtual);
            req.setAttribute("totalPaginas", totalPaginas);
            req.setAttribute("filtro", filtro);
            req.getRequestDispatcher("/WEB-INF/views/receita/receitas.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }
}