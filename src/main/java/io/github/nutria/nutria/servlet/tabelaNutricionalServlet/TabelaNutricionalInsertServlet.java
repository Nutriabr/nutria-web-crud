package io.github.nutria.nutria.servlet.tabelaNutricionalServlet;

import io.github.nutria.nutria.dao.TabelaNutricionalDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.TabelaNutricional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tabela_nutricional/adicionar")
public class TabelaNutricionalInsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String viewPath = "/WEB-INF/views/tabela_nutricional/adicionar.jsp";
        req.getRequestDispatcher(viewPath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            TabelaNutricional tabelaNutricional = new TabelaNutricional();
            TabelaNutricionalDAO tabelaNutricionalDAO = new TabelaNutricionalDAO();

            tabelaNutricional.setIdIngrediente(Long.parseLong(req.getParameter("id-ingrediente")));
            tabelaNutricional.setValorEnergeticoKcal(Double.parseDouble(req.getParameter("valor-energetico")));
            tabelaNutricional.setCarboidratosG(Double.parseDouble(req.getParameter("carboidratos")));
            tabelaNutricional.setAcucaresTotaisG(Double.parseDouble(req.getParameter("acucares-totais")));
            tabelaNutricional.setAcucaresAdicionadosG(Double.parseDouble(req.getParameter("acucares-adicionados")));
            tabelaNutricional.setProteinasG(Double.parseDouble(req.getParameter("proteinas")));
            tabelaNutricional.setGordurasTotaisG(Double.parseDouble(req.getParameter("gorduras-totais")));
            tabelaNutricional.setGordurasSaturadasG(Double.parseDouble(req.getParameter("gorduras-saturadas")));
            tabelaNutricional.setGordurasTransG(Double.parseDouble(req.getParameter("gorduras-trans")));
            tabelaNutricional.setFibraAlimentarG(Double.parseDouble(req.getParameter("fibra-alimentar")));
            tabelaNutricional.setSodioMg(Double.parseDouble(req.getParameter("sodio")));

            tabelaNutricional.setColesterolMg(parseNullableDouble(req, "colesterol"));
            tabelaNutricional.setVitaminaAMcg(parseNullableDouble(req, "vitamina-a"));
            tabelaNutricional.setVitaminaCMg(parseNullableDouble(req, "vitamina-c"));
            tabelaNutricional.setVitaminaDMcg(parseNullableDouble(req, "vitamina-d"));
            tabelaNutricional.setCalcioMg(parseNullableDouble(req, "calcio"));
            tabelaNutricional.setFerroMg(parseNullableDouble(req, "ferro"));
            tabelaNutricional.setPotassioMg(parseNullableDouble(req, "potassio"));

            tabelaNutricionalDAO.inserir(tabelaNutricional);

            resp.sendRedirect(req.getContextPath() + "/tabela_nutricional/listar");
        } catch (DataAccessException dae) {
            req.setAttribute("errorMessage", "Ops! " + dae.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/tabela_nutricional/adicionar.jsp").forward(req, resp);
        }
    }

    private Double parseNullableDouble(HttpServletRequest req, String paramName) {
        String value = req.getParameter(paramName);
        if (value == null || value.isBlank()) {
            return null;
        }
        else return Double.parseDouble(value);
    }
}
