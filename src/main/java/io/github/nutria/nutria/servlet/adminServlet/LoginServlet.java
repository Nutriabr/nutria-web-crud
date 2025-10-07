package io.github.nutria.nutria.servlet.adminServlet;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.util.PasswordVerifier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("error", "Email e senha são obrigatórios");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        Admin admin = adminDAO.findByEmail(email);

        if (admin != null && PasswordVerifier.checkPassword(password, admin.getSenha())) {
            HttpSession session = req.getSession();
            session.setAttribute("adminLoggedIn", admin);
            session.setAttribute("adminId", admin.getId());
            session.setAttribute("adminName", admin.getNome());
            System.out.println(admin.getNome());
            session.setAttribute("adminEmail", admin.getEmail());

            resp.sendRedirect( req.getContextPath()+"/pages/administracao.jsp");
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            req.setAttribute("error", "Email ou senha inválidos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
