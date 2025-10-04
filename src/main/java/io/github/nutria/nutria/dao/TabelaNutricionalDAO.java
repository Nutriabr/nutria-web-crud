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
    private final static Connection connect = ConnectionFactory.connect();

    public TabelaNutricionalDAO() {}

    // Cadastrar usuario
    public boolean insert(TabelaNutricional tabelaNutricional) {
        // Preparando a query de inserção no banco de dados
        String sql = "INSERT INTO tabela_nutricional (valor_energetico_kcal, carboidratos_g, acucares_totais_g, acucares_adicionados_g, proteinas_g, gorduras_totais_g, gorduras_saturadas_g, fibra_alimentar_g, sodio_mg, colesterol_mg, vitamina_a_mcg, vitamina_c_mg, vitamina_d_mcg, calcio_mg, ferro_mg, potassio_mg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = null;
        boolean result;
        try {
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

            // Executando a query de inserção
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<TabelaNutricional> findAll(int page) {
        // 1. Setar em qual página inicia o crud de usuários que será = 1 // O limite de usuário por páginas do .jsp // A partir de qual registro começarão os registros da próxima página
        int limite = 4;
        int offset = (page - 1) * limite;

        // 2. Inicializar variável com a consulta SQL
        String sql = "SELECT * FROM tabela_nutricional LIMIT ? OFFSET ?";

        // 3. Instanciar uma lista para armazenar os usuarios retornados da consulta
        List<TabelaNutricional> tabelaNutricionalArrayList = new ArrayList<TabelaNutricional>();

        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(sql);
            /* 5. Setando os parâmetros passados no método para a instrução SQL
             * */
            ps.setInt(1, limite);
            ps.setInt(2, offset);


            rs = ps.executeQuery();
                /* 6. Enquanto o ResultSet tiver tabela Nutricional como resultado
                 * é instanciado uma nova tablea e armazenado no tabelaNutricionalArrayList
                 * */
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

                    // 7. A tablela é armazenada na lista
                    tabelaNutricionalArrayList.add(tabelaNutricional);
                }
        } catch (SQLException e) {
            // 8. Tratar exceções SQL
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }

                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tabelaNutricionalArrayList;
    }

    /**
     * Método para contar todos os tabelas do Banco de Dados
     * Esse método será utilizado para mostrar a quantidade de tabelas no banco antes de mostrar a tabela.
     * E como o método seguinte usará LIMIT E OFFSET não terá como guardar o total.
     * @return ArrayList Retorna um inteiro com o total de tabelas registrados no banco de dados
     * */
    public int countAll() {
        // 1. Inicializar a variavel responsável por armazenar a quantidade de tabelas
        int totalTabelas = 0;

        // 2. Inicializar a variavel responsável por armazenar a instrução sql
        String sql = "SELECT COUNT(*) FROM tabela_nutricional";

        // 3. Usar try simples para tratar as exceções SQL durante a instrução ao banco de dados
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connect.prepareStatement(sql);

            rs = pstmt.executeQuery();

            /* 4. Enquanto o ResultSet tiver tabeças como resultado
             * é incrementado +1 no totalTabelas
             * */
            while (rs.next()) {
                // 5. É incrementado +1 no totalTabelas
                totalTabelas++;
            }
        } catch (SQLException e) {
            // 6. Tratar exceções SQL
           e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalTabelas;
    }


    /**
     * Esse método será uma atualização geral para o usuário, ou seja, ele receberá todos os parâmetros e o que preicisar ser trocado será alterado aqui.
     * @param idIngrediente, valorEnergeticoKcal, carboidratosG, acucaresTotaisG, acucaresAdicionadosG, proteinasG, gordurasTotaisG, gordurasSaturadasG, fibraAlimentarG, sodioMg, colesterolMg, vitaminaAMcg, vitaminaCMg, vitaminaDMcg, calcioMg, ferroMg, potassioMg
     * @return boolean
     * */
    public boolean update(Long idIngrediente, double valorEnergeticoKcal, double carboidratosG, double acucaresTotaisG, double acucaresAdicionadosG, double proteinasG, double gordurasTotaisG, double gordurasSaturadasG, double fibraAlimentarG, double sodioMg, double colesterolMg, double vitaminaAMcg, double vitaminaCMg, double vitaminaDMcg, double calcioMg, double ferroMg, double potassioMg) {
        //1. Preparamos a instrução SQL
        String sql = "UPDATE usuario SET idIngrediente = ?, valorEnergeticoKcal = ?, carboidratosG = ?, acucaresTotaisG = ?, acucaresAdicionadosG = ?, proteinasG = ?, gordurasTotaisG = ?, gordurasSaturadasG = ?, fibraAlimentarG = ?, sodioMg = ?, colesterolMg = ?, vitaminaAMcg = ?, vitaminaCMg = ?, vitaminaDMcg = ?, calcioMg = ?, ferroMg = ?, potassioMg = ? WHERE id = ?";

        //2. Criamos a variável que receberá o número de linhas afetadas
        int result = 0;

        PreparedStatement pstmt = null;
        // 3. Usar try simples para tratar as exceções SQL durante a instrução ao banco de dados
        try {
            //4. Preparamos a instrção para o banco e logo em seguida setamos os valores
            pstmt = connect.prepareStatement(sql);

            //5. Setando os valores
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


        //6. Executando a instrução no banco
            result = pstmt.executeUpdate();

        //7. Fechamos as variáveis PreparedStatement e ResultSet (se necessário)
            pstmt.close();
        } catch (SQLException sqle) {
            //8. Tratar exceções SQL
            sqle.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //9. Verificamos se alguma linha do banco foi afetada, caso tenha sido a quantidade será representada no retorno do resultSet
        return (result > 0);
    }

    /**
     * Método para deletar usuário no Banco de Dados pelo seu id
     * @param id Recebe como o parametro o id do usuario que será deletado
     * @return boolean - Retorna <i>true</i> se o número de linhas afetadas foi maior que 0 ou <i>false</i> se o
     * número de linhas afetadas for igual ou menor que 0.
     * */
    @Override
    public boolean deleteById(Long id) throws SQLException {
        // 1. Inicializar variável com a query de delete do SQL
        String sql = "DELETE FROM tabela_nutricional WHERE id = ?";

        boolean result = false;

        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(sql);
            ps.setLong(1, id);

            // 3. Validação sobre o número de linhas afetadas, com atribuição de result para true.
            result = (ps.executeUpdate() > 0);

        } catch (SQLException e) {
            // 4. Tratar exceções SQL
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connect != null) {
                    ConnectionFactory.disconnect(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
