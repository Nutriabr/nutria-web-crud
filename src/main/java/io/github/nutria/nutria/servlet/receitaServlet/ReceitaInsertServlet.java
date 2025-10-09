//package io.github.nutria.nutria.servlet.receitaServlet;
//
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
//    private final ReceitaDAO dao = new ReceitaDAO();
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
//        String caminhoUrl = request.getServletPath();
//
//        String nome;
//        String porcao;
//        String idProdutoStr;
//        long idProduto;
//        boolean error;
//        Receita receita;
//
//
//        nome = request.getParameter("nome");
//        porcao = request.getParameter("porcao");
//        idProdutoStr = request.getParameter("idProduto");
//        idProduto = 0;
//        if (idProdutoStr != null && !idProdutoStr.isBlank()) {
//            idProduto = Long.parseLong(idProdutoStr);
//        }
//
//        receita = new Receita(nome,porcao,idProduto);
//
//        if(porcao == null ||  porcao.isBlank() || idProduto <=0 ){
//            response.setStatus(400);
//            response.getWriter().write("Os campos de porção e id do produto são obrigatórios.");
//            return;
//        }
//
//        error = dao.insert(receita);
//
//        if(error){
//            response.setStatus(201);
//        }
//
//        else {
//            response.setStatus(400);
//        }
//    }
//}