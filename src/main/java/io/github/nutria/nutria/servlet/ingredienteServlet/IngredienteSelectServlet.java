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
import java.util.List;

@WebServlet("/ingrediente/listar")
public class IngredienteSelectServlet extends HttpServlet {
    private final int TOTAL_INGREDIENTE_PAGE = 4;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IngredienteDAO ingredienteDAO = new IngredienteDAO();
        int currentPage = 1;
        String pageParam = req.getParameter("page");


        if(pageParam != null){
            try{
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException nfe){
                System.err.println("Parâmetro de página inválida: " + pageParam);
            }
        }

        try {
            int totalIngredientes = ingredienteDAO.contarTodos();
            int totalPages = (int) Math.ceil((double) totalIngredientes / TOTAL_INGREDIENTE_PAGE);
            if (totalPages == 0) totalPages = 1;
            if(currentPage < 1){
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }
            List<Ingrediente> ingredienteList = ingredienteDAO.buscarTodos(currentPage);
            req.setAttribute("totalIngredientes",totalIngredientes);
            req.setAttribute("ingredientesList", ingredienteList);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("totalPages",totalPages);
            req.getRequestDispatcher("/WEB-INF/views/ingrediente/ingredientes.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
