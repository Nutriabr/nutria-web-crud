package io.github.nutria.nutria.servelet.usuarioServelet;

import io.github.nutria.nutria.model.Usuario;
import io.github.nutria.nutria.service.UsuarioService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "usuarioCreateController", urlPatterns = {"/usuarios/inserir"})
public class UsuarioCreateServelet extends HttpServlet {
    UsuarioService service = new UsuarioService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();

        if (path.equals("/usuarios/inserir")) {
            // Instanciando o objeto Usuario com os parâmetros recebidos da requisição
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String telefone = request.getParameter("telefone");
            String empresa = request.getParameter("empresa");
            String foto = request.getParameter("foto");

            Usuario usuario = new Usuario(nome, email, senha, telefone, empresa, foto);

            String result = service.insert(usuario);

            if (result != null) {
                // Se houver erro, retorna o erro com o código 400
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, result);
            } else {
                // Se não houver erro, retorna o status 201 (Created)
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write(String.format("Usuário com email: %s cadastrado com sucesso!", usuario.getEmail()));
            }
        }
    }
}
