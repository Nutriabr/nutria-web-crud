package io.github.nutria.nutria.servlet.produtoServlet;

import io.github.nutria.nutria.dao.ProdutoDAO;
import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/produto/listar")
public class ProdutoSelectServlet extends HttpServlet {
    private final int TOTAL_PRODUTO_PAGE = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int currentPage = 1;
        String pageParam = req.getParameter("page");
        String filtro = req.getParameter("busca");

        if (pageParam != null) {
            try { currentPage = Integer.parseInt(pageParam); } catch (NumberFormatException ignored) {}
        }

        List<Produto> produtosList;
        List<String> emailsList;
        int totalProdutos = 0;

        try {
            if (filtro == null || filtro.trim().isEmpty()) {
                totalProdutos = produtoDAO.contarTodos();
                produtosList = produtoDAO.buscarTodos(currentPage);
            } else {
                try {
                    Long numero = Long.parseLong(filtro.trim());
                    totalProdutos = produtoDAO.contarPorIdOuIdUsuario(numero);
                    produtosList = produtoDAO.buscarPorIdOuIdUsuario(numero, currentPage);
                } catch (NumberFormatException nfe) {
                    totalProdutos = produtoDAO.contarPorNomeProdutoOuNomeUsuario(filtro);
                    produtosList = produtoDAO.buscarPorNomeProdutoOuNomeUsuario(filtro, currentPage);
                }
            }

            int totalPages = (int) Math.ceil((double) totalProdutos / TOTAL_PRODUTO_PAGE);
            if (totalPages == 0) totalPages = 1;
            if (currentPage < 1) currentPage = 1;
            if (currentPage > totalPages) currentPage = totalPages;

            emailsList = produtoDAO.buscarEmails();

            req.setAttribute("totalProdutos", totalProdutos);
            req.setAttribute("produtosList", produtosList);
            req.setAttribute("emailsList", emailsList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("filtro", filtro);

            req.getRequestDispatcher("/WEB-INF/views/produto/produtos.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }
}
