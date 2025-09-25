package io.github.nutria.nutria.controller;

import java.io.*;
import java.util.List;

import io.github.nutria.nutria.model.Usuario;
import io.github.nutria.nutria.service.UsuarioService;
import io.github.nutria.nutria.dao.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "usuarioController", urlPatterns = {"/usuarios", "/usuarios/inserir", "/usuarios/visualizar", "/usuarios/excluir", "/usuarios/atualizar"})
public class UsuarioController extends HttpServlet {
    UsuarioService service = new UsuarioService();
    UsuarioDAO dao = new UsuarioDAO();

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
            int contaUsuario = service.findAll().size();

            if (error != null) {
                // Se houver erro, retorna o erro com o código 400
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, error);
            } else {
                // Se não houver erro, retorna o status 201 (Created)
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write(String.format("Usuário com email: %s cadastrado com sucesso!", usuario.getEmail()));
                contaUsuario++;
            }
        }
        // Testando a conexão com o banco de dados
//        factory.testConnection();
    }

    //        String path = request.getServletPath();
//
//        if (path.equals("/usuarios") || path.equals("/usuarios/visualizar")) {
//            response.getWriter().write(service.findAll().toString());
//        }
//        parametroPagina = request.getParameter("page");
//
//        if (parametroPagina != null) {
//            page = Integer.parseInt(parametroPagina);
//        }
//
//        // Busca lista de usuários
//        List<Usuario> usuarios = dao.findAll(page);
//
//        // Conta todos os usuários
//        totalUsuarios = dao.countAll();
//
//        // Faz o cálculo do total de páginas
//        totalPaginas = (int) Math.ceil((double) totalUsuarios / limit);
//
//        request.setAttribute("usuarios", usuarios);
//        request.setAttribute("page", page);
//        request.setAttribute("totalPaginas", totalPaginas);
//        request.setAttribute("totalUsuarios", totalUsuarios);
//
//        RequestDispatcher rd = request.getRequestDispatcher("pages/usuarios.jsp");
//        rd.forward(request, response);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
        int page = 1;
        int limit = 4;
        int totalUsuarios;
        int totalPaginas;
        String parametroPagina = request.getParameter("page");

            if (path.equals("/usuarios")) {
                if (parametroPagina != null) {
                    try {
                        page = Integer.parseInt(parametroPagina);
                    } catch (NumberFormatException e) {
                        page = 1;
                    }
            }

// Busca lista de usuários
            List<Usuario> usuarios = dao.findAll(page);

// Conta todos os usuários
            totalUsuarios = dao.countAll();

// Faz o cálculo do total de páginas
            totalPaginas = (int) Math.ceil((double) totalUsuarios / limit);

            request.setAttribute("usuarios", usuarios);
            request.setAttribute("page", page);
            request.setAttribute("totalPaginas", totalPaginas);
            request.setAttribute("totalUsuarios", totalUsuarios);

//            response.sendRedirect(request.getContextPath() + "/pages/usuarios.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/pages/usuarios.jsp");
            rd.forward(request, response);

//            if (path.equals("http://localhost:8080/nutria_war_exploded/")) {
//                response.sendRedirect("pages/usuarios.jsp");
//            }
//            RequestDispatcher rd = request.getRequestDispatcher("usuarios.jsp");
//            rd.forward(request, response);
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        int contaUsuario = service.findAll().size();
        if (path.equals("/usuarios/excluir")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (!(id > 0)) {
                boolean deleted = service.deleteUserById(id);
                if (deleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(String.format("Usuário com ID: %s deletado com sucesso!", id));
                    contaUsuario--;
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(String.format("Usuário com ID: %s não existe ou não pode ser deletado!", id));
                }
            }
        }
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (path.equals("/usuarios/atualizar")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String telefone = request.getParameter("telefone");
            String empresa = request.getParameter("empresa");
            String foto = request.getParameter("foto");

//            Recebemos o retorno do método update
            if (id > 0) {
                boolean updated = service.update(id, nome, email, senha, telefone, empresa, foto);
                if (updated) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(String.format("Usuário com ID: %s atualizado com sucesso!", id));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(String.format("Usuário com ID: %s não foi encontrado.", id));
                }
            }
        }
    }
}