package io.github.nutria.nutria.servlet.adminServlet;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.util.PasswordHasher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if (email == null || email.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", "Email e senha são obrigatórios");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        Optional<Admin> admin = adminDAO.buscarPorEmail(email);

        if (admin.isPresent() && PasswordHasher.verificarSenha(senha, admin.get().getSenha())) {
            HttpSession session = req.getSession();
            session.setAttribute("adminLoggedIn", admin);
            session.setAttribute("adminId", admin.get().getId());
            session.setAttribute("adminNome", admin.get().getNome());
            session.setAttribute("adminEmail", admin.get().getEmail());

            resp.sendRedirect( req.getContextPath()+"/home");
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            req.setAttribute("error", "Email ou senha inválidos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
