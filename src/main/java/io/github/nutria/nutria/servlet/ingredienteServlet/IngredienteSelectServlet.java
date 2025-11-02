package io.github.nutria.nutria.servlet.ingredienteServlet;

import io.github.nutria.nutria.dao.IngredienteDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Ingrediente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ingrediente/listar")
public class IngredienteSelectServlet extends HttpServlet {
    private final int TOTAL_INGREDIENTE_PAGE = 4;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        int currentPage = 1;
        String pageParam = req.getParameter("page");
        String filtro = req.getParameter("busca");
        List<Ingrediente> ingredientesList = new ArrayList<>();
        List<String> nomesList;
        int totalIngredientes;


        if(pageParam != null){
            try{
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException nfe){
                System.err.println("Parâmetro de página inválida: " + pageParam);
            }
        }

        try {
            if(filtro == null || filtro.isEmpty()){
                 totalIngredientes = ingredienteDAO.contarTodos();
                 ingredientesList = ingredienteDAO.buscarTodos(currentPage);
            }
            else {
                try {
                    Long id = Long.parseLong(filtro);
                    Ingrediente ingrediente = ingredienteDAO.buscarPorId(id);
                    if(ingrediente != null){
                        ingredientesList.add(ingrediente);
                    }
                    totalIngredientes = 1;
                } catch (NumberFormatException nfe){
                    ingredientesList = ingredienteDAO.buscarPorNome(filtro, currentPage);
                    totalIngredientes = ingredienteDAO.contarPorNome(filtro);
                }
            }

            int totalPages = (int) Math.ceil((double) totalIngredientes / TOTAL_INGREDIENTE_PAGE);
            if (totalPages == 0) totalPages = 1;
            if(currentPage < 1){
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }

            nomesList = ingredienteDAO.buscarNomes();
            req.setAttribute("totalIngredientes",totalIngredientes);
            req.setAttribute("ingredientesList", ingredientesList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages",totalPages);
            req.setAttribute("filtro",filtro);
            req.setAttribute("nomesList",nomesList);
            req.getRequestDispatcher("/WEB-INF/views/ingrediente/ingredientes.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
