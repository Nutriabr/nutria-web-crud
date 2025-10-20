package io.github.nutria.nutria.servlet.receitaServlet;

import io.github.nutria.nutria.dao.ReceitaDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Receita;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/receita/editar")
public class ReceitaUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        String idStr = req.getParameter("id");

        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new InvalidNumberException("ID", idStr);
            }
            Long id = Long.parseLong(idStr);

            Receita receita = receitaDAO.findById(id);

            req.setAttribute("id", receita.getId());
            req.setAttribute("porcao", receita.getPorcao());
            req.setAttribute("idProduto", receita.getIdProduto());

            req.getRequestDispatcher("/WEB-INF/views/receita/editar.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/receita/editar.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receita/editar.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        String viewPath = "/WEB-INF/views/receita/editar.jsp";

        try {
            String idStr = req.getParameter("id");

            if (idStr == null || idStr.isBlank()) {
                throw new ValidationException("ID da receita não foi informado.");
            }

            Long id = Long.parseLong(idStr);
            Receita receita = receitaDAO.findById(id);

            receita.setPorcao(req.getParameter("porcao"));

            receitaDAO.update(receita);

            resp.sendRedirect(req.getContextPath() + "/receita/listar");

        } catch (EntityNotFoundException e) {
            req.setAttribute("errorMessage", "Receita não encontrado para atualização.");
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ocorreu um erro inesperado: " + e.getMessage());
            req.getRequestDispatcher(viewPath).forward(req, resp);
        }
    }
}