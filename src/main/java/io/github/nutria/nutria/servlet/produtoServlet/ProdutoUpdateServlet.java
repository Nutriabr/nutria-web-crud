package io.github.nutria.nutria.servlet.produtoServlet;

import io.github.nutria.nutria.dao.ProdutoDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/produto/editar")
public class ProdutoUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        String idStr = req.getParameter("id");

        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new InvalidNumberException("ID", idStr);
            }
            Long id = Long.parseLong(idStr);

            Produto produto = produtoDAO.findById(id);

            req.setAttribute("id", produto.getId());
            req.setAttribute("nome", produto.getNome());
            req.setAttribute("idProduto", produto.getIdUsuario());

            req.getRequestDispatcher("/WEB-INF/views/produto/editar.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/produto/editar.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/produto/editar.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        String viewPath = "/WEB-INF/views/produto/editar.jsp";

        try {
            String idStr = req.getParameter("id");
            String idUsuarioStr = req.getParameter("idUsuario");

            if (idStr == null || idStr.isBlank()) {
                throw new ValidationException("ID do produto não foi informado.");
            }

            Long id = Long.parseLong(idStr);
            Produto produto = produtoDAO.findById(id);

            produto.setNome(req.getParameter("nome"));

            produtoDAO.alterar(produto);

            resp.sendRedirect(req.getContextPath() + "/produto/listar");

        } catch (EntityNotFoundException e) {
            req.setAttribute("errorMessage", "Produto não encontrado para atualização.");
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ocorreu um erro inesperado: " + e.getMessage());
            req.getRequestDispatcher(viewPath).forward(req, resp);
        }
    }
}
