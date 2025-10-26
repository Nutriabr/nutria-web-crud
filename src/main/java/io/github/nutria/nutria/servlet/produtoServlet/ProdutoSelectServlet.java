package io.github.nutria.nutria.servlet.produtoServlet;

import io.github.nutria.nutria.dao.ProdutoDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/produto/listar")
public class ProdutoSelectServlet extends HttpServlet {
    private final int TOTAL_PRODUTO_PAGE = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int currentPage = 1;
        String pageParam = req.getParameter("page");
        String filtro = req.getParameter("busca");
        List<Produto> produtosList = new ArrayList<>();
        int totalProdutos = 0;

        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException ignored) {}
        }

        try {
            if (filtro == null || filtro.isEmpty()) {
                totalProdutos = produtoDAO.contarTodos();
                produtosList = produtoDAO.buscarTodos(currentPage);
            } else {
                try {
                    Long numero = Long.parseLong(filtro);
                    Produto produto = produtoDAO.buscarPorId(numero);
                    List<Produto> produtosComIdUsuario = produtoDAO.buscarPorIdUsuario(numero, currentPage);
                    totalProdutos = (produto != null ? 1 : 0) + produtoDAO.contarPorIdUsuario(numero);

                    if (produto != null) {
                        boolean jaAdicionado = false;
                        for (Produto p : produtosComIdUsuario) {
                            if (p.getId().equals(produto.getId())) {
                                jaAdicionado = true;
                            }
                            produtosList.add(p);
                        }
                        if (produto != null && !jaAdicionado) {
                            produtosList.add(0, produto);
                        }
                    }
                    // adicionar todos do usuario
                    for (Produto p : produtosComIdUsuario) {
                        produtosList.add(p);
                    }

                } catch (NumberFormatException nfe) {
                    totalProdutos = produtoDAO.contarPorNome(filtro);
                    produtosList = produtoDAO.buscarPorNome(filtro, currentPage);
                }
            }

            int totalPages = (int) Math.ceil((double) totalProdutos / TOTAL_PRODUTO_PAGE);
            if (totalPages == 0) totalPages = 1;
            if (currentPage < 1) currentPage = 1;
            if (currentPage > totalPages) currentPage = totalPages;

            req.setAttribute("totalProdutos", totalProdutos);
            req.setAttribute("produtosList", produtosList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("filtro", filtro);

            req.getRequestDispatcher("/WEB-INF/views/produto/produtos.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
