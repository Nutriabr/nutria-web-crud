package io.github.nutria.nutria.servelet.usuarioServelet;

import io.github.nutria.nutria.service.UsuarioService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "usuarioDeleteController", urlPatterns = {"/usuarios/excluir"})
public class UsuarioDeleteServelet extends HttpServlet {
    private UsuarioService service = new UsuarioService();
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (path.equals("/usuarios/excluir")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (!(id <= 0)) {
                boolean deleted = service.deleteUserById(id);
                if (deleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(String.format("Usuário com ID: %s deletado com sucesso!", id));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(String.format("Usuário com ID: %s não existe ou não pode ser deletado!", id));
                }
            }
        }
    }
}
