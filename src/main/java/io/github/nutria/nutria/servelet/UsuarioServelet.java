package io.github.nutria.nutria.servelet;

import java.io.*;

import io.github.nutria.nutria.model.Usuario;
import io.github.nutria.nutria.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "usuarioController", urlPatterns = {"/usuarios", "/usuarios/inserir", "/usuarios/visualizar", "/usuarios/excluir"})
public class UsuarioServelet extends HttpServlet {
    UsuarioService service = new UsuarioService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (path.equals("/usuarios/inserir")) {
            // Instanciando o objeto Usuario com os parâmetros recebidos da requisição
            Usuario usuario = new Usuario(
                    request.getParameter("nome"),
                    request.getParameter("email"),
                    request.getParameter("senha"),
                    request.getParameter("telefone"),
                    request.getParameter("empresa"),
                    request.getParameter("foto")
            );

            String error = service.insert(usuario);

            if (error != null) {
                // Se houver erro, retorna o erro com o código 400
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, error);
            } else {
                // Se não houver erro, retorna o status 201 (Created)
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write(String.format("Usuário com email: %s cadastrado com sucesso!", usuario.getEmail()));
            }
        }
        // Testando a conexão com o banco de dados
//        factory.testConnection();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (path.equals("/usuarios") || path.equals("/usuarios/visualizar")) {
            response.getWriter().write(service.findAll().toString());
        }
    }

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