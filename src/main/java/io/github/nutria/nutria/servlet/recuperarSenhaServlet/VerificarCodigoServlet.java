package io.github.nutria.nutria.servlet.recuperarSenhaServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/verificar-codigo")
public class VerificarCodigoServlet extends HttpServlet {
    private static final long CODIGO_TEMPO_EXPIRACAO = 600000; // 10 Minutos

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigoEnviado = req.getParameter("codigo");
        HttpSession session = req.getSession();

        String codigoSalvo = (String) session.getAttribute("codigoVerificacao");
        Long timestampSalvo = (Long) session.getAttribute("codigoExpiracao");

        if (codigoSalvo == null || timestampSalvo == null) {
            req.setAttribute("errorMessage", "Sessão inválida ou expirada. Solicite novamente.");
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/esqueceuSenha.jsp").forward(req, resp);
            return;
        }

        if (codigoEnviado == null || !codigoEnviado.trim().equals(codigoSalvo)) {
            req.setAttribute("errorMessage", "Código incorreto. Tente novamente.");
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/codigoVerificacao.jsp").forward(req, resp);
            return;
        }

        long tempoAtual = System.currentTimeMillis();
        if ((tempoAtual - timestampSalvo) > CODIGO_TEMPO_EXPIRACAO) {
            req.setAttribute("errorMessage", "O código expirou. Solicite um novo.");

            session.invalidate();
            req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/esqueceuSenha.jsp").forward(req, resp);
            return;
        }

        session.setAttribute("ehSenhaRedefinidaVerificada", true);
        session.removeAttribute("codigoVerificacao");
        session.removeAttribute("codigoExpiracao");

        req.getRequestDispatcher("/WEB-INF/views/recuperarSenha/cadastroSenha.jsp").forward(req, resp);
    }
}
