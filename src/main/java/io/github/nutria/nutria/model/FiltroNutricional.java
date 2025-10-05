package io.github.nutria.nutria.model;

import java.util.HashMap;
import java.util.Map;

public class FiltroNutricional {
    private final String coluna;
    private final String operador;
    private final double valor1;
    private final Double valor2;

    public FiltroNutricional(String coluna, String operador, double valor1, Double valor2) {
        this.coluna = coluna;
        this.operador = operador;
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public String getColuna() {
        return coluna;
    }
    public String getOperador() {
        return operador;
    }
    public double getValor1() {
        return valor1;
    }
    public Double getValor2() {
        return valor2;
    }

    public static Map<String, FiltroNutricional> filtrosNutricionais() {
        Map<String, FiltroNutricional> filtros = new HashMap<>();
        filtros.put("muito_baixo_sodio", new FiltroNutricional("sodio_mg", "<=", 40.00, null));
        filtros.put("baixo_sodio", new FiltroNutricional("sodio_mg", "BETWEEN", 41.00, 80.00));
        filtros.put("alto_sodio", new FiltroNutricional("sodio_mg", ">=", 600.00, null));
        filtros.put("alto_acucares_adicionados", new FiltroNutricional("acucares_adicionados_g", ">=", 15.00, null));
        filtros.put("alto_gorduras_saturadas", new FiltroNutricional("gorduras_saturadas_g", ">=", 6.00, null));
        filtros.put("fonte_proteinas", new FiltroNutricional("proteinas_g", "BETWEEN", 5.00, 9.99));
        filtros.put("rico_proteinas", new FiltroNutricional("proteinas_g", ">=", 10.00, null));
        filtros.put("fonte_fibras", new FiltroNutricional("fibra_alimentar_g", "BETWEEN", 2.50, 4.99));
        filtros.put("rico_fibras", new FiltroNutricional("fibra_alimentar_g", ">=", 5.00, null));
        return filtros;
    }
}
