package io.github.nutria.nutria.dao;

import io.github.nutria.nutria.dao.interfaces.GenericDAO;
import io.github.nutria.nutria.dao.interfaces.ITabelaNutricionalDAO;
import io.github.nutria.nutria.exceptions.DataAccessException;
import io.github.nutria.nutria.model.FiltroNutricional;
import io.github.nutria.nutria.model.TabelaNutricional;
import io.github.nutria.nutria.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TabelaNutricionalDAO implements GenericDAO<TabelaNutricional, Long>, ITabelaNutricionalDAO {

    /**
     * Constante contendo os filtros nutricionais pré-definidos.
     * <p>
     * Cada chave representa o nome de um filtro e o valor é um {@link FiltroNutricional}
     * com as suas condições de filtragem (coluna, operador e valores) para serem adicionadas na query SQL.
     * <p>
     * Este mapa é utilizado pelo método {@link #filterBy(String, int)} para aplicar filtros.
     * @author Giovanna Santos
     */
    public static final Map<String, FiltroNutricional> FILTROS = Map.of(
            "muito_baixo_sodio", new FiltroNutricional("sodio_mg", "<=", 40.00, null),
            "baixo_sodio", new FiltroNutricional("sodio_mg", "BETWEEN", 41.00, 80.00),
            "alto_sodio", new FiltroNutricional("sodio_mg", ">=", 600.00, null),
            "alto_acucares_adicionados", new FiltroNutricional("acucares_adicionados_g", ">=", 15.00, null),
            "alto_gorduras_saturadas", new FiltroNutricional("gorduras_saturadas_g", ">=", 6.00, null),
            "fonte_proteinas", new FiltroNutricional("proteinas_g", "BETWEEN", 5.00, 9.99),
            "rico_proteinas", new FiltroNutricional("proteinas_g", ">=", 10.00, null),
            "fonte_fibras", new FiltroNutricional("fibra_alimentar_g", "BETWEEN", 2.50, 4.99),
            "rico_fibras", new FiltroNutricional("fibra_alimentar_g", ">=", 5.00, null)
    );

    public TabelaNutricionalDAO() {
    }

    @Override
    public boolean insert(TabelaNutricional tabelaNutricional) {
        String sql = "INSERT INTO tabela_nutricional (id_ingrediente, valor_energetico_kcal, carboidratos_g, acucares_totais_g, " +
                "acucares_adicionados_g, proteinas_g, gorduras_totais_g, gorduras_saturadas_g, gorduras_trans_g, fibra_alimentar_g, " +
                "sodio_mg, colesterol_mg, vitamina_a_mcg, vitamina_c_mg, vitamina_d_mcg, calcio_mg, ferro_mg, potassio_mg) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = null;
        Connection connect = null;
        boolean result;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            ps.setLong(1, tabelaNutricional.getIdIngrediente());
            ps.setDouble(2, tabelaNutricional.getValorEnergeticoKcal());
            ps.setDouble(3, tabelaNutricional.getCarboidratosG());
            ps.setDouble(4, tabelaNutricional.getAcucaresTotaisG());
            ps.setDouble(5, tabelaNutricional.getAcucaresAdicionadosG());
            ps.setDouble(6, tabelaNutricional.getProteinasG());
            ps.setDouble(7, tabelaNutricional.getGordurasTotaisG());
            ps.setDouble(8, tabelaNutricional.getGordurasSaturadasG());
            ps.setDouble(9, tabelaNutricional.getGordurasTransG());
            ps.setDouble(10, tabelaNutricional.getFibraAlimentarG());
            ps.setDouble(11, tabelaNutricional.getSodioMg());

            if (tabelaNutricional.getColesterolMg() != null) ps.setDouble(12, tabelaNutricional.getColesterolMg());
            else ps.setNull(12, Types.DOUBLE);
            if (tabelaNutricional.getVitaminaAMcg() != null) ps.setDouble(13, tabelaNutricional.getVitaminaAMcg());
            else ps.setNull(13, Types.DOUBLE);
            if (tabelaNutricional.getVitaminaCMg() != null) ps.setDouble(14, tabelaNutricional.getVitaminaCMg());
            else ps.setNull(14, Types.DOUBLE);
            if (tabelaNutricional.getVitaminaDMcg() != null) ps.setDouble(15, tabelaNutricional.getVitaminaDMcg());
            else ps.setNull(15, Types.DOUBLE);
            if (tabelaNutricional.getCalcioMg() != null) ps.setDouble(16, tabelaNutricional.getCalcioMg());
            else ps.setNull(16, Types.DOUBLE);
            if (tabelaNutricional.getFerroMg()!= null) ps.setDouble(17, tabelaNutricional.getFerroMg());
            else ps.setNull(17, Types.DOUBLE);
            if (tabelaNutricional.getPotassioMg() != null) ps.setDouble(18, tabelaNutricional.getPotassioMg());
            else ps.setNull(18, Types.DOUBLE);

            result = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao salvar tabela nutricional");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao salvar tabela nutricional", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return result;
    }

    @Override
    public boolean update(TabelaNutricional tabelaNutricional) {
        String sql = "UPDATE tabela_nutricional SET valor_energetico_kcal = ?, carboidratos_g = ?, " +
                "acucares_totais_g = ?, acucares_adicionados_g = ?, proteinas_g = ?, gorduras_totais_g = ?, gorduras_saturadas_g = ?, " +
                "gorduras_trans_g = ?, fibra_alimentar_g = ?, sodio_mg = ?, colesterol_mg = ?, vitamina_a_mcg = ?, vitamina_c_mg = ?, vitamina_d_mcg = ?, " +
                "calcio_mg = ?, ferro_mg = ?, potassio_mg = ? WHERE id_ingrediente = ?";

        boolean result;
        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            ps.setDouble(1, tabelaNutricional.getValorEnergeticoKcal());
            ps.setDouble(2, tabelaNutricional.getCarboidratosG());
            ps.setDouble(3, tabelaNutricional.getAcucaresTotaisG());
            ps.setDouble(4, tabelaNutricional.getAcucaresAdicionadosG());
            ps.setDouble(5, tabelaNutricional.getProteinasG());
            ps.setDouble(6, tabelaNutricional.getGordurasTotaisG());
            ps.setDouble(7, tabelaNutricional.getGordurasSaturadasG());
            ps.setDouble(8, tabelaNutricional.getGordurasTransG());
            ps.setDouble(9, tabelaNutricional.getFibraAlimentarG());
            ps.setDouble(10, tabelaNutricional.getSodioMg());

            if (tabelaNutricional.getColesterolMg() != null) ps.setDouble(11, tabelaNutricional.getColesterolMg());
            else ps.setNull(11, Types.DOUBLE);
            if (tabelaNutricional.getVitaminaAMcg() != null) ps.setDouble(12, tabelaNutricional.getVitaminaAMcg());
            else ps.setNull(12, Types.DOUBLE);
            if (tabelaNutricional.getVitaminaCMg() != null) ps.setDouble(13, tabelaNutricional.getVitaminaCMg());
            else ps.setNull(13, Types.DOUBLE);
            if (tabelaNutricional.getVitaminaDMcg() != null) ps.setDouble(14, tabelaNutricional.getVitaminaDMcg());
            else ps.setNull(14, Types.DOUBLE);
            if (tabelaNutricional.getCalcioMg() != null) ps.setDouble(15, tabelaNutricional.getCalcioMg());
            else ps.setNull(15, Types.DOUBLE);
            if (tabelaNutricional.getFerroMg()!= null) ps.setDouble(16, tabelaNutricional.getFerroMg());
            else ps.setNull(16, Types.DOUBLE);
            if (tabelaNutricional.getPotassioMg() != null) ps.setDouble(17, tabelaNutricional.getPotassioMg());
            else ps.setNull(17, Types.DOUBLE);

            ps.setLong(18, tabelaNutricional.getIdIngrediente());

            result = (ps.executeUpdate() > 0);
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao atualizar a tabela nutricional: " + tabelaNutricional.getIdIngrediente());
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao atualizar a tabela nutricional: " + tabelaNutricional.getIdIngrediente(), e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return result;
    }

    @Override
    public boolean deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM tabela_nutricional WHERE id = ?";

        boolean result;
        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            ps.setLong(1, id);

            result = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao deletar a tabela nutricional: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao deletar a tabela nutricional com ID: " + id, e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return result;
    }

    public TabelaNutricional findById(Long id) {
        String sql = "SELECT * FROM tabela_nutricional WHERE id_ingrediente = ?";

        TabelaNutricional tabelaNutricional = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                tabelaNutricional = new TabelaNutricional(
                        rs.getLong("id_ingrediente"),
                        rs.getDouble("valor_energetico_kcal"),
                        rs.getDouble("carboidratos_g"),
                        rs.getDouble("acucares_totais_g"),
                        rs.getDouble("acucares_adicionados_g"),
                        rs.getDouble("proteinas_g"),
                        rs.getDouble("gorduras_totais_g"),
                        rs.getDouble("gorduras_saturadas_g"),
                        rs.getDouble("gorduras_trans_g"),
                        rs.getDouble("fibra_alimentar_g"),
                        rs.getDouble("sodio_mg"),
                        rs.getDouble("colesterol_mg"),
                        rs.getDouble("vitamina_a_mcg"),
                        rs.getDouble("vitamina_c_mg"),
                        rs.getDouble("vitamina_d_mcg"),
                        rs.getDouble("calcio_mg"),
                        rs.getDouble("ferro_mg"),
                        rs.getDouble("potassio_mg")
                );
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar a tabela nutricional com ID: " + id);
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao buscar tabela nutricional pelo seu ID: " + id, e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return tabelaNutricional;
    }

    @Override
    public List<TabelaNutricional> findAll(int page) {
        int limite = 4;
        int offset = (page - 1) * limite;

        String sql = "SELECT * FROM tabela_nutricional ORDER BY id_ingrediente LIMIT ? OFFSET ? ";

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
                        rs.getDouble("valor_energetico_kcal"),
                        rs.getDouble("carboidratos_g"),
                        rs.getDouble("acucares_totais_g"),
                        rs.getDouble("acucares_adicionados_g"),
                        rs.getDouble("proteinas_g"),
                        rs.getDouble("gorduras_totais_g"),
                        rs.getDouble("gorduras_saturadas_g"),
                        rs.getDouble("gorduras_trans_g"),
                        rs.getDouble("fibra_alimentar_g"),
                        rs.getDouble("sodio_mg"),
                        rs.getDouble("colesterol_mg"),
                        rs.getDouble("vitamina_a_mcg"),
                        rs.getDouble("vitamina_c_mg"),
                        rs.getDouble("vitamina_d_mcg"),
                        rs.getDouble("calcio_mg"),
                        rs.getDouble("ferro_mg"),
                        rs.getDouble("potassio_mg")
                );

                tabelaNutricionalArrayList.add(tabelaNutricional);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao buscar por todas as tabelas nutricionais");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a busca das tabelas nutricionais", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return tabelaNutricionalArrayList;
    }

    @Override
    public int countAll() {
        int totalTabelas = 0;

        String sql = "SELECT COUNT(*) FROM tabela_nutricional";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                totalTabelas = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao realizar a contagem total de tabelas nutricionais");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a contagem total de tabelas nutricionais", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return totalTabelas;
    }

    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM tabela_nutricional WHERE id_ingrediente = ?";

        boolean result = false;
        Connection connect = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            ps.setLong(1, id);
            
            rs = ps.executeQuery();

            if (rs.next()) {
                result = (rs.getInt(1) > 0);
            }
        }
        catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao verificar existência na tabela nutricional");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao verificar existência na tabela nutricional", e);
        }
        finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }
        
        return result;
    }

    @Override
    public List<TabelaNutricional> filterBy(String nomeFiltro, int page) {
        int limit = 4;
        int offset = (page - 1) * limit;

        FiltroNutricional filtro = FILTROS.get(nomeFiltro);

        String sql;
        if (filtro.getOperador().equals("BETWEEN")) {
            sql = "SELECT * FROM tabela_nutricional WHERE " + filtro.getColuna() +
                    " BETWEEN ? AND ? ORDER BY id_ingrediente LIMIT ? OFFSET ?";
        } else {
            sql = "SELECT * FROM tabela_nutricional WHERE " + filtro.getColuna() + " " +
                    filtro.getOperador() + "  ? ORDER BY id_ingrediente LIMIT ? OFFSET ?";
        }

        List<TabelaNutricional> tabelaNutricionalArrayList = new ArrayList<TabelaNutricional>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            if (filtro.getOperador().equals("BETWEEN")) {
                ps.setDouble(1, filtro.getValor1());
                ps.setDouble(2, filtro.getValor2());
                ps.setInt(3, limit);
                ps.setInt(4, offset);
            } else {
                ps.setDouble(1, filtro.getValor1());
                ps.setInt(2, limit);
                ps.setInt(3, offset);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                TabelaNutricional tabelaNutricional = new TabelaNutricional(
                        rs.getLong("id_ingrediente"),
                        rs.getDouble("valor_energetico_kcal"),
                        rs.getDouble("carboidratos_g"),
                        rs.getDouble("acucares_totais_g"),
                        rs.getDouble("acucares_adicionados_g"),
                        rs.getDouble("proteinas_g"),
                        rs.getDouble("gorduras_totais_g"),
                        rs.getDouble("gorduras_saturadas_g"),
                        rs.getDouble("gorduras_trans_g"),
                        rs.getDouble("fibra_alimentar_g"),
                        rs.getDouble("sodio_mg"),
                        rs.getDouble("colesterol_mg"),
                        rs.getDouble("vitamina_a_mcg"),
                        rs.getDouble("vitamina_c_mg"),
                        rs.getDouble("vitamina_d_mcg"),
                        rs.getDouble("calcio_mg"),
                        rs.getDouble("ferro_mg"),
                        rs.getDouble("potassio_mg")
                );

                tabelaNutricionalArrayList.add(tabelaNutricional);
            }
        } catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao realizar a filtragem da tabela nutricional");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a filtragem da tabela nutricional", e);
        } finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return tabelaNutricionalArrayList;
    }

    @Override
    public List<TabelaNutricional> findByNutrientRange(String type, String column, double minValue, double maxValue, int page) {
        int limit = 4;
        int offset = (page - 1) * limit;

        String sql = "SELECT * FROM tabela_nutricional WHERE " + column;

        if (type.equals("min")) {
            sql += " >= ? ORDER BY id_ingrediente LIMIT ? OFFSET ?";
        }
        else if (type.equals("max")){
            sql += " <= ? ORDER BY id_ingrediente LIMIT ? OFFSET ?";
        }
        else {
            sql += " BETWEEN ? AND ? ORDER BY id_ingrediente LIMIT ? OFFSET ?";
        }

        TabelaNutricional tabelaNutricional = null;
        List<TabelaNutricional> tabelaNutricionalArrayList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Connection connect = null;

        try {
            connect = ConnectionFactory.connect();
            ps = connect.prepareStatement(sql);

            if (type.equals("min")) {
                ps.setDouble(1, minValue);
                ps.setInt(2, limit);
                ps.setInt(3, offset);
            }
            else if (type.equals("max")) {
                ps.setDouble(1, maxValue);
                ps.setInt(2, limit);
                ps.setInt(3, offset);
            }
            else {
                ps.setDouble(1, minValue);
                ps.setDouble(2, maxValue);
                ps.setInt(3, limit);
                ps.setInt(4, offset);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                tabelaNutricional = new TabelaNutricional(
                        rs.getLong("id_ingrediente"),
                        rs.getDouble("valor_energetico_kcal"),
                        rs.getDouble("carboidratos_g"),
                        rs.getDouble("acucares_totais_g"),
                        rs.getDouble("acucares_adicionados_g"),
                        rs.getDouble("proteinas_g"),
                        rs.getDouble("gorduras_totais_g"),
                        rs.getDouble("gorduras_saturadas_g"),
                        rs.getDouble("gorduras_trans_g"),
                        rs.getDouble("fibra_alimentar_g"),
                        rs.getDouble("sodio_mg"),
                        rs.getDouble("colesterol_mg"),
                        rs.getDouble("vitamina_a_mcg"),
                        rs.getDouble("vitamina_c_mg"),
                        rs.getDouble("vitamina_d_mcg"),
                        rs.getDouble("calcio_mg"),
                        rs.getDouble("ferro_mg"),
                        rs.getDouble("potassio_mg")
                );

                tabelaNutricionalArrayList.add(tabelaNutricional);
            }
        }
        catch (SQLException e) {
            System.err.println("[DAO ERROR] Erro ao realizar a filtram da tabela nutricional por mínimo e/ou máximo");
            e.printStackTrace(System.err);
            throw new DataAccessException("Erro ao realizar a filtragem da tabela nutricional por mínimo e/ou máximo", e);
        }
        finally {
            try {
                if (connect != null) ConnectionFactory.disconnect(connect);
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                throw new DataAccessException("Erro ao fechar recursos do banco de dados", e);
            }
        }

        return tabelaNutricionalArrayList;
    }
}
