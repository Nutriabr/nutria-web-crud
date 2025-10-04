package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.model.TabelaNutricional;
import io.github.nutria.nutria.util.ConnectionFactory;

// Importações necessárias para operações com JDBC e manipulação de listas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabelaNutricionalDAO implements GenericDAO<TabelaNutricional, Long> {

    public TabelaNutricionalDAO() {
    }

    public boolean insert(TabelaNutricional tabelaNutricional) {
        String sql = "INSERT INTO tabela_nutricional (valor_energetico_kcal, carboidratos_g, acucares_totais_g, acucares_adicionados_g, proteinas_g, gorduras_totais_g, gorduras_saturadas_g, fibra_alimentar_g, sodio_mg, colesterol_mg, vitamina_a_mcg, vitamina_c_mg, vitamina_d_mcg, calcio_mg, ferro_mg, potassio_mg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = null;
        Connection connect = null;
        boolean result;
        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

//            ps.setLong(1, tabelaNutricional.getIdIngrediente());
            ps.setDouble(1, tabelaNutricional.getValorEnergeticoKcal());
            ps.setDouble(2, tabelaNutricional.getCarboidratosG());
            ps.setDouble(3, tabelaNutricional.getAcucaresTotaisG());
            ps.setDouble(4, tabelaNutricional.getAcucaresAdicionadosG());
            ps.setDouble(5, tabelaNutricional.getProteinasG());
            ps.setDouble(6, tabelaNutricional.getGordurasTotaisG());
            ps.setDouble(7, tabelaNutricional.getGordurasSaturadasG());
            ps.setDouble(8, tabelaNutricional.getFibraAlimentarG());
            ps.setDouble(9, tabelaNutricional.getSodioMg());
            ps.setDouble(10, tabelaNutricional.getColesterolMg());
            ps.setDouble(11, tabelaNutricional.getVitaminaAMcg());
            ps.setDouble(12, tabelaNutricional.getVitaminaCMg());
            ps.setDouble(13, tabelaNutricional.getVitaminaDMcg());
            ps.setDouble(14, tabelaNutricional.getCalcioMg());
            ps.setDouble(15, tabelaNutricional.getFerroMg());
            ps.setDouble(16, tabelaNutricional.getPotassioMg());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<TabelaNutricional> findAll(int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM tabela_nutricional LIMIT ? OFFSET ?";

        List<TabelaNutricional> tabelaNutricionalArrayList = new ArrayList<TabelaNutricional>();

        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            ps.setInt(1, limite);
            ps.setInt(2, offset);


            rs = ps.executeQuery();

            while (rs.next()) {
                TabelaNutricional tabelaNutricional = new TabelaNutricional(
                        rs.getLong("id_ingrediente"),
                        rs.getDouble("valor_energetivo_kcal"),
                        rs.getDouble("carboidratos_g"),
                        rs.getDouble("acucares_totais_g"),
                        rs.getDouble("acucares_adicionados_g"),
                        rs.getDouble("proteinas_g"),
                        rs.getDouble("gorduras_totais_g"),
                        rs.getDouble("gorduras_saturadas_g"),
                        rs.getDouble("fibra_alimentar_g"),
                        rs.getDouble("sodio_mg"),
                        rs.getDouble("colesterol_mg"),
                        rs.getDouble("vitamina_a_mcg"),
                        rs.getDouble("vitamina_c_mg"),
                        rs.getDouble("vitamina_d_mc g"),
                        rs.getDouble("calcio_mg"),
                        rs.getDouble("ferro_mg"),
                        rs.getDouble("potassio_mg")
                );

                tabelaNutricionalArrayList.add(tabelaNutricional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();

                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tabelaNutricionalArrayList;
    }

    public int countAll() {
        int totalTabelas = 0;

        String sql = "SELECT COUNT(*) FROM tabela_nutricional";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            pstmt = connect.prepareStatement(sql);

            rs = pstmt.executeQuery();


            while (rs.next()) {
                totalTabelas++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (rs != null) rs.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalTabelas;
    }

    public boolean update(Long idIngrediente, double valorEnergeticoKcal, double carboidratosG, double acucaresTotaisG, double acucaresAdicionadosG, double proteinasG, double gordurasTotaisG, double gordurasSaturadasG, double fibraAlimentarG, double sodioMg, double colesterolMg, double vitaminaAMcg, double vitaminaCMg, double vitaminaDMcg, double calcioMg, double ferroMg, double potassioMg) {
        String sql = "UPDATE usuario SET idIngrediente = ?, valorEnergeticoKcal = ?, carboidratosG = ?, acucaresTotaisG = ?, acucaresAdicionadosG = ?, proteinasG = ?, gordurasTotaisG = ?, gordurasSaturadasG = ?, fibraAlimentarG = ?, sodioMg = ?, colesterolMg = ?, vitaminaAMcg = ?, vitaminaCMg = ?, vitaminaDMcg = ?, calcioMg = ?, ferroMg = ?, potassioMg = ? WHERE id = ?";

        int result = 0;

        PreparedStatement pstmt = null;
        Connection connect = null;
        try {
            connect = ConnectionFactory.connect();
            pstmt = connect.prepareStatement(sql);

            pstmt.setDouble(1, idIngrediente);
            pstmt.setDouble(2, valorEnergeticoKcal);
            pstmt.setDouble(3, carboidratosG);
            pstmt.setDouble(4, acucaresTotaisG);
            pstmt.setDouble(5, acucaresAdicionadosG);
            pstmt.setDouble(6, proteinasG);
            pstmt.setDouble(7, gordurasTotaisG);
            pstmt.setDouble(8, gordurasSaturadasG);
            pstmt.setDouble(9, fibraAlimentarG);
            pstmt.setDouble(10, sodioMg);
            pstmt.setDouble(11, colesterolMg);
            pstmt.setDouble(12, vitaminaAMcg);
            pstmt.setDouble(13, vitaminaCMg);
            pstmt.setDouble(14, vitaminaDMcg);
            pstmt.setDouble(15, calcioMg);
            pstmt.setDouble(16, ferroMg);
            pstmt.setDouble(17, potassioMg);


            result = pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (result > 0);
    }


    @Override
    public boolean deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM tabela_nutricional WHERE id = ?";

        boolean result = false;

        PreparedStatement ps = null;
        Connection connect = ConnectionFactory.connect();
        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            result = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connect != null) ConnectionFactory.disconnect(connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
