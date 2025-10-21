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
import java.util.List;

@WebServlet("/produto/listar")
public class
ProdutoSelectServlet extends HttpServlet {
    private final int TOTAL_PRODUTO_PAGE = 4;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int currentPage = 1;
        String pageParam = req.getParameter("page");


        if(pageParam != null){
            try{
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException nfe){
                System.err.println("Parâmetro de página inválida: " + pageParam);
            }
        }

        try {
            int totalProdutos = produtoDAO.contarTodos();
            int totalPages = (int) Math.ceil((double) totalProdutos / TOTAL_PRODUTO_PAGE);
            if (totalPages == 0) totalPages = 1;
            if(currentPage < 1){
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }
            List<Produto> produtoList = produtoDAO.buscarTodos(currentPage);
            req.setAttribute("totalProdutos",totalProdutos);
            req.setAttribute("produtosList", produtoList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages",totalPages);
            req.getRequestDispatcher("/WEB-INF/views/produto/produtos.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}