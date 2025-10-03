package io.github.nutria.nutria.servelet.receitaServelet;

import io.github.nutria.nutria.dao.ReceitaDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "receitaController", urlPatterns = {"/receitas", "/receitas/visualizar"})
public class ReceitaFindAllController extends HttpServlet {
    // 1. Instância do DAO para acessar o banco de dados
    private final ReceitaDAO dao = new ReceitaDAO();

    // 2. Declaração das variáveis
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Requisição do caminho
        String caminhoUrl = request.getServletPath();

        if(caminhoUrl.equals("/receitas") || caminhoUrl.equals("/receitas/visualizar")){
            response.getWriter().write(dao.findAll(1).toString());        }
    }
}
