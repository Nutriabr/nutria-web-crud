package io.github.nutria.nutria.servlet.ingredienteServlet;

import io.github.nutria.nutria.dao.IngredienteDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.RequiredFieldException;
import io.github.nutria.nutria.exceptions.ValidationException;
import io.github.nutria.nutria.model.Ingrediente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ingrediente/adicionar")
public class IngredienteInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/ingrediente/adicionar.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome;
        boolean success;
        Ingrediente ingrediente;
        IngredienteDAO ingredienteDAO = new IngredienteDAO();


        nome = req.getParameter("nome");

        ingrediente = new Ingrediente(nome);


        try {
            success = ingredienteDAO.inserir(ingrediente);
            if (!success) throw new DataAccessException("Erro ao inserir ingrediente no banco de dados.");

            req.getSession().setAttribute("message", "Ingrediente inserido com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/ingrediente/listar");

        } catch (RequiredFieldException rfe){
            System.err.println("[ERRO DE CAMPOS OBRIGATÓRIOS AUSENTES]");
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/ingrediente/adicionar.jsp").forward(req,resp);
        } catch (ValidationException ve){
            System.err.println("[ERRO DE VALIDAÇÃO]" + ve);
            req.setAttribute("errorMessage", "Ops! " + ve.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/ingrediente/adicionar.jsp").forward(req,resp);
        }  catch (DataAccessException dae){
            System.err.println("[ERRO NO BANCO]");
            req.setAttribute("errorMessage","Ops! Não foi possível salvar o ingrediente!");
            req.getRequestDispatcher("/WEB-INF/views/ingrediente/adicionar.jsp").forward(req,resp);
        }
    }
}