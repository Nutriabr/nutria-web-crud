package io.github.nutria.nutria.servlet.tabelaNutricionalServlet;

import io.github.nutria.nutria.dao.TabelaNutricionalDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.exceptions.EntityNotFoundException;
import io.github.nutria.nutria.exceptions.InvalidNumberException;
import io.github.nutria.nutria.model.TabelaNutricional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/tabela_nutricional/editar")
public class TabelaNutricionalUpdateServlet extends HttpServlet {
    private final String viewPath = "/WEB-INF/views/tabela_nutricional/editar.jsp";
    private final TabelaNutricionalDAO tabelaNutricionalDAO = new TabelaNutricionalDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");

        try {
            if (idStr == null || idStr.isBlank()) {
                throw new InvalidNumberException("ID", idStr);
            }
            Long id = Long.parseLong(idStr);

            TabelaNutricional tabelaNutricional = tabelaNutricionalDAO.findById(id);

            req.setAttribute("id", tabelaNutricional.getIdIngrediente());
            req.setAttribute("valor-energetico", tabelaNutricional.getValorEnergeticoKcal());
            req.setAttribute("carboidratos", tabelaNutricional.getCarboidratosG());
            req.setAttribute("acucares-totais", tabelaNutricional.getAcucaresTotaisG());
            req.setAttribute("acucares-adicionados", tabelaNutricional.getAcucaresAdicionadosG());
            req.setAttribute("proteinas", tabelaNutricional.getProteinasG());
            req.setAttribute("gorduras-totais", tabelaNutricional.getGordurasTotaisG());
            req.setAttribute("gorduras_saturadas", tabelaNutricional.getGordurasSaturadasG());
            req.setAttribute("gorduras_trans", tabelaNutricional.getGordurasTransG());
            req.setAttribute("fibra-alimentar", tabelaNutricional.getFibraAlimentarG());
            req.setAttribute("sodio", tabelaNutricional.getSodioMg());
            req.setAttribute("colesterol", tabelaNutricional.getColesterolMg());
            req.setAttribute("vitamina-a", tabelaNutricional.getVitaminaAMcg());
            req.setAttribute("vitamina-c", tabelaNutricional.getVitaminaCMg());
            req.setAttribute("vitamina-d", tabelaNutricional.getVitaminaDMcg());
            req.setAttribute("calcio", tabelaNutricional.getCalcioMg());
            req.setAttribute("ferro", tabelaNutricional.getFerroMg());
            req.setAttribute("potassio", tabelaNutricional.getPotassioMg());

            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "O ID informado é inválido.");
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (InvalidNumberException | EntityNotFoundException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (DataAccessException e) {
            System.err.println("[ERRO INTERNO]: " + e);
            req.setAttribute("errorMessage", "Erro ao acessar os dados. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            TabelaNutricional tabelaNutricional = tabelaNutricionalDAO.findById(id);

            double valor_energetico = Double.parseDouble(req.getParameter("valor-energetico"));
            double carboidratos = Double.parseDouble(req.getParameter("carboidratos"));
            double acucares_totais = Double.parseDouble(req.getParameter("acucares-totais"));
            double acucares_adicionados = Double.parseDouble(req.getParameter("acucares-adicionados"));
            double proteinas = Double.parseDouble(req.getParameter("proteinas"));
            double gorduras_totais = Double.parseDouble(req.getParameter("gorduras-totais"));
            double gorduras_saturadas = Double.parseDouble(req.getParameter("gorduras-saturadas"));
            double gorduras_trans = Double.parseDouble(req.getParameter("gorduras-trans"));
            double fibra_alimentar = Double.parseDouble(req.getParameter("fibra-alimentar"));
            double sodio = Double.parseDouble(req.getParameter("sodio"));

            Double colesterol = parseNullableDouble(req, "colesterol");
            Double vitamina_a = parseNullableDouble(req, "vitamina-a");
            Double vitamina_c = parseNullableDouble(req, "vitamina-c");
            Double vitamina_d = parseNullableDouble(req, "vitamina-d");
            Double calcio = parseNullableDouble(req, "calcio");
            Double ferro = parseNullableDouble(req, "ferro");
            Double potassio = parseNullableDouble(req, "potassio");

            tabelaNutricional.setValorEnergeticoKcal(valor_energetico);
            tabelaNutricional.setCarboidratosG(carboidratos);
            tabelaNutricional.setAcucaresTotaisG(acucares_totais);
            tabelaNutricional.setAcucaresAdicionadosG(acucares_adicionados);
            tabelaNutricional.setProteinasG(proteinas);
            tabelaNutricional.setGordurasTotaisG(gorduras_totais);
            tabelaNutricional.setGordurasSaturadasG(gorduras_saturadas);
            tabelaNutricional.setGordurasTransG(gorduras_trans);
            tabelaNutricional.setFibraAlimentarG(fibra_alimentar);
            tabelaNutricional.setSodioMg(sodio);
            tabelaNutricional.setColesterolMg(colesterol);
            tabelaNutricional.setVitaminaAMcg(vitamina_a);
            tabelaNutricional.setVitaminaCMg(vitamina_c);
            tabelaNutricional.setVitaminaDMcg(vitamina_d);
            tabelaNutricional.setCalcioMg(calcio);
            tabelaNutricional.setFerroMg(ferro);
            tabelaNutricional.setPotassioMg(potassio);

            tabelaNutricionalDAO.alterar(tabelaNutricional);

            resp.sendRedirect(req.getContextPath() + "/tabela_nutricional/listar");
        } catch (EntityNotFoundException e) {
            req.setAttribute("errorMessage", "Tabela nutricional não encontrada para atualização.");
            req.getRequestDispatcher(viewPath).forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", "Ocorreu um erro inesperado: " + e.getMessage());
            req.getRequestDispatcher(viewPath).forward(req, resp);
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