package io.github.nutria.nutria.servlet.receitaServlet;

import io.github.nutria.nutria.dao.ReceitaDAO;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.model.Receita;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/receitas/inserir")
public class ReceitaInsertServlet extends HttpServlet {
    private final ReceitaDAO dao = new ReceitaDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String porcao;
        String idProdutoStr;
        Long idProduto;
        boolean success;
        Produto produto;
        Receita receita;

        porcao = request.getParameter("porcao");
        idProdutoStr = request.getParameter("idProduto");

        if (porcao == null || porcao.isBlank() || idProdutoStr == null || idProdutoStr.isBlank()) {
            response.setStatus(400);
            response.getWriter().write("Os campos de porção e id do produto são obrigatórios.");
            return;
        }

        try {
            idProduto = Long.parseLong(idProdutoStr);

            produto = new Produto();
            produto.setId(idProduto);

            receita = new Receita(porcao, produto);

            success = dao.insert(receita);

            if (success) {
                response.setStatus(201);
                request.setAttribute("message","Receita inserida com sucesso!");
            } else {
                response.setStatus(500);
                request.setAttribute("message","Erro ao inserir receita.");
                request.getRequestDispatcher("/erro.jsp").forward(request,response);
            }
        } catch (NumberFormatException e) {
            response.setStatus(400);
            response.getWriter().write("O ID do produto deve ser um número válido.");
        } catch (ServletException se){
            se.printStackTrace();
        }
    }
}