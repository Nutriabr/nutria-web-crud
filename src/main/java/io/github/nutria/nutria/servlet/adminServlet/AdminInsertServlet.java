package io.github.nutria.nutria.servlet.adminServlet;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/adicionar")
public class AdminInsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String viewPath = "/WEB-INF/views/admin/adicionar.jsp";
        req.getRequestDispatcher(viewPath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();


        try {
            String dataNascimentoStr = req.getParameter("nascimento");
            Date dataNascimento = null;
            if (dataNascimentoStr != null && !dataNascimentoStr.isEmpty()) {
                dataNascimento = Date.valueOf(dataNascimentoStr);
            }

            String telefoneStr = req.getParameter("telefone");
            String telefone = telefoneStr.replaceAll("\\s", "");

            admin.setNascimento(dataNascimento);
            admin.setTelefone(telefone);

            admin.setNome(req.getParameter("nome"));
            admin.setEmail(req.getParameter("email"));
            admin.setSenha(req.getParameter("senha"));
            admin.setCargo(req.getParameter("cargo"));

            String foto = req.getParameter("foto");
            if (foto.isEmpty()) {
                admin.setFoto("Sem foto");
            } else {
                admin.setFoto(foto);
            }

            adminDAO.inserir(admin);

            req.getSession().setAttribute("successMessage", "Administrador adicionado com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/admin/listar");

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
        } catch (IllegalArgumentException iae) {
            System.err.println("Formato de data inválido: " + req.getParameter("birth"));
            req.setAttribute("errorMessage", "O formato da data de nascimento é inválido. Use YYYY-MM-DD.");
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (DataAccessException dae) {
            System.err.println("[ERRO INTERNO]: " + dae);
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp");
        }
    }
}
