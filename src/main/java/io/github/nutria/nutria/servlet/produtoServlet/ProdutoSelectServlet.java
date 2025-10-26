package io.github.nutria.nutria.servlet.produtoServlet;

import io.github.nutria.nutria.dao.ProdutoDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/produto/listar")
public class
ProdutoSelectServlet extends HttpServlet {
    private final int TOTAL_PRODUTO_PAGE = 4;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int currentPage = 1;
        String pageParam = req.getParameter("page");
        String filtro = req.getParameter("busca");
        List<Produto> produtosList = new ArrayList<>();
        List<Long> idsAdicionados = new ArrayList<>();
        int totalProdutos;

        if(pageParam != null){
            try{
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException nfe){
                System.err.println("Parâmetro de página inválida: " + pageParam);
            }
        }

        try {
            if(filtro == null || filtro.isEmpty()){
                produtosList = produtoDAO.buscarTodos(currentPage);
                totalProdutos = produtoDAO.contarTodos();

            } else {
                try{
                    Long numero = Long.parseLong(filtro);
                    Produto produto = produtoDAO.buscarPorId(numero);
                    if (produto != null) {
                        produtosList.add(produto);
                        idsAdicionados.add(produto.getId());
                    }

                    List<Produto> produtosComIdUsuario = produtoDAO.buscarPorIdUsuario(numero, currentPage);
                    for (Produto p : produtosComIdUsuario) {
                        if (!idsAdicionados.contains(p.getId())) {
                            produtosList.add(p);
                            idsAdicionados.add(p.getId());
                        }
                    }
                }catch (NumberFormatException nfe){
                    produtosList = produtoDAO.buscarPorNome(filtro,currentPage);

                }
                totalProdutos =  produtosList.size();
            }
            int totalPages = (int) Math.ceil((double) totalProdutos / TOTAL_PRODUTO_PAGE);
            if (totalPages == 0) totalPages = 1;
            if(currentPage < 1){
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }
            req.setAttribute("totalProdutos",totalProdutos);
            req.setAttribute("produtosList", produtosList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages",totalPages);
            req.setAttribute("filtro", filtro);
            req.getRequestDispatcher("/WEB-INF/views/produto/produtos.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}