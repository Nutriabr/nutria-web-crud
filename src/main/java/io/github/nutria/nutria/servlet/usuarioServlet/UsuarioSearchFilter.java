package io.github.nutria.nutria.servlet.usuarioServlet;

import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuario/buscar")
public class UsuarioSearchFilter extends HttpServlet {
    private static final int TOTAL_USUARIOS_PAGE = 4;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int currentPage = 1;

        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                System.err.println("Parâmetro de página inválido: " + pageParam);
            }
        }

        try {
            String busca = req.getParameter("busca");
            int totalUsuarios = usuarioDAO.contarTodosFiltrados(busca);
            int totalPages = (int) Math.ceil((double) totalUsuarios / TOTAL_USUARIOS_PAGE);

            if (currentPage < 1) {
                currentPage = 1;
            } else if (currentPage > totalPages && totalPages > 0) {
                currentPage = totalPages;
            }

            List<Usuario> usuarioList = usuarioDAO.buscarPorNomeDeUsuarioOuDominioEmail(busca, currentPage);

            req.setAttribute("usuarioList", usuarioList);
            req.setAttribute("totalUsuarios", totalUsuarios);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("busca", busca);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/usuario/usuarios.jsp");
            dispatcher.forward(req, resp);

        } catch (
                DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
