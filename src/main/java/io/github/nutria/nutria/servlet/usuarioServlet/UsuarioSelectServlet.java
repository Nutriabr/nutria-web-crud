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

@WebServlet("/usuario/listar")
public class UsuarioSelectServlet extends HttpServlet {
    private static final int TOTAL_USUARIOS_PAGINAS = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int paginaAtual = 1;
        int totalPages = 1;
        int totalUsuarios = 0;
        List<Usuario> usuarioList = null;

        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            try {
                paginaAtual = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                System.err.println("Parâmetro de página inválido: " + pageParam);
            }
        }

        try {
            String busca = req.getParameter("busca");

            if (busca != null && !busca.isEmpty()) {
                totalUsuarios = usuarioDAO.contarTodosFiltrados(busca);
                totalPages = (int) Math.ceil((double) totalUsuarios / TOTAL_USUARIOS_PAGINAS);
                usuarioList = usuarioDAO.buscarPorNomeDeUsuarioOuDominioEmail(busca, paginaAtual);
            } else {
                totalUsuarios = usuarioDAO.contarTodos();
                totalPages = (int) Math.ceil((double) totalUsuarios / TOTAL_USUARIOS_PAGINAS);
                usuarioList = usuarioDAO.buscarTodos(paginaAtual);
            }

            if (paginaAtual < 1) {
                paginaAtual = 1;
            } else if (paginaAtual > totalPages && totalPages > 0) {
                paginaAtual = totalPages;
            }


            req.setAttribute("usuarioList", usuarioList);
            req.setAttribute("totalUsuarios", totalUsuarios);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", paginaAtual);
            req.setAttribute("busca", busca);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/usuario/usuarios.jsp");
            dispatcher.forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}