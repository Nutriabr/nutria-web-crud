package io.github.nutria.nutria.servlet.receitaIngredienteServlet;

import io.github.nutria.nutria.dao.ReceitaIngredienteDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.ReceitaIngrediente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/receitasIngredientes/editar")
public class ReceitaIngredienteUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();
        ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();

        try {
            String idStr = req.getParameter("idReceita");
            String id2Str = req.getParameter("idReceita");
            if (idStr == null || idStr.isEmpty() && id2Str == null || id2Str.isEmpty()) {
                throw new InvalidNumberException("ID", idStr);
            }

            req.setAttribute("quantidade", receitaIngrediente.getQuantidade());

            req.getRequestDispatcher("/WEB-INF/views/receitaIngrediente/editar.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/editar.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/editar.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();
        final String viewPath = "/WEB-INF/views/receitasIngredientes/receitasIngrediente.jsp";


        try {
            System.out.println(req.getParameter("idReceita"));
            Long idReceita = Long.parseLong(req.getParameter("idReceita"));
            Long idIngrediente = Long.parseLong(req.getParameter("idIngrediente"));

            ReceitaIngrediente receitaIngrediente = receitaIngredienteDAO.findById(idReceita, idIngrediente);

            receitaIngrediente.setQuantidade(Double.parseDouble(req.getParameter("quantidade")));

            if (receitaIngredienteDAO.update(receitaIngrediente)) {
                resp.sendRedirect(req.getContextPath() + "/receitaIngrediente/listar");
            }
        } catch (RequiredFieldException rfe) {
            System.err.println("[ERRO DE CAMPO OBRIGATÓRIO]: " + rfe);
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/editar").forward(req, resp);
        } catch (NumberFormatException nfe) {
            System.err.println("[FORMATO DE CAMPO INVÁLIDO");
            req.setAttribute("errorMessage", "Ops! " + nfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/editar").forward(req, resp);
        } catch (DataAccessException dae) {
            throw new DataAccessException("Erro ao acessar o banco de dados", dae);
        }
    }
}
