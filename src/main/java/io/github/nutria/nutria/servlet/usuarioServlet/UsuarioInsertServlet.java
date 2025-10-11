package io.github.nutria.nutria.servlet.usuarioServlet;

import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "UsuarioInsertServlet", value = "/usuario/insert")
public class UsuarioInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pega os parâmetros do formulário
        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String empresa = request.getParameter("empresa");
        String foto = request.getParameter("foto");

        // Cria um objeto Usuario e um objeto UsuarioDAO
        Usuario user = new Usuario(
                id,
                nome,
                email,
                senha,
                telefone,
                empresa,
                foto
        );
        UsuarioDAO dao = new UsuarioDAO();

        // Seta os atributos do objeto Usuario

        // Redireciona para a página de listagem de usuários
        if (dao.insert(user)) {
            response.sendRedirect("/pages/usuarios.jsp");
        } else {
            throw new DataAccessException("Erro ao inserir usuário no banco de dados.");
        }

    }
}
