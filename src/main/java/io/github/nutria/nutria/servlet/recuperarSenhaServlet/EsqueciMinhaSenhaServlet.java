package io.github.nutria.nutria.servlet.recuperarSenhaServlet;

import io.github.nutria.nutria.exceptions.EntityNotFoundException;
import io.github.nutria.nutria.util.EmailUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@WebServlet("/recuperar-senha")
public class EsqueciMinhaSenhaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String viewPath = "/WEB-INF/views/recuperarSenha/esqueceuSenha.jsp";
        req.getRequestDispatcher(viewPath).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        String codigoVerificacao = String.valueOf(codigo);

        String email = req.getParameter("email");

        HttpSession session = req.getSession();
        session.setAttribute("codigoVerificacao", codigoVerificacao);
        session.setAttribute("emailParaRecuperacao", email);
        session.setAttribute("codigoExpiracao", System.currentTimeMillis());

        try {
            EmailUtil.enviarEmail(email, codigoVerificacao);

            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/codigoVerificacao.jsp").forward(req, resp);
        } catch (MessagingException | UnsupportedEncodingException exception) {
            req.setAttribute("errorMessage", "Erro ao enviar o email. Tente novamente mais tarde!");
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/esqueceuSenha.jsp").forward(req, resp);
        } catch (EntityNotFoundException enfe) {
            req.setAttribute("errorMessage", enfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/esqueceuSenha.jsp").forward(req, resp);
        }
    }
}
