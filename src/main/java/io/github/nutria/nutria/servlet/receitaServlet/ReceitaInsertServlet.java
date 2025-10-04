package io.github.nutria.nutria.servlet.receitaServlet;

//import io.github.nutria.nutria.dao.ReceitaDAO;
//import io.github.nutria.nutria.model.Receita;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//@WebServlet(name = "receitaController", urlPatterns = {"/receitas", "/receitas/inserir"})
//public class ReceitaInsertServlet extends HttpServlet {
//    // 1. Instância do DAO para acessar o banco de dados
//    private final ReceitaDAO dao = new ReceitaDAO();
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        // 2. Recuperar o caminho da URL da requisição
//        String caminhoUrl = request.getServletPath();
//
//        // 3. Declaração das variáveis
//        String nome;
//        String porcao;
//        String idProdutoStr;
//        long idProduto;
//        boolean error;
//        Receita receita;
//
//        if(caminhoUrl.equals("/receitas/inserir")){
//            // 4. Requisitar os parâmetros enviados
//            nome = request.getParameter("nome");
//            porcao = request.getParameter("porcao");
//            idProdutoStr = request.getParameter("idProduto");
//            idProduto = 0;
//            if (idProdutoStr != null && !idProdutoStr.isBlank()) {
//                idProduto = Long.parseLong(idProdutoStr);
//            }
//
//            // 5. Instanciação do objeto Receita com os dados obtidos
//            receita = new Receita(nome,porcao,idProduto);
//
//            // 6. Validação dos campos obrigatórios
//            if(porcao == null ||  porcao.isBlank() || idProduto <=0 ){
//                response.setStatus(400);
//                response.getWriter().write("Os campos de porção e id do produto são obrigatórios.");
//                return;
//            }
//
//            // 7. Chamada do método insert
//            error = dao.insert(receita);
//
//            // 8. Se tiver não tiver erro, retornar 201 (Created)
//            if(error){
//                response.setStatus(201);
//            }
//
//            // 9. Se tiver erro, retornar 400 (Bad Request)
//            else {
//                response.setStatus(400);
//            }
//
//        }
//    }
//}
