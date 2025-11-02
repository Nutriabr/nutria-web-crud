package io.github.nutria.nutria.servlet.produtoServlet;

import io.github.nutria.nutria.dao.ProdutoDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.EntityNotFoundException;
import io.github.nutria.nutria.exceptions.InvalidNumberException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/produto/excluir")
public class ProdutoDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("input-id");
        String acao = req.getParameter("acao");

        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();

            if ("deletarPorEmail".equals(acao)) {
                String opcao = req.getParameter("opcao");
                if (opcao != null && !opcao.isEmpty()) {
                    produtoDAO.deletarPorEmailUsuario(opcao);
                    req.getSession().setAttribute("successMessage",
                            "Produtos do usuário " + opcao + " deletados com sucesso!");
                }
            } else if (idStr != null && !idStr.isEmpty()) {
                Long id = Long.parseLong(idStr);
                produtoDAO.deletarPorId(id);
                req.getSession().setAttribute("successMessage", "Produto deletado com sucesso!");
            }

            resp.sendRedirect(req.getContextPath() + "/produto/listar");

        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/produto/produtos.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/produto/produtos.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }
}
