package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.model.TabelaNutricional;
import io.github.nutria.nutria.util.ConnectionFactory;

// Importações necessárias para operações com JDBC e manipulação de listas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabelaNutricionalDAO implements GenericDAO<TabelaNutricional, Long>, AutoCloseable {
//    ConnectionFactory factory = new ConnectionFactory();
    private final static Connection connect = ConnectionFactory.connect();

    public TabelaNutricionalDAO() {}

    // Cadastrar usuario
    public boolean insert(TabelaNutricional tabelaNutricional) {
        // Preparando a query de inserção no banco de dados
        String sql = "INSERT INTO tabela_nutricional (valor_energetico_kcal, carboidratos_g, acucares_totais_g, acucares_adicionados_g, proteinas_g, gorduras_totais_g, gorduras_saturadas_g, fibra_alimentar_g, sodio_mg, colesterol_mg, vitamina_a_mcg, vitamina_c_mg, vitamina_d_mcg, calcio_mg, ferro_mg, potassio_mg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {

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

            // Executando a query de inserção
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TabelaNutricional> findAll() {
        String sql = "SELECT * FROM tabela_nutricional";

        List<TabelaNutricional> tabelaNutricionalArrayList = new ArrayList<TabelaNutricional>();

        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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
        }
        return tabelaNutricionalArrayList;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM tabela_nutricional WHERE id = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        try {
            if (connect == null && !connect.isClosed()) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
