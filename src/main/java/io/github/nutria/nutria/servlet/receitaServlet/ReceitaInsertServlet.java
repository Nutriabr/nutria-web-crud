package io.github.nutria.nutria.servlet.receitaServlet;

import io.github.nutria.nutria.dao.ReceitaDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.RequiredFieldException;
import io.github.nutria.nutria.exceptions.ValidationException;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.model.Receita;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/receitas/adicionar")
public class ReceitaInsertServlet extends HttpServlet {
    private final ReceitaDAO dao = new ReceitaDAO();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String porcao;
        String idProdutoStr;
        Long idProduto;
        boolean success;
        Produto produto;
        Receita receita;

        porcao = req.getParameter("porcao");
        idProdutoStr = req.getParameter("idProduto");
        try {
            idProduto = Long.parseLong(idProdutoStr);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID do produto deve ser um número válido.");
            req.getRequestDispatcher("/WEB-INF/views/receita/adicionar.jsp").forward(req, resp);
            return;
        }

        produto = new Produto();
        produto.setId(idProduto);
        receita = new Receita(porcao, produto);


        try {
            success = dao.insert(receita);
            if (!success) throw new DataAccessException("Erro ao inserir receita no banco de dados.");

            req.setAttribute("message", "Receita inserida com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/receitas/listar");

        } catch (RequiredFieldException rfe){
            System.err.println("[ERRO DE CAMPOS OBRIGATÓRIOS AUSENTES]");
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receita/adicionar.jsp").forward(req,resp);
        } catch (ValidationException ve){
            System.err.println("[ERRO DE VALIDAÇÃO]" + ve);
            req.setAttribute("errorMessage", "Ops! " + ve.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receita/adicionar.jsp").forward(req,resp);
        }  catch (DataAccessException dae){
            System.err.println("[ERRO NO BANCO]");
            req.setAttribute("errorMessage","Ops! Não foi possível salvar a receita!");
            req.getRequestDispatcher("/WEB-INF/views/receita/adicionar.jsp").forward(req,resp);
        }
    }
}