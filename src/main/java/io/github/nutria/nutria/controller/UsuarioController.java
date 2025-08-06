package io.github.nutria.nutria.controller;

import java.io.*;

import io.github.nutria.nutria.model.entity.Usuario;
import io.github.nutria.nutria.service.UsuarioService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "usuarioController", urlPatterns = {"/usuarios", "/usuarios/inserir"})
public class UsuarioController {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        UsuarioService service = new UsuarioService();

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

            String error = service.insertUser(usuario);

            if (error != null) {
                // Se houver erro, retorna o erro com o código 400
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, error);
            } else {
                // Se não houver erro, retorna o status 201 (Created)
                response.setStatus(HttpServletResponse.SC_CREATED);
            }

        }
        // Testando a conexão com o banco de dados
//        factory.testConnection();
    }
}