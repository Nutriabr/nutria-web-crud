package io.github.nutria.nutria.service;

import io.github.nutria.nutria.dao.TabelaNutricionalDAO;
import io.github.nutria.nutria.model.TabelaNutricional;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;
import java.util.List;

public class TabelaNutricionalService {
    TabelaNutricionalDAO dao = new TabelaNutricionalDAO();

    public void save(HttpServletRequest request) {
        try {
            // Inicializando os atributos da tabela
//            String id_tabela_nutricional_str = request.getParameter("id");
            String valor_energetico_kcal_str = request.getParameter("valor_energetico");
            String carboidratos_g_str = request.getParameter("carboidratos_g");
            String acucares_totais_g_str = request.getParameter("acucares_totais");
            String acucares_adicionados_g_str = request.getParameter("acucares_adicionados");
            String proteinas_g_str = request.getParameter("proteinas_g");
            String gorduras_totais_g_str = request.getParameter("gorduras_totais");
            String gorduras_saturadas_g_str = request.getParameter("gorduras_saturadas");
            String gorduras_trans_g_str = request.getParameter("gorduras_trans");
            String fibra_alimentar_g_str = request.getParameter("fibra_alimentar");

            String sodio_mg_str = request.getParameter("sodio_mg");
            String colesterol_mg_str = request.getParameter("colesterol_mg");
            String vitamina_a_mcg_str = request.getParameter("vitamina_a_mcg");
            String vitamina_c_mg_str = request.getParameter("vitamina_c_mg");
            String vitamina_d_mcg_str = request.getParameter("vitamina_d_mcg");
            String calcio_mg_str = request.getParameter("calcio_mg");
            String ferro_mg_str = request.getParameter("ferro_mg");
            String potassio_mg_str = request.getParameter("potassio_mg");

            // Validando se os campos obrigatórios estão preenchidos
//            long id_tabela_nutricional = id_tabela_nutricional_str != null && !id_tabela_nutricional_str.isBlank() ? Long.parseLong(id_tabela_nutricional_str) : 0;
            double valor_energetico_kcal = valor_energetico_kcal_str != null && !valor_energetico_kcal_str.isBlank() ? Double.parseDouble(valor_energetico_kcal_str) : 0;
            double carboidratos_g = carboidratos_g_str != null && !carboidratos_g_str.isBlank() ? Double.parseDouble(carboidratos_g_str) : 0;
            double acucares_totais_g = acucares_totais_g_str != null && !acucares_totais_g_str.isBlank() ? Double.parseDouble(acucares_totais_g_str) : 0;
            double acucares_adicionados_g = acucares_adicionados_g_str != null && !acucares_adicionados_g_str.isBlank() ? Double.parseDouble(acucares_adicionados_g_str) : 0;
            double proteinas_g = proteinas_g_str != null && !proteinas_g_str.isBlank() ? Double.parseDouble(proteinas_g_str) : 0;
            double gorduras_totais_g = gorduras_totais_g_str != null && !gorduras_totais_g_str.isBlank() ? Double.parseDouble(gorduras_totais_g_str) : 0;
            double gorduras_saturadas_g = gorduras_saturadas_g_str != null && !gorduras_saturadas_g_str.isBlank() ? Double.parseDouble(gorduras_saturadas_g_str) : 0;
            double gorduras_trans_g = gorduras_trans_g_str != null && !gorduras_trans_g_str.isBlank() ? Double.parseDouble(gorduras_trans_g_str) : 0;
            double fibra_alimentar_g = fibra_alimentar_g_str != null && !fibra_alimentar_g_str.isBlank() ? Double.parseDouble(fibra_alimentar_g_str) : 0;
            double sodio_mg = sodio_mg_str != null && !sodio_mg_str.isBlank() ? Double.parseDouble(sodio_mg_str) : 0;
            double colesterol_mg = colesterol_mg_str != null && !colesterol_mg_str.isBlank() ? Double.parseDouble(colesterol_mg_str) : 0;
            double vitamina_a_mcg = vitamina_a_mcg_str != null && !vitamina_a_mcg_str.isBlank() ? Double.parseDouble(vitamina_a_mcg_str) : 0;
            double vitamina_c_mg = vitamina_c_mg_str != null && !vitamina_c_mg_str.isBlank() ? Double.parseDouble(vitamina_c_mg_str) : 0;
            double vitamina_d_mcg = vitamina_d_mcg_str != null && !vitamina_d_mcg_str.isBlank() ? Double.parseDouble(vitamina_d_mcg_str) : 0;
            double calcio_mg = calcio_mg_str != null && !calcio_mg_str.isBlank() ? Double.parseDouble(calcio_mg_str) : 0;
            double ferro_mg = ferro_mg_str != null && !ferro_mg_str.isBlank() ? Double.parseDouble(ferro_mg_str) : 0;
            double potassio_mg = potassio_mg_str != null && !potassio_mg_str.isBlank() ? Double.parseDouble(potassio_mg_str) : 0;


            TabelaNutricional tabela =
                    new TabelaNutricional(valor_energetico_kcal, carboidratos_g, acucares_totais_g,
                            acucares_adicionados_g, proteinas_g, gorduras_totais_g, gorduras_saturadas_g,
                            gorduras_trans_g, fibra_alimentar_g, sodio_mg, colesterol_mg, vitamina_a_mcg,
                            vitamina_c_mg, vitamina_d_mcg, calcio_mg, ferro_mg, potassio_mg);
            // Inserindo o usuário no banco de dados
            dao.insert(tabela);

        } catch (NumberFormatException npe) {
            npe.printStackTrace();
        }
    }

    public List<TabelaNutricional> findAll() {
        return dao.findAll();
    }
}
