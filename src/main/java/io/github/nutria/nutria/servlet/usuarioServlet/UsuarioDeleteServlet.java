package io.github.nutria.nutria.servlet.usuarioServlet;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.EntityNotFoundException;
import io.github.nutria.nutria.exceptions.InvalidNumberException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usuario/excluir")
public class UsuarioDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("input-id");
        Long id = Long.parseLong(idStr);

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            usuarioDAO.deleteById(id);

            resp.sendRedirect(req.getContextPath() + "/usuario/listar");
        }  catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/usuario/editar.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/usuario/editar.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }

    }
}