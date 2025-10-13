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

@WebServlet(name = "UsuarioMostraFormEdicaoServlet", value = "/usuario/editar")
public class UsuarioSalvarUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();


        try {
            usuario.setId(Long.parseLong(req.getParameter("id")));
            usuario.setNome(req.getParameter("nome"));
            usuario.setEmail(req.getParameter("email"));
            usuario.setSenha(req.getParameter("password"));
            usuario.setTelefone(req.getParameter("phone"));
            usuario.setEmpresa(req.getParameter("role"));
            usuario.setFoto(req.getParameter("picture"));

            if (usuarioDAO.update(usuario)) {
                resp.sendRedirect(req.getContextPath() + "/usuario/listar");
            }
        } catch (DuplicateEmailException dee) {
            System.err.println("[ERRO DE DUPLICIDADE]: " + dee);
            req.setAttribute("errorMessage", "Ops! Esse e-mail já está em uso!");
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (DuplicatePhoneException dpe) {
            System.err.println("[ERRO DE DUPLICIDADE]: " + dpe);
            req.setAttribute("errorMessage", "Ops! Esse telefone já está em uso!");
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (RequiredFieldException rfe) {
            System.err.println("[ERRO DE CAMPO OBRIGATÓRIO]: " + rfe);
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (InvalidEmailException iee) {
            System.err.println("[ERRO DE EMAIL INVÁLIDO]: " + iee);
            req.setAttribute("errorMessage", "Ops! " + iee.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (InvalidPhoneException ipe) {
            System.err.println("[ERRO DE TELEFONE INVÁLIDO]: " + ipe);
            req.setAttribute("errorMessage", "Ops! " + ipe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (InvalidPasswordException ipwe) {
            System.err.println("[ERRO DE SENHA INVÁLIDO]: " + ipwe);
            req.setAttribute("errorMessage", "Ops! " + ipwe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (DataAccessException dae) {
            throw new DataAccessException("Erro ao acessar o banco de dados", dae);
        }
    }
}
