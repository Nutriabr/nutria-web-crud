package io.github.nutria.nutria.servlet.receitaIngredienteServlet;

import io.github.nutria.nutria.dao.ReceitaIngredienteDAO;
import io.github.nutria.nutria.exceptions.*;
import io.github.nutria.nutria.model.ReceitaIngrediente;
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

            receitaIngrediente = receitaIngredienteDAO.buscarPorId(id);

            Double quantidade = receitaIngrediente.getQuantidade();

            req.setAttribute("id", receitaIngrediente.getId());
            req.setAttribute("quantidade", quantidade);

            req.getSession().setAttribute("successMessage", "Receita Ingrediente atualizada com sucesso!");
            req.getRequestDispatcher("/WEB-INF/views/receitaIngrediente/editar.jsp").forward(req, resp);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/editar.jsp").forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            System.out.println(e.getMessage());
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/editar.jsp").forward(req, resp);
        } catch (DataAccessException dae) {
            System.err.println("[ERRO INTERNO]: " + dae);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReceitaIngredienteDAO receitaIngredienteDAO = new ReceitaIngredienteDAO();

        int lastPage = (int) Math.ceil((double) receitaIngredienteDAO.contarTodos() / 4);


        try {
            String idStr = req.getParameter("id");
            Long id = Long.parseLong(String.valueOf(idStr));

            ReceitaIngrediente receitaIngrediente = receitaIngredienteDAO.buscarPorId(id);

            receitaIngrediente.setQuantidade(Double.parseDouble(req.getParameter("quantity")));

            receitaIngredienteDAO.alterar(receitaIngrediente);

            resp.sendRedirect(req.getContextPath() + "/receitaIngrediente/listar?page=" + (lastPage + 1));
        } catch (RequiredFieldException rfe) {
            System.out.println(rfe.getMessage());
            System.err.println("[ERRO DE CAMPO OBRIGATÓRIO]: " + rfe);
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/editar.jsp").forward(req, resp);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            System.err.println("[FORMATO DE CAMPO INVÁLIDO");
            req.setAttribute("errorMessage", "Ops! " + nfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/receitasIngredientes/editar.jsp").forward(req, resp);
        } catch (DataAccessException dae) {
            System.out.println(dae.getMessage());
            throw new DataAccessException("Erro ao acessar o banco de dados", dae);
        }
    }
}
