package io.github.nutria.nutria.servlet.recuperarSenhaServlet;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.EntityNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/redefinir-senha")
public class RedefinirSenhaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Boolean ehSenhaVerificada = (Boolean) session.getAttribute("ehSenhaRedefinidaVerificada");
        String email = (String) session.getAttribute("emailParaRecuperacao");

        if (ehSenhaVerificada == null || !ehSenhaVerificada || email == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String novaSenha = req.getParameter("password");
        String confirmarSenha = req.getParameter("passwordConfirm");

        if (novaSenha == null || novaSenha.isBlank() || !novaSenha.equals(confirmarSenha)) {
            req.setAttribute("errorMessage", "As senhas não coincidem ou estão vazias.");
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/redefinirSenha.jsp").forward(req, resp);
            return;
        }

        if (novaSenha.length() < 8) {
            req.setAttribute("errorMessage", "A senha deve ter pelo menos 8 caracteres.");
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/redefinirSenha.jsp").forward(req, resp);
            return;
        }

        try {
            AdminDAO adminDAO = new AdminDAO();

            adminDAO.alterarSenhaPeloEmail(email, novaSenha);

            session.invalidate();
            req.getSession().setAttribute("successMessage", "Senha redefinida com sucesso! Faça login.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (EntityNotFoundException enfe) {
            req.setAttribute("errorMessage", enfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/redefinirSenha.jsp").forward(req, resp);
        } catch (DataAccessException dae) {
            System.err.println("[ERRO INTERNO]: " + dae);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Erro ao redefinir a senha. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/redefinirSenha.jsp").forward(req, resp);
        }
    }
}
