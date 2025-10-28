package io.github.nutria.nutria.servlet.produtoServlet;

import io.github.nutria.nutria.dao.ProdutoDAO;
import io.github.nutria.nutria.dao.UsuarioDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.RequiredFieldException;
import io.github.nutria.nutria.exceptions.ValidationException;
import io.github.nutria.nutria.model.Produto;
import io.github.nutria.nutria.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/produto/adicionar")
public class ProdutoInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/produto/adicionar.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String emailUsuario = req.getParameter("emailUsuario");
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Optional<Usuario> optionalUsuario = usuarioDAO.buscarPorEmail(emailUsuario);
        Usuario usuario = new Usuario();
        Long idUsuario;
        if (optionalUsuario.isPresent()) {
             usuario = optionalUsuario.get();
        }
        idUsuario = usuario.getId();
        boolean success;
        Produto produto = new Produto(nome,idUsuario);

        try {
            success = produtoDAO.inserir(produto);
            if (!success) throw new DataAccessException("Erro ao inserir produto no banco de dados.");

            req.getSession().setAttribute("message", "Produto inserido com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/produto/listar");

        } catch (RequiredFieldException rfe){
            System.err.println("[ERRO DE CAMPOS OBRIGATÓRIOS AUSENTES]");
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/produto/adicionar.jsp").forward(req,resp);
        } catch (ValidationException ve){
            System.err.println("[ERRO DE VALIDAÇÃO]" + ve);
            req.setAttribute("errorMessage", "Ops! " + ve.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/produto/adicionar.jsp").forward(req,resp);
        }  catch (DataAccessException dae){
            System.err.println("[ERRO NO BANCO]");
            req.setAttribute("errorMessage","Ops! Não foi possível salvar o produto!");
            req.getRequestDispatcher("/WEB-INF/views/produto/adicionar.jsp").forward(req,resp);
        }
    }
}