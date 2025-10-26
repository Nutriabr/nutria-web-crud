package io.github.nutria.nutria.model;

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
}
