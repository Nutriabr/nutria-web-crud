package io.github.nutria.nutria.servlet.receitaServlet;

import io.github.nutria.nutria.dao.ReceitaDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Receita;
import io.github.nutria.nutria.model.ReceitaIngrediente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/receita/listar")
public class ReceitaSelectServlet extends HttpServlet {
    private final int TOTAL_RECEITA_PAGE = 4;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        int currentPage = 1;
        String pageParam = req.getParameter("page");
        String filtro = req.getParameter("busca");
        List<Receita> receitasList = new ArrayList<>();
        List<Long> idsAdicionados = new ArrayList<>();

        int totalReceitas;
        int totalPages;


        if(pageParam != null){
            try{
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException nfe){
                System.err.println("Parâmetro de página inválida: " + pageParam);
            }
        }

        try {
            if (filtro == null || filtro.isEmpty()){
                totalReceitas = receitaDAO.contarTodos();
                totalPages = (int) Math.ceil((double) totalReceitas / TOTAL_RECEITA_PAGE);
                if (totalPages == 0) totalPages = 1;
                if(currentPage < 1){
                    currentPage = 1;
                } else if (currentPage > totalPages && totalPages > 0) {
                    currentPage = totalPages;
                }
                receitasList = receitaDAO.buscarTodos(currentPage);
            }
            else {
                try {
                    Long numero = Long.parseLong(filtro);
                    Receita receita = receitaDAO.buscarPorId(numero);
                    if (receita != null){
                        receitasList.add(receita);
                        idsAdicionados.add(receita.getId());
                    }

                    List<Receita> receitasComIdProduto = receitaDAO.buscarPorIdProduto(numero, currentPage);
                    for (Receita r : receitasComIdProduto) {
                        if (!idsAdicionados.contains(r.getId())) {
                            receitasList.add(r);
                            idsAdicionados.add(r.getId());
                        }
                    }

                    totalReceitas = receitaDAO.contarPorId(numero) + receitaDAO.contarPorIdProduto(numero);
                    totalPages = (int) Math.ceil((double) totalReceitas / TOTAL_RECEITA_PAGE);

                } catch (NumberFormatException nfe){
                    receitasList = receitaDAO.buscarPorPorcao(filtro,currentPage);
                    totalReceitas = receitaDAO.contarPorPorcao(filtro);
                    totalPages = (int) Math.ceil((double) totalReceitas / TOTAL_RECEITA_PAGE);
                    if (totalPages == 0) totalPages = 1;
                    if(currentPage < 1){
                        currentPage = 1;
                    } else if (currentPage > totalPages && totalPages > 0) {
                        currentPage = totalPages;
                    }
                }
            }
            req.setAttribute("totalReceitas",totalReceitas);
            req.setAttribute("receitasList", receitasList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages",totalPages);
            req.setAttribute("filtro",filtro);
            req.getRequestDispatcher("/WEB-INF/views/receita/receitas.jsp").forward(req, resp);


        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}