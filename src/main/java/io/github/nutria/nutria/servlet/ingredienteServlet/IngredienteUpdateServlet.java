package io.github.nutria.nutria.servlet.ingredienteServlet;

import io.github.nutria.nutria.dao.IngredienteDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Ingrediente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ingrediente/editar")
public class IngredienteUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        String idStr = req.getParameter("id");

        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new InvalidNumberException("ID", idStr);
            }
            Long id = Long.parseLong(idStr);

            Ingrediente ingrediente = ingredienteDAO.findById(id);

            req.setAttribute("id", ingrediente.getId());
            req.setAttribute("nome", ingrediente.getNome());

            req.getRequestDispatcher("/WEB-INF/views/ingrediente/editar.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/ingrediente/editar.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/ingrediente/editar.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        String viewPath = "/WEB-INF/views/ingrediente/editar.jsp";

        try {
            String idStr = req.getParameter("id");

            if (idStr == null || idStr.isBlank()) {
                throw new ValidationException("ID do ingrediente não foi informado.");
            }

            Long id = Long.parseLong(idStr);
            Ingrediente ingrediente = ingredienteDAO.findById(id);

            ingrediente.setNome(req.getParameter("nome"));

            ingredienteDAO.update(ingrediente);

            resp.sendRedirect(req.getContextPath() + "/ingrediente/listar");

        } catch (EntityNotFoundException e) {
            req.setAttribute("errorMessage", "Ingrediente não encontrado para atualização.");
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ocorreu um erro inesperado: " + e.getMessage());
            req.getRequestDispatcher(viewPath).forward(req, resp);
        }
    }
}