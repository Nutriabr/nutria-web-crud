package io.github.nutria.nutria.servlet.usuarioServlet;

import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UsuarioUpdateServlet", value = "/usuario/atualizar")
public class UsuarioUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String viewPath = "/WEB-INF/views/usuario/editar.jsp";

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            Long id = Long.parseLong(req.getParameter("id"));

            Usuario usuario = usuarioDAO.findById(id);
            if (usuario != null) {
                req.setAttribute("usuario", usuario);
                req.getRequestDispatcher(viewPath).forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/usuario/listar");
            }
        } catch (DataAccessException dae) {
            throw new DataAccessException("Erro ao acessar o banco de dados", dae);
        }
    }
}