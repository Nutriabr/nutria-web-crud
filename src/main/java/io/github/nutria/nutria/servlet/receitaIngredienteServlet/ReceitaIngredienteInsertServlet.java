package io.github.nutria.nutria.servlet.receitaIngredienteServlet;

import io.github.nutria.nutria.dao.ReceitaIngredienteDAO;
import io.github.nutria.nutria.dao.ReceitaIngredienteDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.ReceitaIngrediente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/receitasIngredientes/adicionar")
public class ReceitaIngredienteInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPage = req.getParameter("page");
        final String viewPath = "/WEB-INF/views/receitaIngrediente/adicionar.jsp";
        req.getRequestDispatcher(viewPath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaIngredienteDAO dao = new ReceitaIngredienteDAO();
        ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();

        try {
            receitaIngrediente.setIdReceita(Long.parseLong(req.getParameter("idReceita")));
            receitaIngrediente.setIdIngrediente(Long.parseLong(req.getParameter("idIngrediente")));
            receitaIngrediente.setQuantidade(Integer.parseInt(req.getParameter("quantity")));

            dao.insert(receitaIngrediente);

            resp.sendRedirect(req.getContextPath() + "/receitaIngrediente/listar");
        } catch (RequiredFieldException rfe) {
            System.err.println("[ERRO DE CAMPO OBRIGATÓRIO]: " + rfe);
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (DataAccessException dae) {
            throw new DataAccessException("Erro ao acessar o banco de dados", dae);
        }
    }
}
