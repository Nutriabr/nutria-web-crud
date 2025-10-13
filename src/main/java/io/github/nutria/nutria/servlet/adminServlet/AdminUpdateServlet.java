package io.github.nutria.nutria.servlet.adminServlet;

import io.github.nutria.nutria.dao.AdminDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.Admin;
import io.github.nutria.nutria.util.PasswordHasher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/editar")
public class AdminUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO adminDAO = new AdminDAO();
        String idStr = req.getParameter("id");

        try {
            if (idStr == null || idStr.isEmpty()) {
                throw new InvalidNumberException("ID", idStr);
            }
            Long id = Long.parseLong(idStr);

            Admin admin = adminDAO.findById(id);

            req.setAttribute("id", admin.getId());
            req.setAttribute("nome", admin.getNome());
            req.setAttribute("email", admin.getEmail());
            req.setAttribute("telefone", admin.getTelefone());
            req.setAttribute("nascimento", admin.getNascimento());
            req.setAttribute("cargo", admin.getCargo());
            req.setAttribute("foto", admin.getFoto());

            req.getRequestDispatcher("/WEB-INF/views/admin/editar.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/admin/editar.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/admin/editar.jsp").forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO adminDAO = new AdminDAO();
        String viewPath = "/WEB-INF/views/admin/editar.jsp";

        try {
            Long id = Long.valueOf(req.getParameter("id"));
            Admin admin = adminDAO.findById(id);

            admin.setNome(req.getParameter("name"));
            admin.setEmail(req.getParameter("email"));
            admin.setTelefone(req.getParameter("phone").replaceAll("\\s", ""));
            admin.setCargo(req.getParameter("role"));
            admin.setFoto(req.getParameter("picture"));

            String birthDateStr = req.getParameter("birth");
            if (birthDateStr != null && !birthDateStr.isEmpty()) {
                admin.setNascimento(Date.valueOf(birthDateStr));
            }

            String newPassword = req.getParameter("password");
            if (newPassword != null && !newPassword.isEmpty()) {
                String newHash = PasswordHasher.hashPassword(newPassword);
                admin.setSenha(newHash);
            }

            adminDAO.update(admin);

            resp.sendRedirect(req.getContextPath() + "/admin/listar");

        } catch (DuplicateEmailException | DuplicatePhoneException e) {
            req.setAttribute("errorMessage", "Ops! " + e.getMessage());
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (EntityNotFoundException e) {
            req.setAttribute("errorMessage", "Administrador não encontrado para atualização.");
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ocorreu um erro inesperado: " + e.getMessage());
            req.getRequestDispatcher(viewPath).forward(req, resp);
        }
    }
}