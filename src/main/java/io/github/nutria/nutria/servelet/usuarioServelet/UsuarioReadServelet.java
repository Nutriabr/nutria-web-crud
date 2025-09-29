package io.github.nutria.nutria.servelet.usuarioServelet;

import io.github.nutria.nutria.service.UsuarioService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "usuarioReadController", urlPatterns = {"/usuarios/visualizar", "/usuarios"})
public class UsuarioReadServelet extends HttpServlet {
    private UsuarioService service = new UsuarioService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (path.equals("/usuarios") || path.equals("/usuarios/visualizar")) {
            response.getWriter().write(service.findAll().toString());
        }
    }

}
