package io.github.nutria.nutria.servlet.receitaIngredienteServlet;

import io.github.nutria.nutria.dao.ReceitaIngredienteDAO;
import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.ReceitaIngrediente;
import io.github.nutria.nutria.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/receitasIngredientes/editar")
public class ReceitaIngredienteUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();
        ReceitaIngrediente receitaIngrediente = new ReceitaIngrediente();

        try {
            String idStr = req.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
                throw new InvalidNumberException("ID", idStr);
            }
            Long id = Long.parseLong(idStr);

            req.setAttribute("id", id);
            req.setAttribute("idReceita", receitaIngrediente.getIdReceita());
            req.setAttribute("idIngrediente", receitaIngrediente.getIdIngrediente());
            req.setAttribute("quantidade", receitaIngrediente.getQuantidade());

            req.getRequestDispatcher("/WEB-INF/views/usuario/editar.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/usuario/editar.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/usuario/editar.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();
        final String viewPath = "/WEB-INF/views/receitasIngredientes/receitasIngrediente.jsp";


        try {
            System.out.println(req.getParameter("id"));
            Long id = Long.valueOf(req.getParameter("id"));
            ReceitaIngrediente receitaIngrediente = receitaIngredienteDAO.findById(id);

            receitaIngrediente.setId(Long.parseLong(req.getParameter("id")));
            receitaIngrediente.setIdReceita(Long.parseLong(req.getParameter("idReceita")));
            receitaIngrediente.setIdIngrediente(Long.parseLong(req.getParameter("idIngrediente")));
            receitaIngrediente.setQuantidade(Double.parseDouble(req.getParameter("quantidade")));

            if (receitaIngredienteDAO.update(receitaIngrediente)) {
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
