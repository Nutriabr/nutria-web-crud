package io.github.nutria.nutria.servlet.receitaServlet;
import java.io.*;

import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.service.ReceitaService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "receitaController", urlPatterns = {"/receitas", "/receitas/inserir", "/receitas/excluir", "/receitas/visualizar"})
public class ReceitaServlet extends HttpServlet{
    ReceitaService service = new ReceitaService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Declaração das variáveis;
        String nome;
        String porcao;
        String idProduto_str;
        long idProduto;
        String error;
        Receita receita;

        // Requisição do caminho
        String caminhoUrl = request.getServletPath();

        if(caminhoUrl.equals("/receitas/inserir")){
            // Parâmetros enviados na requisição
            nome = request.getParameter("nome");
            porcao = request.getParameter("porcao");
            idProduto_str = request.getParameter("idProduto");
            idProduto = Long.parseLong(idProduto_str);

            // Instanciação do objeto Receita com os dados obtidos
            receita = new Receita(nome,porcao,idProduto);

            // Chamada do método insert
            error = service.insert(receita);

            // Se tiver erro, retornar 400 (Bad Request)
            if(error != null){
                response.setStatus(400);
            }

            // Se tiver não tiver erro, retornar 201 (Created)
            else {
                response.setStatus(201);
            }

        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Requisição do caminho
        String caminhoUrl = request.getServletPath();

        if(caminhoUrl.equals("/receitas") || caminhoUrl.equals("/receitas/visualizar")){
            response.getWriter().write(service.findAll().toString());        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // Declaração das variáveis
        String id_str;
        long id;

        // Requisição do caminho
        String caminhoUrl = request.getServletPath();

        if(caminhoUrl.equals("/receitas/excluir")) {
            // Requisição do parâmetro do id
            id_str = request.getParameter("id");
            id = Long.parseLong(id_str);

            // Chamada do método deleteById e resposta
            if(service.deleteById(id)){
                // Se excluir, responde 200 OK.
                response.setStatus(200);
            }
            else {
                // Se não encontrar, responde 404 Not Found.
                response.setStatus(404);
            }
        }

    }
}