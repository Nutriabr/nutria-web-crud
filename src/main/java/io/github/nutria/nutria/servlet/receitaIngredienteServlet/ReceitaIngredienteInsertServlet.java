package io.github.nutria.nutria.servlet.receitaIngredienteServlet;

import io.github.nutria.nutria.dao.ReceitaIngredienteDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.RequiredFieldException;
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
        req.getRequestDispatcher("/WEB-INF/views/receitaIngrediente/adicionar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();
        ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();

        int lastPage = (int) Math.ceil((double) receitaIngredienteDAO.contarTodos() / 4);

        try {
            receitaIngrediente.setIdReceita(Long.parseLong(req.getParameter("idReceita")));
            receitaIngrediente.setIdIngrediente(Long.parseLong(req.getParameter("idIngrediente")));
            receitaIngrediente.setQuantidade(Integer.parseInt(req.getParameter("quantidade")));

            receitaIngredienteDAO.inserir(receitaIngrediente);

            req.getSession().setAttribute("successMessage", "Receita Ingrediente adicionada com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/receitaIngrediente/listar?page=" + (lastPage + 1));
        } catch (RequiredFieldException rfe) {
            System.err.println("[ERRO DE CAMPO OBRIGATÓRIO]: " + rfe);
            req.setAttribute("quantidade", req.getParameter("quantity"));
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/adicionar.jsp").forward(req, resp);
        } catch (NumberFormatException nfe) {
            System.err.println("[ERRO DE NÚMERO INCORRETO]: " + nfe);
            req.setAttribute("quantidade", req.getParameter("quantity"));
            req.setAttribute("errorMessage", "Ops! " + nfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/adicionar.jsp").forward(req, resp);
        } catch (DataAccessException dae) {
            throw new DataAccessException("Erro ao acessar o banco de dados", dae);
        }
    }
}
